package tools.mdsd.characteristics.services.internal.impl;

import java.util.stream.Stream;
import tools.mdsd.characteristics.services.internal.ServiceStreamProvider;

public class SingleServiceStreamProviderImpl<ServiceType>
        implements ServiceStreamProvider<ServiceType> {

    private ServiceType service;

    public SingleServiceStreamProviderImpl(ServiceType service) {
        this.service = service;
    }

    @Override
    public Stream<ServiceType> getServiceManagers(Object... parameters) {
        return Stream.of(service);
    }

}
