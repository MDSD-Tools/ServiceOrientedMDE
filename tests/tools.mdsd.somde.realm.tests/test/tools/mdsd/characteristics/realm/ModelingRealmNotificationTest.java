package tools.mdsd.characteristics.realm;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.mdsd.characteristics.realm.util.RealmResourceFactoryImpl;

class ModelingRealmNotificationTest {
    private List<Notifier> mockCalledFor;
    
    private class InjectorAdapterMock extends DependencyResolvingAdapter {
        @Override
        protected void doInject(Notifier object) {
            mockCalledFor.add(object);
        }        
    }
    
    @BeforeEach
    void setup() {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("modelingrealm", new RealmResourceFactoryImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("stuff", new XMIResourceFactoryImpl());
        mockCalledFor = new LinkedList<>();
    }

    @Test
    void test() {
        EObject object = EcoreFactory.eINSTANCE.createEObject();
        var adapterMock = new InjectorAdapterMock();
        object.eAdapters().add(adapterMock);
        
        
        ResourceSet resSet = new ResourceSetImpl();
        Resource res = resSet.createResource(URI.createFileURI("file:/./test.modelingrealm"));
        res.getContents().add(object);
        
        assertTrue(resSet.eAdapters().contains(adapterMock));
        assertTrue(mockCalledFor.contains(object));
        
        Resource newRes = resSet.createResource(URI.createFileURI("file:/./test.stuff"));
        EObject newObj = EcoreFactory.eINSTANCE.createEObject();
        newRes.getContents().add(newObj);
        assertTrue(mockCalledFor.contains(newObj));
    }

}
