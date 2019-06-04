package tools.mdsd.characteristics.services;

import java.util.List;
import java.util.Optional;

public class ServiceManager<ObjectType, ServiceType extends Service<ServiceType>> {

    private List<ServiceRegistry<?, ServiceType, ObjectType>> serviceRegistries;

    public ServiceManager(List<ServiceRegistry<?, ServiceType, ObjectType>> serviceRegistries) {
        this.serviceRegistries = serviceRegistries;
    }

    public Optional<ServiceType> getService(ObjectType object) {
        for (ServiceRegistry<?, ServiceType, ObjectType> reg : serviceRegistries) {
            Optional<ServiceType> service = reg.getService(object);
            if (service.isPresent())
                return service;
        }
        return Optional.empty();
    }




}
