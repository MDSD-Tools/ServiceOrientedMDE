package tools.mdsd.somde.services.guice.tests.mocks;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import tools.mdsd.somde.services.Service;
import tools.mdsd.somde.services.ServiceManager;
import tools.mdsd.somde.services.ServiceManagerFactory;

public class MockServiceManagerFactory implements ServiceManagerFactory {

    @Override
    public <T extends Service<T>> ServiceManager<T> createServiceManager(Iterable<T> services) {
        return new ServiceManager<T>() {
            
            // The mocked service manager simply returns the first of the services
            
            @Override
            public Optional<T> getService(Object... parameter) {
                return Optional.of(services.iterator().next());
            }

            @Override
            public Collection<T> collectServices(Object... parameters) {
                return Collections.singletonList(services.iterator().next());
            }
            
        };
    }

}
