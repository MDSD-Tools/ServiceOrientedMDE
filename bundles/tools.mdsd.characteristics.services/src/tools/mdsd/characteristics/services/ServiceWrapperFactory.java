package tools.mdsd.characteristics.services;

public interface ServiceWrapperFactory {
    <OT, ST extends Service<ST>> Class<? extends ST> createServiceWrapper(Class<ST> serviceType,
            ServiceManager<OT, ST> manager);
}
