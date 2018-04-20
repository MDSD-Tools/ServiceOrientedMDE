package tools.mdsd.characteristics.support.api;

import java.util.Optional;
import java.util.Set;

import tools.mdsd.characteristics.valuetype.ValueType;
import tools.mdsd.characteristics.valuetype.ValueTypeProperty;

public interface IValueTypePropertiesProvider {

	Set<Class<? extends ValueTypeProperty>> getProvidedPropertiesFor(ValueType valueType);
	
	<P extends ValueTypeProperty> Optional<P> getProperty(ValueType type, Class<P> propertyType);
	
}
