package tools.mdsd.somde.realm.guicebased;

import com.google.inject.AbstractModule;
import tools.mdsd.somde.services.eclipse.guice.ServicesCommonsEclipseModule;
import tools.mdsd.somde.services.guice.ServicesCommonsModule;
import tools.mdsd.somde.realm.guicebased.BootstrapModule;

public class EclipseBootstrapModule extends AbstractModule implements BootstrapModule {
    
    @Override
    protected void configure() {
        binder().install(new ServicesCommonsModule());
        binder().install(new ServicesCommonsEclipseModule());
    }

}
