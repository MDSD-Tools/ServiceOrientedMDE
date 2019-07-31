package tools.mdsd.characteristics.services.eclipse.guice;

import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import com.google.inject.AbstractModule;
import tools.mdsd.characteristics.services.ServiceTypeRegistryAccess;
import tools.mdsd.characteristics.services.eclipse.ExtensionRegistryBasedServiceTypeRegistryAccessImpl;

public class ServicesCommonsEclipseModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IExtensionRegistry.class).toInstance(Platform.getExtensionRegistry());
        bind(ServiceTypeRegistryAccess.class)
                .to(ExtensionRegistryBasedServiceTypeRegistryAccessImpl.class);
    }

}
