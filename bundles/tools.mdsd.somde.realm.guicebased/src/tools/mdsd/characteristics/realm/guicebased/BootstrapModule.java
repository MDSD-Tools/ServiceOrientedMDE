package tools.mdsd.characteristics.realm.guicebased;

import java.util.Collection;
import java.util.ServiceLoader;
import com.google.common.collect.Lists;
import com.google.inject.Module;
import com.google.inject.util.Modules;

/**
 * This is purely a marker interface as point of registration for bootstrapping modules using
 * OSGI-independent Java Service Provider Specification.
 * 
 * @author krach
 *
 */
public interface BootstrapModule extends Module {
    
    static Module getBootstrapModule() {
        Collection<BootstrapModule> modules = Lists.newArrayList(ServiceLoader.load(BootstrapModule.class));
        return Modules.combine(modules);
    }

}
