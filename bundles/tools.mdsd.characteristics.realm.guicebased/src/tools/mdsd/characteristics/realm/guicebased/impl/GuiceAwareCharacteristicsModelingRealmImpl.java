package tools.mdsd.characteristics.realm.guicebased.impl;


import java.util.Optional;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;
import tools.mdsd.characteristics.realm.ConfigurationItem;
import tools.mdsd.characteristics.realm.RealmPackage;
import tools.mdsd.characteristics.realm.guicebased.ApiFactoryModule;
import tools.mdsd.characteristics.realm.guicebased.GuiceInjectorAware;
import tools.mdsd.characteristics.realm.impl.CharacteristicsModelingRealmImpl;

public class GuiceAwareCharacteristicsModelingRealmImpl
		extends CharacteristicsModelingRealmImpl implements GuiceInjectorAware.AwareEObject {
	
	private class MonitorConfiguration extends EContentAdapter {
		@Override
		public void notifyChanged(Notification notification) {
			super.notifyChanged(notification);
			if (notification.getNotifier() == GuiceAwareCharacteristicsModelingRealmImpl.this) {
				// Handle the case that a completely new configuration item was added / removed
				if (notification.getFeature() == RealmPackage.eINSTANCE.getCharacteristicsModelingRealm_Configuration()) {
					injector = null;
				}
			} else {
				// Check if the change originated from the configuration elements
				EObject notifier = (EObject) notification.getNotifier();
				while (notifier.eContainer() != null) {
					if (notifier.eContainer() == GuiceAwareCharacteristicsModelingRealmImpl.this) {
						if (getConfiguration().contains(notifier)) {
							injector = null;
						}
					}
					notifier = notifier.eContainer();
				}
			}
		}
	}
	
	public GuiceAwareCharacteristicsModelingRealmImpl() {  
		this.eAdapters().add(new MonitorConfiguration());
	}
	
	private Injector injector = null;
	
	@Override
	public Injector getInjector() {
		if (injector == null) {
			createInjector();
		}
		return injector;
	}
	
	private synchronized void createInjector() {
		if (injector == null) {
			Module injectorModule = new ApiFactoryModule();
			for (ConfigurationItem item : this.getConfiguration()) {
				Optional<Module> module = (new ModuleProviderRegistry()).doSwitch(item);
				if (module.isPresent()) {
					injectorModule = Modules.override(injectorModule).with(module.get());
				}
			}
			injector = Guice.createInjector(injectorModule); 
		}
	}
}
