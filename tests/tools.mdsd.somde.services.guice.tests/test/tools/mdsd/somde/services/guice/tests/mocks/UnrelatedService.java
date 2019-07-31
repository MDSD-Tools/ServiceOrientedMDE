package tools.mdsd.somde.services.guice.tests.mocks;

import tools.mdsd.somde.services.Service;
import tools.mdsd.somde.services.annotations.DispatchOnce;

public interface UnrelatedService extends Service<UnrelatedService>{ 
    
    @DispatchOnce
    int completelyDifferentService(float test);

}
