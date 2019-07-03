package tools.mdsd.characteristics.realm.guicebased;

import java.util.Collection;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;

public class Bootstrap {
    private static Injector bootstrapper = null;
    
    private static class BootstrapModule extends AbstractModule {}
    
    public static synchronized void initialize(Collection<Module> bootstrapModules) {
        Module bootstrapModule = new BootstrapModule();
        for (var module : bootstrapModules) {
            bootstrapModule = Modules.override(bootstrapModule).with(module);
        }
        bootstrapper = Guice.createInjector(bootstrapModules);
    }
    
    public static Injector getBootstrappingInjector() {
        if (bootstrapper == null) {
            throw new IllegalStateException("The Characteristics Bootstrapper has not been initialized."
                    + "The initialization has to be done manually, if not run on the Eclipse Platform");
        }
        return bootstrapper;
    }
}
