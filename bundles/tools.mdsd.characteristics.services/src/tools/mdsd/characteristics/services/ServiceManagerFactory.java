package tools.mdsd.characteristics.services;


public interface ServiceManagerFactory<ObjectType> {
    <T extends Service<T>> ServiceManager<ObjectType, T> createServiceManager(Iterable<T> services);

}
