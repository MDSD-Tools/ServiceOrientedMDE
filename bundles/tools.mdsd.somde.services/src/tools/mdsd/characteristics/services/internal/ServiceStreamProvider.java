package tools.mdsd.characteristics.services.internal;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import tools.mdsd.characteristics.services.ServiceManager;

public interface ServiceStreamProvider<ServiceType> {

    public interface ComposedServiceManager<ServiceType>
            extends ServiceStreamProvider<ServiceType>, ServiceManager<ServiceType> {

        default Optional<ServiceType> getService(Object... parameters) {
            return getServiceManagers(parameters).findFirst();
        }

        default Collection<ServiceType> collectServices(Object... parameters) {
            return getServiceManagers(parameters).collect(Collectors.toList());
        }
    }

    Stream<ServiceType> getServiceManagers(Object... parameters);

    public static class EmptyStreamProvider implements ServiceStreamProvider<Object> {
        @Override
        public Stream<Object> getServiceManagers(Object... parameters) {
            return Stream.empty();
        }
    }

    public final static EmptyStreamProvider EMPTY_PROVIDER = new EmptyStreamProvider();

    @SuppressWarnings("unchecked")
    public static <ServiceType> ServiceStreamProvider<ServiceType> empty() {
        return (ServiceStreamProvider<ServiceType>) EMPTY_PROVIDER;
    }
}
