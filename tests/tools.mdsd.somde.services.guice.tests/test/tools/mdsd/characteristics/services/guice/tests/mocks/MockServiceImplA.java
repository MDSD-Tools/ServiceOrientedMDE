package tools.mdsd.characteristics.services.guice.tests.mocks;

public class MockServiceImplA implements MockService {

    @Override
    public String foo() {
        return getClass().getName();
    }

}
