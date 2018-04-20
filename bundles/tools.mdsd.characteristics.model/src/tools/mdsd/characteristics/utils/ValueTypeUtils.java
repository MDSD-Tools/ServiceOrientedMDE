package tools.mdsd.characteristics.utils;

import org.eclipse.emf.ecore.EDataType;

import tools.mdsd.characteristics.support.ModelingRealmManagement;
import tools.mdsd.characteristics.valuetype.ValueType;
import tools.mdsd.characteristics.valuetype.ValueTypeProperty;
import tools.mdsd.characteristics.valuetype.impl.ValueTypeImpl;;

public final class ValueTypeUtils {
	
	private ValueTypeUtils() {}
	
	public static <P extends ValueTypeProperty> P getProperty(ValueType vt, Class<P> propertyType) {
		return ModelingRealmManagement.getInstance().getModelingRealm(vt.eResource().getResourceSet()).getValueTypeSupport().getValueTypePropertiesProvider()
				.getProperty(vt, propertyType).orElse(null);
	}
	
	public static boolean isInstanceOfDataType(Object obj, EDataType dataType) {
		return dataType.getInstanceClass().isAssignableFrom(obj.getClass());
	}

	public static boolean adheresToValueType(ValueTypeImpl valueTypeImpl, Object instance) {
		throw new UnsupportedOperationException("This operation should be overridden by the subclass");
	}

	public static boolean hasProperty(ValueType vt, Class<? extends ValueTypeProperty> propertyType) {
		return ModelingRealmManagement.getInstance().getModelingRealm(vt.eResource().getResourceSet()).getValueTypeSupport().getValueTypePropertiesProvider()
				.getProvidedPropertiesFor(vt).contains(propertyType);
	}

}
