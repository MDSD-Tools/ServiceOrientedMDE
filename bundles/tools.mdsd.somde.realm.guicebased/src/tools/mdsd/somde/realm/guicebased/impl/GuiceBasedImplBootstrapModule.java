package tools.mdsd.somde.realm.guicebased.impl;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import tools.mdsd.somde.realm.ServiceOrientedModelingRealm;
import tools.mdsd.somde.realm.guicebased.BootstrapModule;
import tools.mdsd.somde.realm.guicebased.ModuleImport;
import tools.mdsd.somde.realm.guicebased.ModuleProvider;
import tools.mdsd.somde.realm.guicebased.ModuleProviderRegistry;

public class GuiceBasedImplBootstrapModule extends AbstractModule implements BootstrapModule {

    @Override
    protected void configure() {
        bind(ServiceOrientedModelingRealm.class)
                .to(GuiceAwareServiceOrientedModelingRealmImpl.class);
        bind(ModuleProviderRegistry.class).to(ModuleProviderRegistryImpl.class);
        bind(new TypeLiteral<ModuleProvider<ModuleImport>>() {})
                .to(ReflectiveNameBasedModuleProvider.class);
    }


}
