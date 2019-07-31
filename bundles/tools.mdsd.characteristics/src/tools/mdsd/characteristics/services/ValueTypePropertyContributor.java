package tools.mdsd.characteristics.services;

import java.util.Collection;
import tools.mdsd.characteristics.services.Service;
import tools.mdsd.characteristics.services.annotations.DispatchAll;
import tools.mdsd.characteristics.valuetype.ValueType;

public interface ValueTypePropertyContributor extends Service<ValueTypePropertyContributor> {
    
    @DispatchAll
    Collection<Class<?>> collectProperties(ValueType valueType);

}
