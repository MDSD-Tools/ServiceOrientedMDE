package tools.mdsd.characteristics.services.impl;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Provider;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeDescription.ForLoadedType;
import net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Pipe;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;
import net.bytebuddy.matcher.ElementMatchers;
import tools.mdsd.characteristics.services.Service;
import tools.mdsd.characteristics.services.ServiceManager;
import tools.mdsd.characteristics.services.annotations.DispatchAll;
import tools.mdsd.characteristics.services.annotations.DispatchOnce;

public class ServiceWrapperImpl<ServiceType extends Service<ServiceType>> {
    public static interface ServiceManagerProviderAccess<ServiceType> {
        Provider<ServiceManager<ServiceType>> getManagerProvider();
    }

    public static class DispatchOnceServiceWrapper {
        @RuntimeType
        public static <ServiceType extends Service<ServiceType>> Object intercept(
                @Pipe Function<ServiceType, Object> forwarder,
                @AllArguments @RuntimeType Object[] parameters,
                @This ServiceManagerProviderAccess<ServiceType> access) {
            return forwarder.apply(access.getManagerProvider().get().getService(parameters)
                    .orElseThrow(() -> new IllegalArgumentException(String.format(
                            "No appropriate service of registered for object %s", Arrays.toString(parameters)))));
        }
    }

    public static class DispatchAllServiceWrapper {
        @RuntimeType
        public static <ServiceType extends Service<ServiceType>> Object intercept(
                @Pipe Function<ServiceType, Object> forwarder,
                @AllArguments @RuntimeType Object[] parameters,
                @This ServiceManagerProviderAccess<ServiceType> access) {
            return access.getManagerProvider().get().collectServices(parameters).stream()
                    .map(forwarder).collect(Collectors.toList());
        }
    }

    public static <ST extends Service<ST>> Class<? extends ST> of(Class<ST> serviceType) {
        var managerType = TypeDescription.Generic.Builder
                .parameterizedType(ForLoadedType.of(Provider.class),
                        TypeDescription.Generic.Builder
                                .parameterizedType(ServiceManager.class, serviceType).build())
                .build();
        try {
            return new ByteBuddy().subclass(serviceType, ConstructorStrategy.Default.NO_CONSTRUCTORS)
                    .method(ElementMatchers.isAnnotatedWith(DispatchOnce.class))
                    .intercept(MethodDelegation.withDefaultConfiguration()
                            .withBinders(Pipe.Binder.install(Function.class))
                            .to(DispatchOnceServiceWrapper.class))
                    .method(ElementMatchers.isAnnotatedWith(DispatchAll.class)
                            .and(ElementMatchers.returns(Collection.class)))
                    .intercept(MethodDelegation.withDefaultConfiguration()
                            .withBinders(Pipe.Binder.install(Function.class))
                            .to(DispatchAllServiceWrapper.class))
                    .defineField("managerProvider", managerType)
                    .implement(ServiceManagerProviderAccess.class)
                    .intercept(FieldAccessor.ofBeanProperty()).defineConstructor(Visibility.PUBLIC)
                    .withParameter(managerType)
                    .intercept(MethodCall
                            .invoke(serviceType.isInterface() ? Object.class.getConstructor()
                                    : serviceType.getConstructor())
                            .andThen(FieldAccessor.ofField("managerProvider").setsArgumentAt(0)))
                    .annotateMethod(new Inject() {
                        @Override
                        public Class<? extends Annotation> annotationType() {
                            return Inject.class;
                        }
                    })

                    .make().load(serviceType.getClassLoader()).getLoaded();
        } catch (NoSuchMethodException | SecurityException e) {
            throw new IllegalArgumentException(e);
        }

    }

}
