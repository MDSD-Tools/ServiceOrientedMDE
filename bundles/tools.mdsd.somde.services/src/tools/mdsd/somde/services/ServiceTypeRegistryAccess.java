package tools.mdsd.somde.services;

import java.util.Collection;

public interface ServiceTypeRegistryAccess {
    Collection<Class<?>> getRegisteredServiceTypes();
    
    Collection<Class<?>> getRegisteredImplementations(Class<?> serviceType);
}
