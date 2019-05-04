package tools.mdsd.characteristics.api.guicebased.impl;


import java.util.Optional;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;

import tools.mdsd.characteristics.api.ApiPackage;
import tools.mdsd.characteristics.api.ConfigurationItem;
import tools.mdsd.characteristics.api.SerializationService;
import tools.mdsd.characteristics.api.ValueTypePropertiesService;
import tools.mdsd.characteristics.api.guicebased.ApiFactoryModule;
import tools.mdsd.characteristics.api.guicebased.GuiceInjectorAware;
import tools.mdsd.characteristics.api.impl.CharacteristicsModelingRealmImpl;

public class GuiceAwareCharacteristicsModelingRealmImpl
		extends CharacteristicsModelingRealmImpl implements GuiceInjectorAware.AwareEObject {
	
	private class MonitorConfiguration extends EContentAdapter {
		@Override
		public void notifyChanged(Notification notification) {
			super.notifyChanged(notification);
			if (notification.getNotifier() == GuiceAwareCharacteristicsModelingRealmImpl.this) {
				// Handle the case that a completely new configuration item was added / removed
				if (notification.getFeature() == ApiPackage.eINSTANCE.getCharacteristicsModelingRealm_Configuration()) {
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
	
	
	@Override
	public ValueTypePropertiesService getValueTypePropertiesService() {
		if (valueTypePropertiesService == null) {
			this.eSet(ApiPackage.eINSTANCE.getCharacteristicsModelingRealm_ValueTypePropertiesService(), 
					resolveInstance(ValueTypePropertiesService.class));
		}
		return super.getValueTypePropertiesService();
	}
	
	@Override
	public SerializationService getSerializationService() {
		if (serializationService == null) {
			this.eSet(ApiPackage.eINSTANCE.getCharacteristicsModelingRealm_SerializationService(), 
					resolveInstance(SerializationService.class));
		}
		return super.getSerializationService();
	}
}
