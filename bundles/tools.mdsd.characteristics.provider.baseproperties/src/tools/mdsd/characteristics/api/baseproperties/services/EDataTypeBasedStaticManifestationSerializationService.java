package tools.mdsd.characteristics.api.baseproperties.services;

import java.util.Collections;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EDataType;

import tools.mdsd.characteristics.api.ApiFactory;
import tools.mdsd.characteristics.api.ServiceRegistration;
import tools.mdsd.characteristics.api.impl.StaticManifestationSerializationServiceImpl;
import tools.mdsd.characteristics.valuetype.ValueType;
import tools.mdsd.characteristics.valuetype.properties.BasicProperties.EDataTypeBased;

public class EDataTypeBasedStaticManifestationSerializationService extends StaticManifestationSerializationServiceImpl {
	
	@Override
	public EList<ServiceRegistration> getRegistrations() {
		ServiceRegistration reg = ApiFactory.eINSTANCE.createServiceRegistration();
		reg.getForProperty().add(EDataTypeBased.class);
		return new BasicEList<ServiceRegistration>(Collections.singletonList(reg));
	}
	
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

}
