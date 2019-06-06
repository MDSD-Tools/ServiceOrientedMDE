package tools.mdsd.characteristics.services;

public interface ServiceWrapperFactory {
    <ST extends Service<ST>> Class<? extends ST> createServiceWrapper(Class<ST> serviceType,
            ServiceManager<ST> manager);
}
