package tools.mdsd.somde.realm.guicebased;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.google.inject.AbstractModule;

public class MockModule extends AbstractModule {
    boolean fooCalled = false;
    
    MockService serviceInstance = new MockService() {
        @Override
        public void foo() {
            fooCalled = true;
        }
    };
    
    @Override
    protected void configure() {
        bind(MockService.class).toInstance(serviceInstance);
    }
    
    public void assertFooNotCalled() {
        assertFalse(fooCalled);
    }
    public void assertFooCalled() {
        assertTrue(fooCalled);
    }
}
