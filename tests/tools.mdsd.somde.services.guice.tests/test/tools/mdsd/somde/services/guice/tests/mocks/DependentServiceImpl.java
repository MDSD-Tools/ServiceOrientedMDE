package tools.mdsd.somde.services.guice.tests.mocks;

import javax.inject.Inject;

public class DependentServiceImpl implements DependentService {
    
    @Inject
    MockService mockService;

    @Override
    public String foo() {
        return "delegated:" + mockService.foo();
    }

}
