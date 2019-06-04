package tools.mdsd.characteristics.services.api;

import tools.mdsd.characteristics.services.Service;
import tools.mdsd.characteristics.valuetype.ValueType;

public interface StaticManifestationSerializationService extends Service<StaticManifestationSerializationService> {
    
    String serializeStatic(ValueType valueType, Object value);
    
    Object deserializeStatic(ValueType valueType, String storage);

}
