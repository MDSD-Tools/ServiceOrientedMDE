package tools.mdsd.characteristics.realm.guicebased;

import com.google.inject.Module;
import tools.mdsd.characteristics.realm.ConfigurationItem;

public interface ModuleProvider<T extends ConfigurationItem> {
	
	Class<T> getConfigurationType();
	
	Module getModule(T configurationItem);
}
