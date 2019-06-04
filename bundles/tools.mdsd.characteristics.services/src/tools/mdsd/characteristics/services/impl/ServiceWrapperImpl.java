package tools.mdsd.characteristics.services.impl;

import java.util.function.Function;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.Argument;
import net.bytebuddy.implementation.bind.annotation.Pipe;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.matcher.ElementMatchers;
import tools.mdsd.characteristics.services.Service;
import tools.mdsd.characteristics.services.ServiceManager;
import tools.mdsd.characteristics.services.ServiceWrapperFactory;

@SuppressWarnings("restriction")
public class ServiceWrapperImpl<ObjectType, ServiceType extends Service<ServiceType>> {
    public static ServiceWrapperFactory FACTORY = new ServiceWrapperFactory() {
        @Override
        public <OT, ST extends Service<ST>> Class<? extends ST> createServiceWrapper(
                Class<ST> serviceType, ServiceManager<OT, ST> manager) {
            return ServiceWrapperImpl.of(serviceType, manager);
        }
    };

    private ServiceManager<ObjectType, ServiceType> manager;

    public ServiceWrapperImpl(ServiceManager<ObjectType, ServiceType> manager) {
        this.manager = manager;

    }

    @SuppressWarnings("unchecked")
    @RuntimeType
    public Object intercept(@Pipe Function<ServiceType, Object> forwarder,
            @Argument(0) @RuntimeType Object object) {
        return manager.getService((ObjectType) object).map(forwarder)
                .orElseThrow(() -> new IllegalArgumentException(String
                        .format("No appropriate service of registered for object %s", object)));
    }

    public static <OT, ST extends Service<ST>> Class<? extends ST> of(Class<ST> serviceType,
            ServiceManager<OT, ST> manager) {
        ServiceWrapperImpl<OT, ST> wrapper = new ServiceWrapperImpl<OT, ST>(manager);
        return new ByteBuddy().subclass(serviceType)
                .method(ElementMatchers.isDeclaredBy(serviceType))
                .intercept(MethodDelegation.withDefaultConfiguration()
                        .withBinders(Pipe.Binder.install(Function.class)).to(wrapper))
                .make().load(serviceType.getClassLoader()).getLoaded();

    }

}
