package tools.mdsd.characteristics.services;

import java.util.Collection;

public interface ServiceRegistryFactory {
    
    Collection<Class<?>> getSupportedKeyTypes();
    
    <KeyType, ServiceType, ObjectType> ServiceRegistry<KeyType, ServiceType, ObjectType> createRegistry(
            Iterable<ServiceRegistration<? extends ServiceType, KeyType>> services,
            Class<KeyType> keyType);
    
    
    

}
