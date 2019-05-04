package tools.mdsd.characteristics.api.guicebased.impl;

import java.util.Optional;

import org.eclipse.emf.ecore.EObject;

import com.google.inject.Module;

import tools.mdsd.characteristics.api.ConfigurationItem;
import tools.mdsd.characteristics.api.guicebased.guicebasedapi.ModuleImport;
import tools.mdsd.characteristics.api.guicebased.guicebasedapi.util.GuiceBasedAPISwitch;

public class ModuleProviderRegistry extends GuiceBasedAPISwitch<Optional<Module>> {
	
	@Override
	public Optional<Module> defaultCase(EObject object) {
		throw new UnsupportedOperationException("The registry can only handle configuration items.");
	}
	
	@Override
	public Optional<Module> caseConfigurationItem(ConfigurationItem object) {
		// TODO create extension point and check if suitable provider is registered
		return Optional.empty();
	}
	
	@Override
	public Optional<Module> caseModuleImport(ModuleImport object) {
		return Optional.of((new ReflectiveNameBasedModuleProvider()).getModule(object));
	}

}
