package tools.mdsd.characteristics.services.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import tools.mdsd.characteristics.properties.basic.EDataTypeBased;
import tools.mdsd.characteristics.services.api.StaticManifestationSerializationService;
import tools.mdsd.characteristics.valuetype.ValueType;
import tools.mdsd.characteristics.valuetype.ValuetypePackage;

public class EDataTypeBasedStaticManifestationSerializationService implements StaticManifestationSerializationService {
	
	@Override
	public String serializeStatic(ValueType valueType, Object manifestationObject) {
		EDataType dataType = valueType.getProperty(EDataTypeBased.class).getDataType();
		return dataType.getEPackage().getEFactoryInstance().convertToString(dataType, manifestationObject);
	}
	
	@Override
	public Object deserializeStatic(ValueType valueType, String serialized) {
		EDataType dataType = valueType.getProperty(EDataTypeBased.class).getDataType();
		return dataType.getEPackage().getEFactoryInstance().createFromString(dataType, serialized);
	}

    @Override
    public void registerService(
            ServiceRegistrationFacade<? super StaticManifestationSerializationService> facade) {
        facade.register(this, EClass.class, ValuetypePackage.eINSTANCE.getEDataTypeValueType());   
    }
}
