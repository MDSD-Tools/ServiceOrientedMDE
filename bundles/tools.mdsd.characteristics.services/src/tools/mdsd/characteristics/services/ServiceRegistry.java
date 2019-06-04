package tools.mdsd.characteristics.services;

import java.util.Optional;

public interface ServiceRegistry<KeyType, ServiceType, ObjectType> {
    public interface ServiceRegistryFactory<ServiceType, ObjectType> {
        <KeyType> ServiceRegistry<KeyType, ServiceType, ?> create(
                Iterable<ServiceRegistration<? extends ServiceType, KeyType>> services,
                Class<KeyType> keyType);
    }
    
    Optional<ServiceType> getService(ObjectType object);
    
    Class<KeyType> getKeyType();

}
