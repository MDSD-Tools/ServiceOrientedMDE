package tools.mdsd.characteristics.services.api;

import tools.mdsd.characteristics.services.Service;
import tools.mdsd.characteristics.services.annotations.DispatchOnce;
import tools.mdsd.characteristics.valuetype.ValueType;

public interface ValueTypePropertyProvider extends Service<ValueTypePropertyProvider> {
    @DispatchOnce
    <T> T getProperty(Class<T> propertyType, ValueType valueType);

}
