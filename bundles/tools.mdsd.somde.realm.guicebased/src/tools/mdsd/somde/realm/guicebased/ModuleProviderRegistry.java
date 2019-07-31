package tools.mdsd.somde.realm.guicebased;

import com.google.inject.Module;
import tools.mdsd.somde.realm.ConfigurationItem;
import java.util.Optional;

public interface ModuleProviderRegistry {
    
    Optional<Module> lookupModule(ConfigurationItem item);

}
