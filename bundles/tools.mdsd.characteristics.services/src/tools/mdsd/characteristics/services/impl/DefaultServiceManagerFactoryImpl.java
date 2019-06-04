package tools.mdsd.characteristics.services.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import tools.mdsd.characteristics.services.Service;
import tools.mdsd.characteristics.services.Service.ServiceRegistrationFacade;
import tools.mdsd.characteristics.services.ServiceManager;
import tools.mdsd.characteristics.services.ServiceManagerFactory;
import tools.mdsd.characteristics.services.ServiceRegistration;
import tools.mdsd.characteristics.services.ServiceRegistry;
import tools.mdsd.characteristics.services.ServiceRegistry.ServiceRegistryFactory;

public class DefaultServiceManagerFactoryImpl<ObjectType> implements ServiceManagerFactory<ObjectType> {
    public class ServiceManagerBuilder<ST extends Service<ST>, OT>
            implements ServiceRegistrationFacade<ST> {
        private Map<Class<?>, List<ServiceRegistration<ST, ?>>> registrations = new HashMap<>();

        @Override
        public <T> void register(ST service, Class<T> keyType, T key) {
            registrations.computeIfAbsent(keyType, kt -> new LinkedList<>())
                    .add(new ServiceRegistration<>(service, key));
        }

        @SuppressWarnings("unchecked")
        public ServiceManager<OT, ST> build() {
            List<ServiceRegistry<?, ST, OT>> registries =
                    registrations.entrySet().stream().map(entry -> {
                        @SuppressWarnings("rawtypes")
                        List regs = entry.getValue();
                        @SuppressWarnings("rawtypes")
                        Class key = entry.getKey();
                        return registryFactory.create(regs, key);
                    }).collect(Collectors.toList());
            return new ServiceManager<>(registries);
        }
    }

    private ServiceRegistryFactory<?, ObjectType> registryFactory;

    // TODO: Couldn't solve generics so that ServiceManagerFactory specifies a lower bound for
    // the service class. Therefore the manager class currently only supports completely service
    // agnostic registry factories.
    public DefaultServiceManagerFactoryImpl(ServiceRegistryFactory<Object, ObjectType> registryFactory) {
        this.registryFactory = registryFactory;
    }

    public <T extends Service<T>> ServiceManager<ObjectType, T> createServiceManager(
            Iterable<T> services) {
        ServiceManagerBuilder<T, ObjectType> builder = new ServiceManagerBuilder<T, ObjectType>();
        for (T service : services) {
            service.registerService(builder);
        }
        return builder.build();
    }
}
