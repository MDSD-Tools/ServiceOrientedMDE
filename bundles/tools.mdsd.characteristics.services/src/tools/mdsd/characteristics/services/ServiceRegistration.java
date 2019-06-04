package tools.mdsd.characteristics.services;

public class ServiceRegistration<ServiceType, KeyType> {

    private final ServiceType service;
    private final KeyType key;

    public ServiceRegistration(ServiceType service, KeyType key) {
        this.service = service;
        this.key = key;
    }

    public ServiceType getService() {
        return service;
    }

    public KeyType getKey() {
        return key;
    }
}
