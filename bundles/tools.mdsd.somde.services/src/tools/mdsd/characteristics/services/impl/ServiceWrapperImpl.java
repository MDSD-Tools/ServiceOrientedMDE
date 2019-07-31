package tools.mdsd.characteristics.services.impl;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.function.Function;
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
import net.bytebuddy.implementation.bind.annotation.Pipe;
import net.bytebuddy.matcher.ElementMatchers;
import tools.mdsd.characteristics.services.Service;
import tools.mdsd.characteristics.services.ServiceManager;
import tools.mdsd.characteristics.services.ServiceManagerProviderAccess;
import tools.mdsd.characteristics.services.annotations.DispatchAll;
import tools.mdsd.characteristics.services.annotations.DispatchOnce;
import tools.mdsd.characteristics.services.delegation.DispatchAllServiceWrapper;
import tools.mdsd.characteristics.services.delegation.DispatchOnceServiceWrapper;

@SuppressWarnings("restriction")
public class ServiceWrapperImpl<ServiceType extends Service<ServiceType>> {
    public static <ST extends Service<ST>> Class<? extends ST> of(Class<ST> serviceType) {
        var managerType = TypeDescription.Generic.Builder
                .parameterizedType(ForLoadedType.of(Provider.class),
                        TypeDescription.Generic.Builder
                                .parameterizedType(ServiceManager.class, serviceType).build())
                .build();
        try {
            return new ByteBuddy()
                    .subclass(serviceType, ConstructorStrategy.Default.NO_CONSTRUCTORS)
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
