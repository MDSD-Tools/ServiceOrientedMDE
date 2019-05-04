package tools.mdsd.characteristics.api.guicebased;

import com.google.inject.Module;

import tools.mdsd.characteristics.api.ConfigurationItem;

public interface ModuleProvider<T extends ConfigurationItem> {
	
	Class<T> getConfigurationType();
	
	Module getModule(T configurationItem);
}
