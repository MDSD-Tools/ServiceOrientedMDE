package tools.mdsd.characteristics.services.guice.tests.mocks;

import tools.mdsd.characteristics.services.ServiceRegistrationFacade;

public class UnrelatedServiceImpl implements UnrelatedService {

    @Override
    public void registerService(ServiceRegistrationFacade<UnrelatedService> facade) {
    }

    @Override
    public int completelyDifferentService(float test) {
        return 42;
    }

}
