package tools.mdsd.characteristics.services.impl;

import java.util.Optional;
import java.util.WeakHashMap;
import java.util.function.Function;
import tools.mdsd.characteristics.services.ServiceRegistration;
import tools.mdsd.characteristics.services.ServiceRegistry;

public class ServiceRegistryImpl<KeyType, ServiceType, ObjectType>
        implements ServiceRegistry<KeyType, ServiceType, ObjectType> {
    private final WeakHashMap<KeyType, ServiceType> serviceCache;
    private final Function<ObjectType, KeyType> keyRetrievalFunc;
    private final Class<KeyType> keyType;

    public ServiceRegistryImpl(
            Iterable<ServiceRegistration<? extends ServiceType, KeyType>> services,
            Class<KeyType> keyType, Function<ObjectType, KeyType> keyRetrievalFunc) {
        this.keyType = keyType;
        this.keyRetrievalFunc = keyRetrievalFunc;
        serviceCache = new WeakHashMap<>();
        for (ServiceRegistration<? extends ServiceType, KeyType> reg : services) {
            serviceCache.put(reg.getKey(), reg.getService());
        }
    }

    public Optional<ServiceType> getService(ObjectType object) {
        return Optional.ofNullable(serviceCache.get(keyRetrievalFunc.apply(object)));
    }

    public Class<KeyType> getKeyType() {
        return keyType;
    }


}
