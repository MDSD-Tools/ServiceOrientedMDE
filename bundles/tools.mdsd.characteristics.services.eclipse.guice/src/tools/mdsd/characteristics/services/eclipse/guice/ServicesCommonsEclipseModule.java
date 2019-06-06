package tools.mdsd.characteristics.services.eclipse.guice;

import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import tools.mdsd.characteristics.services.ServiceTypeRegistryAccess;
import tools.mdsd.characteristics.services.eclipse.ExtensionRegistryBasedServiceTypeRegistryAccessImpl;
import tools.mdsd.characteristics.services.guice.ServicesCommonsModule;

public class ServicesCommonsEclipseModule extends ServicesCommonsModule {
    
    @Override
    protected void configure() {
        super.configure();
        bind(IExtensionRegistry.class).toInstance(Platform.getExtensionRegistry());
        bind(ServiceTypeRegistryAccess.class).to(ExtensionRegistryBasedServiceTypeRegistryAccessImpl.class);
    }

}
