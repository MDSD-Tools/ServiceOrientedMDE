package tools.mdsd.characteristics.realm.guicebased;

import com.google.inject.AbstractModule;
import tools.mdsd.characteristics.services.eclipse.guice.ServicesCommonsEclipseModule;
import tools.mdsd.characteristics.services.guice.ServicesCommonsModule;

public class EclipseBootstrapModule extends AbstractModule implements BootstrapModule {
    
    @Override
    protected void configure() {
        binder().install(new ServicesCommonsModule());
        binder().install(new ServicesCommonsEclipseModule());
    }

}
