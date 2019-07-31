package tools.mdsd.somde.realm.guicebased.impl;


import java.util.Optional;
import javax.inject.Inject;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;
import tools.mdsd.somde.realm.ConfigurationItem;
import tools.mdsd.somde.realm.DependencyResolvingAdapter;
import tools.mdsd.somde.realm.RealmPackage;
import tools.mdsd.somde.realm.guicebased.ModuleProviderRegistry;
import tools.mdsd.somde.realm.impl.ServiceOrientedModelingRealmImpl;

public class GuiceAwareServiceOrientedModelingRealmImpl extends ServiceOrientedModelingRealmImpl {

    private ModuleProviderRegistry moduleProviderRegistry;

    private class MonitorConfiguration extends EContentAdapter {
        @Override
        public void notifyChanged(Notification notification) {
            super.notifyChanged(notification);
            if (notification.getNotifier() == GuiceAwareServiceOrientedModelingRealmImpl.this) {
                // Handle the case that a completely new configuration item was added / removed
                if (notification.getFeature() == RealmPackage.eINSTANCE
                        .getServiceOrientedModelingRealm_Configuration()) {
                    recreateInjector();
                }
            } else {
                // Check if the change originated from the configuration elements
                EObject notifier = (EObject) notification.getNotifier();
                while (notifier.eContainer() != null) {
                    if (notifier.eContainer() == GuiceAwareServiceOrientedModelingRealmImpl.this) {
                        if (getConfiguration().contains(notifier)) {
                            recreateInjector();
                        }
                    }
                    notifier = notifier.eContainer();
                }
            }
        }
    }

    @Inject
    public GuiceAwareServiceOrientedModelingRealmImpl(ModuleProviderRegistry moduleProviderRegistry) {
        this.moduleProviderRegistry = moduleProviderRegistry;
        this.eAdapters().add(new MonitorConfiguration());
    }
    
    private Injector injector = null;
    private GuiceInjectorDependencyResolvingAdapter injectorAdapter = null;

    public Injector getInjector() {
        if (injector == null) {
            createInjector();
        }
        return injector;
    }

    @Override
    protected DependencyResolvingAdapter getResolvingAdapter() {
        if (injectorAdapter == null) {
            injectorAdapter = new GuiceInjectorDependencyResolvingAdapter(this::getInjector);
        }
        return injectorAdapter;
    }

    protected void recreateInjector() {
        boolean adapterWasSet = (injectorAdapter != null) && eResource() != null
                && eResource().getResourceSet() != null
                && eResource().getResourceSet().eAdapters().remove(injectorAdapter);
        injector = null;
        if (adapterWasSet) {
            eResource().getResourceSet().eAdapters().add(injectorAdapter);
        }

    }

    private synchronized void createInjector() {
        if (injector == null) {
            Module injectorModule = Modules.EMPTY_MODULE;
            for (ConfigurationItem item : this.getConfiguration()) {
                Optional<Module> module = moduleProviderRegistry.lookupModule(item);
                if (module.isPresent()) {
                    injectorModule = Modules.override(injectorModule).with(module.get());
                }
            }
            injector = Guice.createInjector(injectorModule);
        }
    }
}
