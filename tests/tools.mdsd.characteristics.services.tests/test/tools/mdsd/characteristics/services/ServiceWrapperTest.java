package tools.mdsd.characteristics.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.mdsd.characteristics.services.annotations.DispatchOnce;
import tools.mdsd.characteristics.services.impl.ServiceWrapperImpl;

class ServiceWrapperTest {
    /**
     * Some dummy service implementation to test the managing functionality
     */
    public interface TestService extends Service<TestService> {
        @DispatchOnce
        int getTheAnswerToLifeTheUniverseAndEverything(EObject object);

        EObject getSomeOtherEObject(EObject object, int parameter, EObject secondParameter);

        @Override
        default void registerService(ServiceRegistrationFacade<TestService> facade) {
            // Since we are using a mock manager no registration is necessary
        }
    }

    private static EObject keyObject = EcoreFactory.eINSTANCE.createEObject();
    private static EObject keyObject2 = EcoreFactory.eINSTANCE.createEObject();
    private static EObject notRegisteredKey = EcoreFactory.eINSTANCE.createEObject();

    protected ServiceWrapperFactory wrapperFactory = ServiceWrapperImpl.FACTORY;

    private TestService mockService;
    private TestService mockService2;
    private ServiceManager<TestService> mockManager;


    @BeforeEach
    void initializeMocks() {
        mockService = mock(TestService.class);
        mockService2 = mock(TestService.class);

        when(mockService.getTheAnswerToLifeTheUniverseAndEverything(any())).thenReturn(0);
        when(mockService.getTheAnswerToLifeTheUniverseAndEverything(keyObject)).thenReturn(42);
        when(mockService2.getTheAnswerToLifeTheUniverseAndEverything(any())).thenReturn(0);
        when(mockService2.getTheAnswerToLifeTheUniverseAndEverything(keyObject2)).thenReturn(43);

        mockManager = new ServiceManager<TestService>() {

            @Override
            public Optional<TestService> getService(Object... parameter) {
                if (parameter[0] == keyObject) {
                    return Optional.of(mockService);
                } else if (parameter[0] == keyObject2) {
                    return Optional.of(mockService2);
                }
                throw new IllegalArgumentException();
            }

            @Override
            public Collection<TestService> collectServices(Object... parameters) {
                return Collections.emptyList();
            }
        };
    }

    @Test
    void testServiceCall()
            throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        // Create ServiceWrapper to test
        TestService managedService =
                wrapperFactory.createServiceWrapper(TestService.class, mockManager)
                        .getDeclaredConstructor().newInstance();

        // Verify behavior
        assertEquals(42, managedService.getTheAnswerToLifeTheUniverseAndEverything(keyObject));

        verify(mockService, times(1)).getTheAnswerToLifeTheUniverseAndEverything(any());
        verify(mockService2, times(0)).getTheAnswerToLifeTheUniverseAndEverything(any());
        verifyNoMoreInteractions(mockService);
        verifyNoMoreInteractions(mockService2);

        assertEquals(43, managedService.getTheAnswerToLifeTheUniverseAndEverything(keyObject2));

        verify(mockService, times(1)).getTheAnswerToLifeTheUniverseAndEverything(any());
        verify(mockService2, times(1)).getTheAnswerToLifeTheUniverseAndEverything(any());
        verifyNoMoreInteractions(mockService);
        verifyNoMoreInteractions(mockService2);

        assertThrows(IllegalArgumentException.class,
                () -> managedService.getTheAnswerToLifeTheUniverseAndEverything(notRegisteredKey));

        verifyNoMoreInteractions(mockService);
        verifyNoMoreInteractions(mockService2);
    }

}
