package tools.mdsd.characteristics.realm.guicebased;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.google.inject.Guice;
import com.google.inject.Injector;
import tools.mdsd.characteristics.realm.guicebased.impl.GuiceInjectorDependencyResolvingAdapter;
import tools.mdsd.characteristics.realm.guicebased.test.guicetest.Container;
import tools.mdsd.characteristics.realm.guicebased.test.guicetest.Element;
import tools.mdsd.characteristics.realm.guicebased.test.guicetest.GuiceTestFactory;

class GuiceInjectingAdapterTest {
    @BeforeAll
    static void setup() {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*",
                new XMIResourceFactoryImpl());
    }

    @Test
    void testInjection() {
        MockModule module = new MockModule();
        Injector injector = Guice.createInjector(module);
        GuiceInjectorDependencyResolvingAdapter adapterToTest =
                new GuiceInjectorDependencyResolvingAdapter(() -> injector);

        ResourceSet resSet = new ResourceSetImpl();
        resSet.eAdapters().add(adapterToTest);
        Resource res = resSet.createResource(URI.createFileURI("file:/./test.xmi"));

        Container container = GuiceTestFactory.eINSTANCE.createContainer();
        res.getContents().add(container);

        Element element = GuiceTestFactory.eINSTANCE.createElement();
        container.getElements().add(element);
        element.callFoo();

        module.assertFooCalled();
    }

    @Test
    void testInjectorUpdate() {
        MockModule module = new MockModule();
        MockModule updatedModule = new MockModule();
        Injector injector = Guice.createInjector(module);
        Injector updatedInjector = Guice.createInjector(updatedModule);

        GuiceInjectorDependencyResolvingAdapter adapterToTest =
                new GuiceInjectorDependencyResolvingAdapter(() -> injector);
        GuiceInjectorDependencyResolvingAdapter updatedAdapter =
                new GuiceInjectorDependencyResolvingAdapter(() -> updatedInjector);

        ResourceSet resSet = new ResourceSetImpl();
        resSet.eAdapters().add(adapterToTest);
        Resource res = resSet.createResource(URI.createFileURI("file:/./test.xmi"));

        Container container = GuiceTestFactory.eINSTANCE.createContainer();
        res.getContents().add(container);

        Element element = GuiceTestFactory.eINSTANCE.createElement();
        container.getElements().add(element);
        
        resSet.eAdapters().remove(adapterToTest);
        resSet.eAdapters().add(updatedAdapter);
        element.callFoo();

        module.assertFooNotCalled();
        updatedModule.assertFooCalled();
    }

}
