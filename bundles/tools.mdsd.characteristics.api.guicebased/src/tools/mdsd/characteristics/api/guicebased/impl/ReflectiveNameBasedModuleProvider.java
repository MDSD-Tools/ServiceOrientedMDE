package tools.mdsd.characteristics.api.guicebased.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.google.inject.Module;

import tools.mdsd.characteristics.api.guicebased.ModuleProvider;
import tools.mdsd.characteristics.api.guicebased.guicebasedapi.ModuleImport;

public class ReflectiveNameBasedModuleProvider implements ModuleProvider<ModuleImport> {
	@Override
	public Module getModule(ModuleImport item) {
		String moduleName = item.getModuleFQN();
		try {
			Class<?> moduleClass = Class.forName(moduleName);
			Constructor<?>[] constructors = moduleClass.getConstructors();
			if (constructors.length > 1) {
				throw new IllegalArgumentException(
						String.format("Ambiguous constructors exist on module %s", moduleName));
			} else {
				Constructor<?> constructor = constructors[0];
				try {
					if (constructor.getParameterCount() == 0) {
						return (Module) constructor.newInstance();
					} else if (constructor.getParameterCount() == 1) {
						return (Module) constructor.newInstance(item);
					}
				} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
				throw new IllegalArgumentException(
						String.format("No compatible constructors found on module %s", moduleName));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Class<ModuleImport> getConfigurationType() {
		return ModuleImport.class;
	}
}

