package tools.mdsd.characteristics.realm.guicebased.impl;

import java.util.Optional;
import javax.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import com.google.inject.Module;
import tools.mdsd.characteristics.realm.ConfigurationItem;
import tools.mdsd.characteristics.realm.guicebased.ModuleImport;
import tools.mdsd.characteristics.realm.guicebased.ModuleProvider;
import tools.mdsd.characteristics.realm.guicebased.ModuleProviderRegistry;
import tools.mdsd.characteristics.realm.guicebased.util.GuiceBasedRealmSwitch;

public class ModuleProviderRegistryImpl extends GuiceBasedRealmSwitch<Optional<Module>>
        implements ModuleProviderRegistry {
    
    @Inject
    ModuleProvider<ModuleImport> importModuleProvider;

    @Override
    public Optional<Module> lookupModule(ConfigurationItem item) {
        return this.doSwitch(item);
    }
    
    @Override
    public Optional<Module> defaultCase(EObject object) {
        throw new UnsupportedOperationException(
                "The registry can only handle configuration items.");
    }

    @Override
    public Optional<Module> caseConfigurationItem(ConfigurationItem object) {
        // TODO create extension point and check if suitable provider is registered
        return Optional.empty();
    }

    @Override
    public Optional<Module> caseModuleImport(ModuleImport object) {
        return Optional.of(importModuleProvider.getModule(object));
    }

    


}
