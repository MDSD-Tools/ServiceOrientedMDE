package tools.mdsd.characteristics.realm.guicebased.impl;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import tools.mdsd.characteristics.realm.CharacteristicsModelingRealm;
import tools.mdsd.characteristics.realm.guicebased.BootstrapModule;
import tools.mdsd.characteristics.realm.guicebased.ModuleImport;
import tools.mdsd.characteristics.realm.guicebased.ModuleProvider;
import tools.mdsd.characteristics.realm.guicebased.ModuleProviderRegistry;

public class GuiceBasedImplBootstrapModule extends AbstractModule implements BootstrapModule {

    @Override
    protected void configure() {
        bind(CharacteristicsModelingRealm.class)
                .to(GuiceAwareCharacteristicsModelingRealmImpl.class);
        bind(ModuleProviderRegistry.class).to(ModuleProviderRegistryImpl.class);
        bind(new TypeLiteral<ModuleProvider<ModuleImport>>() {})
                .to(ReflectiveNameBasedModuleProvider.class);
    }


}
