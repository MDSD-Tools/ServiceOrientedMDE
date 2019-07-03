package tools.mdsd.characteristics.services.guice.tests.mocks;

import tools.mdsd.characteristics.services.Service;
import tools.mdsd.characteristics.services.ServiceRegistrationFacade;
import tools.mdsd.characteristics.services.annotations.DispatchOnce;

public interface DependentService extends Service<DependentService> {
    
    @DispatchOnce
    String foo();
    
    @Override
    default void registerService(ServiceRegistrationFacade<DependentService> facade) {
        //Not necessary for test
    }

}
