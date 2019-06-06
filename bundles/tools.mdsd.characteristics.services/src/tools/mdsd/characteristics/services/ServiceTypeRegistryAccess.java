package tools.mdsd.characteristics.services;

public interface ServiceTypeRegistryAccess {
    Iterable<Class<? extends Service<?>>> getRegisteredServiceTypes();
    
    <T extends Service<T>> Iterable<Class<? extends T>> getRegisteredImplementations(Class<T> serviceType);
}
