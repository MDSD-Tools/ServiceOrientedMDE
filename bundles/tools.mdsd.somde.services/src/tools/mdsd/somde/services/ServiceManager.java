package tools.mdsd.somde.services;

import java.util.Collection;
import java.util.Optional;

public interface ServiceManager<ServiceType> {

    Optional<ServiceType> getService(Object... parameter);

    Collection<ServiceType> collectServices(Object... parameters);

}
