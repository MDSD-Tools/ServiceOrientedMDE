package tools.mdsd.characteristics.services.impl;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Pipe;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;
import net.bytebuddy.matcher.ElementMatchers;
import tools.mdsd.characteristics.services.Service;
import tools.mdsd.characteristics.services.ServiceManager;
import tools.mdsd.characteristics.services.ServiceWrapperFactory;
import tools.mdsd.characteristics.services.annotations.DispatchAll;
import tools.mdsd.characteristics.services.annotations.DispatchOnce;

public class ServiceWrapperImpl<ServiceType extends Service<ServiceType>> {
    public static ServiceWrapperFactory FACTORY = new ServiceWrapperFactory() {
        @Override
        public <ST extends Service<ST>> Class<? extends ST> createServiceWrapper(
                Class<ST> serviceType, ServiceManager<ST> manager) {
            return ServiceWrapperImpl.of(serviceType, manager);
        }
    };

    public static interface ServiceManagerProvider<ServiceType extends Service<ServiceType>> {
        ServiceManager<ServiceType> getManager();
    }

    public static class DispatchOnceServiceWrapper {
        @RuntimeType
        public static <ServiceType extends Service<ServiceType>> Object intercept(
                @Pipe Function<ServiceType, Object> forwarder,
                @AllArguments @RuntimeType Object[] parameters,
                @This ServiceManagerProvider<ServiceType> manager) {
            return manager.getManager().getService(parameters).map(forwarder)
                    .orElseThrow(() -> new IllegalArgumentException(String.format(
                            "No appropriate service of registered for object %s", parameters)));
        }
    }

    public static class DispatchAllServiceWrapper {
        @RuntimeType
        public static <ServiceType extends Service<ServiceType>> Object intercept(
                @Pipe Function<ServiceType, Object> forwarder,  
                @AllArguments @RuntimeType Object[] parameters,
                @This ServiceManagerProvider<ServiceType> manager) {
            return manager.getManager().collectServices(parameters).stream().map(forwarder)
                    .collect(Collectors.toList());
        }
    }


    public static <ST extends Service<ST>> Class<? extends ST> of(Class<ST> serviceType,
            ServiceManager<ST> manager) {

        return new ByteBuddy().subclass(serviceType).implement(ServiceManagerProvider.class)
                .intercept(FixedValue.value(manager))
                .method(ElementMatchers.isAnnotatedWith(DispatchOnce.class))
                .intercept(MethodDelegation.withDefaultConfiguration()
                        .withBinders(Pipe.Binder.install(Function.class))
                        .to(DispatchOnceServiceWrapper.class))
                .method(ElementMatchers.isAnnotatedWith(DispatchAll.class)
                        .and(ElementMatchers.returns(Collection.class)))
                .intercept(MethodDelegation.withDefaultConfiguration()
                        .withBinders(Pipe.Binder.install(Function.class))
                        .to(DispatchAllServiceWrapper.class))
                .make().load(serviceType.getClassLoader()).getLoaded();

    }

}
