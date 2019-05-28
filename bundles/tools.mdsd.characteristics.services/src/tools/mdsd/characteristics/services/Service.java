package tools.mdsd.characteristics.services;

public interface Service<ST extends Service<ST>> {
    public interface ServiceRegistrationFacade<ServiceType> {
        <T> void register(ServiceType service, Class<T> keyType, T key);        
    }
    
    void registerService(ServiceRegistrationFacade<? super ST> facade);
}
