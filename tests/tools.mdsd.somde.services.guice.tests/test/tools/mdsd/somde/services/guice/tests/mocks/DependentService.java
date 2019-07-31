package tools.mdsd.somde.services.guice.tests.mocks;

import tools.mdsd.somde.services.Service;
import tools.mdsd.somde.services.ServiceRegistrationFacade;
import tools.mdsd.somde.services.annotations.DispatchOnce;

public interface DependentService extends Service<DependentService> {
    
    @DispatchOnce
    String foo();
    
    @Override
    default void registerService(ServiceRegistrationFacade<DependentService> facade) {
        //Not necessary for test
    }

}
