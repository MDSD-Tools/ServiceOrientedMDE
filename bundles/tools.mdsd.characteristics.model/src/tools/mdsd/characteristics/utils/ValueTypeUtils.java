package tools.mdsd.characteristics.utils;

import org.eclipse.emf.ecore.EDataType;

import tools.mdsd.characteristics.valuetype.ValueType;
import tools.mdsd.characteristics.valuetype.impl.ValueTypeImpl;;

public final class ValueTypeUtils {
	
	private ValueTypeUtils() {}
	
	public static <P> P getProperty(ValueType vt, Class<P> propertyType) {
		return CharacteristicsAPIAware.lookupCharacteristicsModelingRealm(vt)
				.getValueTypePropertiesService()
				.getProperty(propertyType, vt)
				.orElse(null);
	}
	
	public static boolean isInstanceOfDataType(Object obj, EDataType dataType) {
		return dataType.isInstance(obj);
	}

	public static boolean adheresToValueType(ValueTypeImpl valueTypeImpl, Object instance) {
		throw new UnsupportedOperationException("This operation should be overridden by the subclass");
	}

	public static boolean hasProperty(ValueType vt, Class<?> propertyType) {
		return CharacteristicsAPIAware.lookupCharacteristicsModelingRealm(vt)
				.getValueTypePropertiesService()
				.getProperty(propertyType, vt).isPresent();
	}

}
