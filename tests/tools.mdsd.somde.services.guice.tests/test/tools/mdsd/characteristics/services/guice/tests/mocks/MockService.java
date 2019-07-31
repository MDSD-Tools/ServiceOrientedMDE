package tools.mdsd.characteristics.services.guice.tests.mocks;

import tools.mdsd.characteristics.services.Service;
import tools.mdsd.characteristics.services.ServiceRegistrationFacade;
import tools.mdsd.characteristics.services.annotations.DispatchOnce;

public interface MockService extends Service<MockService> {
    
    @DispatchOnce
    String foo();
    
    @Override
    default void registerService(ServiceRegistrationFacade<MockService> facade) {
        //Not necessary for test
    }

}
