package tools.mdsd.characteristics.utils;

import java.util.Optional;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.resource.Resource;

import tools.mdsd.characteristics.CharacteristicsConstants;
import tools.mdsd.characteristics.api.CharacteristicsModelingRealm;
import tools.mdsd.characteristics.valuetype.ValueType;
import tools.mdsd.characteristics.valuetype.impl.ValueTypeImpl;;

public final class ValueTypeUtils {
	
	private ValueTypeUtils() {}
	
	public static <P> P getProperty(ValueType vt, Class<P> propertyType) {
		return Optional.ofNullable(vt.eResource().getResourceSet().getResource(CharacteristicsConstants.REALM_INSTANCE_OR_DEFAULT, true))
				.map(Resource::getContents).filter(c -> !c.isEmpty()).map(c -> c.get(0))
				.map(CharacteristicsModelingRealm.class::cast)
				.map(CharacteristicsModelingRealm::getValueTypePropertiesService)
				.flatMap(vtps -> vtps.getProperty(propertyType, vt))
				.orElse(null);
	}
	
	public static boolean isInstanceOfDataType(Object obj, EDataType dataType) {
		return dataType.getInstanceClass().isAssignableFrom(obj.getClass());
	}

	public static boolean adheresToValueType(ValueTypeImpl valueTypeImpl, Object instance) {
		throw new UnsupportedOperationException("This operation should be overridden by the subclass");
	}

	public static boolean hasProperty(ValueType vt, Class<?> propertyType) {
		
		return Optional.ofNullable(vt.eResource().getResourceSet().getResource(CharacteristicsConstants.REALM_INSTANCE_OR_DEFAULT, true))
			.map(Resource::getContents).filter(c -> !c.isEmpty()).map(c -> c.get(0))
			.map(CharacteristicsModelingRealm.class::cast)
			.map(CharacteristicsModelingRealm::getValueTypePropertiesService)
			.flatMap(vtps -> vtps.getProperty(propertyType, vt))
			.isPresent();
		
	}

}
