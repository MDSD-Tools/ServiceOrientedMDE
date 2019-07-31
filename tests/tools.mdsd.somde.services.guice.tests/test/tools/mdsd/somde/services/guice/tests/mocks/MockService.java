package tools.mdsd.somde.services.guice.tests.mocks;

import tools.mdsd.somde.services.Service;
import tools.mdsd.somde.services.ServiceRegistrationFacade;
import tools.mdsd.somde.services.annotations.DispatchOnce;

public interface MockService extends Service<MockService> {
    
    @DispatchOnce
    String foo();
    
    @Override
    default void registerService(ServiceRegistrationFacade<MockService> facade) {
        //Not necessary for test
    }

}
