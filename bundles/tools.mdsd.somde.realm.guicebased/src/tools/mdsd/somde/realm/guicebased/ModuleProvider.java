package tools.mdsd.somde.realm.guicebased;

import com.google.inject.Module;
import tools.mdsd.somde.realm.ConfigurationItem;

public interface ModuleProvider<T extends ConfigurationItem> {
	
	Class<T> getConfigurationType();
	
	Module getModule(T configurationItem);
}
