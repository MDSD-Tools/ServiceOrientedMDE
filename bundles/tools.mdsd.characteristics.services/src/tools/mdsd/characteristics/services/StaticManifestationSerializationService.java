package tools.mdsd.characteristics.services;

import tools.mdsd.characteristics.valuetype.ValueType;

public interface StaticManifestationSerializationService extends Service<StaticManifestationSerializationService> {
    
    String serializeStatic(ValueType valueType, Object value);
    
    Object deserializeStatic(ValueType valueType, String storage);

}
