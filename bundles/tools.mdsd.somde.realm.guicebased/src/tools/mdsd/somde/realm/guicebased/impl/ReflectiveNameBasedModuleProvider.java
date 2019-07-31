package tools.mdsd.somde.realm.guicebased.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javax.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import tools.mdsd.somde.realm.guicebased.ModuleImport;
import tools.mdsd.somde.realm.guicebased.ModuleProvider;

public class ReflectiveNameBasedModuleProvider implements ModuleProvider<ModuleImport> {
    
    @Inject
    Injector injector;
    
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
                    Module result = null;
                    if (constructor.getParameterCount() == 0) {
                        result = (Module) constructor.newInstance();
                    } else if (constructor.getParameterCount() == 1) {
                        result = (Module) constructor.newInstance(item);
                    }
                    injector.injectMembers(result);
                } catch (InstantiationException | IllegalAccessException
                        | InvocationTargetException e) {
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

