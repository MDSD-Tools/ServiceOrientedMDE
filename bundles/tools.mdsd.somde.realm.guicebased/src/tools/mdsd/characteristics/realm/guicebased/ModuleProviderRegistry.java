package tools.mdsd.characteristics.realm.guicebased;

import com.google.inject.Module;
import java.util.Optional;
import tools.mdsd.characteristics.realm.ConfigurationItem;

public interface ModuleProviderRegistry {
    
    Optional<Module> lookupModule(ConfigurationItem item);

}
