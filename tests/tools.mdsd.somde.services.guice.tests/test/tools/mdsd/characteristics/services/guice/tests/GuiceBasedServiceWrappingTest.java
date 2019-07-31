package tools.mdsd.characteristics.services.guice.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Set;
import org.junit.jupiter.api.Test;
import com.google.inject.AbstractModule;
import com.google.inject.ConfigurationException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.util.Modules;
import tools.mdsd.characteristics.services.ServiceManagerFactory;
import tools.mdsd.characteristics.services.guice.ServiceWrapperDelegationModule;
import tools.mdsd.characteristics.services.guice.tests.mocks.DependentService;
import tools.mdsd.characteristics.services.guice.tests.mocks.DependentServiceImpl;
import tools.mdsd.characteristics.services.guice.tests.mocks.MockService;
import tools.mdsd.characteristics.services.guice.tests.mocks.MockServiceImplA;
import tools.mdsd.characteristics.services.guice.tests.mocks.MockServiceImplB;
import tools.mdsd.characteristics.services.guice.tests.mocks.MockServiceManagerFactory;
import tools.mdsd.characteristics.services.guice.tests.mocks.UnrelatedService;
import tools.mdsd.characteristics.services.guice.tests.mocks.UnrelatedServiceImpl;

class GuiceBasedServiceWrappingTest {

    private static class MockModule extends AbstractModule {
        @Override
        protected void configure() {
            var multibinder = Multibinder.newSetBinder(binder(), MockService.class);
            multibinder.addBinding().to(MockServiceImplA.class);
            multibinder.addBinding().to(MockServiceImplB.class);

            var unrelatedBinder = Multibinder.newSetBinder(binder(), UnrelatedService.class);
            unrelatedBinder.addBinding().to(UnrelatedServiceImpl.class);

            bind(ServiceManagerFactory.class).to(MockServiceManagerFactory.class);
        }
    }

    private static class DependentMockModule extends AbstractModule {
        @Override
        protected void configure() {
            var dependentBinder = Multibinder.newSetBinder(binder(), DependentService.class);
            dependentBinder.addBinding().to(DependentServiceImpl.class);
        }
    }


    @Test
    void testInstantiation() {
        Module mod = new MockModule();
        Injector injector = Guice.createInjector(mod);

        assertThrows(ConfigurationException.class, () -> injector.getInstance(MockService.class));

        Set<MockService> services = injector.getInstance(Key.get(new TypeLiteral<Set<MockService>>() {}));
        assertEquals(2, services.size());

        mod = Modules.override(mod).with(new ServiceWrapperDelegationModule<>(MockService.class),
                new ServiceWrapperDelegationModule<>(UnrelatedService.class));

        Injector newInjector = Guice.createInjector(mod);

        MockService service = newInjector.getInstance(MockService.class);
        assertFalse(service instanceof MockServiceImplA);
        assertFalse(service instanceof MockServiceImplB);
        assertEquals(MockServiceImplA.class.getName(), service.foo());

        UnrelatedService unrelated = newInjector.getInstance(UnrelatedService.class);
        assertEquals(unrelated.completelyDifferentService(0.2f), 42);
    }

    @Test
    void testDependentServices() {
        Module mod = Modules.override(new MockModule()).with(new DependentMockModule(),
                new ServiceWrapperDelegationModule<>(MockService.class),
                new ServiceWrapperDelegationModule<>(UnrelatedService.class),
                new ServiceWrapperDelegationModule<>(DependentService.class));
        Injector injector = Guice.createInjector(mod);

        DependentService dependent = injector.getInstance(DependentService.class);
        assertEquals("delegated:" + MockServiceImplA.class.getName(), dependent.foo());
    }

}
