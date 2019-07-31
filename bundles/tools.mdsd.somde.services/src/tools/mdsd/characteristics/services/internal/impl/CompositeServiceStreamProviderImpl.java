package tools.mdsd.characteristics.services.internal.impl;

import java.util.Collection;
import java.util.stream.Stream;
import tools.mdsd.characteristics.services.internal.ServiceStreamProvider;
import tools.mdsd.characteristics.services.internal.ServiceStreamProvider.ComposedServiceManager;

public class CompositeServiceStreamProviderImpl<ServiceType>
        implements ServiceStreamProvider<ServiceType>, ComposedServiceManager<ServiceType> {
    
    private Collection<ServiceStreamProvider<ServiceType>> containedProviders;

    public CompositeServiceStreamProviderImpl(Collection<ServiceStreamProvider<ServiceType>> containedProviders) {
        this.containedProviders = containedProviders;
    }

    @Override
    public Stream<ServiceType> getServiceManagers(Object... parameters) {
        return containedProviders.stream().flatMap(p -> p.getServiceManagers(parameters));
    }

}
