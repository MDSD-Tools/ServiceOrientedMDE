package tools.mdsd.characteristics.services;

import java.util.Collection;

public interface ServiceTypeRegistryAccess {
    Collection<Class<?>> getRegisteredServiceTypes();
    
    Collection<Class<?>> getRegisteredImplementations(Class<?> serviceType);
}
