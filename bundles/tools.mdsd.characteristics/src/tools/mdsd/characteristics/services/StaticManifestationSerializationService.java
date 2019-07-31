package tools.mdsd.characteristics.services;

import tools.mdsd.characteristics.services.Service;
import tools.mdsd.characteristics.services.annotations.DispatchOnce;
import tools.mdsd.characteristics.valuetype.ValueType;

public interface StaticManifestationSerializationService extends Service<StaticManifestationSerializationService> {
    
    @DispatchOnce
    String serializeStatic(ValueType valueType, Object value);
    
    @DispatchOnce
    Object deserializeStatic(ValueType valueType, String storage);

}
