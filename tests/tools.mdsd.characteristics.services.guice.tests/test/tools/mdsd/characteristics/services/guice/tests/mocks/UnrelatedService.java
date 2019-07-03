package tools.mdsd.characteristics.services.guice.tests.mocks;

import tools.mdsd.characteristics.services.Service;
import tools.mdsd.characteristics.services.annotations.DispatchOnce;

public interface UnrelatedService extends Service<UnrelatedService>{ 
    
    @DispatchOnce
    int completelyDifferentService(float test);

}
