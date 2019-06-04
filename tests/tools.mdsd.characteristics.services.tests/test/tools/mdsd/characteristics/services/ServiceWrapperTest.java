package tools.mdsd.characteristics.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import tools.mdsd.characteristics.services.impl.ServiceWrapperImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ServiceWrapperTest {
    /**
     * Some dummy service implementation to test the managing functionality
     */
    public interface TestService extends Service<TestService> {
        int getTheAnswerToLifeTheUniverseAndEverything(EObject object);

        EObject getSomeOtherEObject(EObject object, int parameter, EObject secondParameter);

        @Override
        default void registerService(ServiceRegistrationFacade<? super TestService> facade) {
            facade.register(this, EObject.class, keyObject);
        }
    }

    private static EObject keyObject = EcoreFactory.eINSTANCE.createEObject();
    private static EObject keyObject2 = EcoreFactory.eINSTANCE.createEObject();
    private static EObject notRegisteredKey = EcoreFactory.eINSTANCE.createEObject();
    
    protected ServiceWrapperFactory wrapperFactory = ServiceWrapperImpl.FACTORY;


    @Test
    void testServiceCall(@Mock ServiceManager<EObject, TestService> mockManager,
            @Mock TestService mockService, @Mock TestService mockService2)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {

        // Set-up service mocks
        when(mockManager.getService(notRegisteredKey)).thenThrow(IllegalArgumentException.class);
        when(mockManager.getService(keyObject)).thenReturn(Optional.of(mockService));
        when(mockManager.getService(keyObject2)).thenReturn(Optional.of(mockService2));

        when(mockService.getTheAnswerToLifeTheUniverseAndEverything(any())).thenReturn(0);
        when(mockService.getTheAnswerToLifeTheUniverseAndEverything(keyObject)).thenReturn(42);
        when(mockService2.getTheAnswerToLifeTheUniverseAndEverything(any())).thenReturn(0);
        when(mockService2.getTheAnswerToLifeTheUniverseAndEverything(keyObject2)).thenReturn(43);

        // Create ServiceWrapper to test
        TestService managedService = wrapperFactory.createServiceWrapper(TestService.class, mockManager)
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
