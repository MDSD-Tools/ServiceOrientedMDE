package tools.mdsd.somde.services;

public interface ServiceManagerFactory {
    
    <T extends Service<T>> ServiceManager<T> createServiceManager(Iterable<T> services);

}
