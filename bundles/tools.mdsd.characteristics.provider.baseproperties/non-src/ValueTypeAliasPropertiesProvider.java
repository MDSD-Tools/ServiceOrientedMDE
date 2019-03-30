package tools.mdsd.characteristics.valuetype.properties.providers;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import tools.mdsd.characteristics.support.ModelingRealmManagement;
import tools.mdsd.characteristics.support.api.IValueTypePropertiesProvider;
import tools.mdsd.characteristics.valuetype.ValueType;
import tools.mdsd.characteristics.valuetype.ValueTypeProperty;
import tools.mdsd.characteristics.valuetype.ValuetypePackage;

@Component(scope=ServiceScope.BUNDLE)
public class ValueTypeAliasPropertiesProvider implements IValueTypePropertiesProvider {
	
	@Override
	public Set<Class<? extends ValueTypeProperty>> getProvidedPropertiesFor(ValueType valueType) {
		if (ValuetypePackage.Literals.VALUE_TYPE_ALIAS.isInstance(valueType)) {
			ValueType originalValueType = (ValueType) valueType.eGet(ValuetypePackage.Literals.VALUE_TYPE_ALIAS__ORIGINAL_VALUE_TYPE);
			return ModelingRealmManagement.getInstance().getModelingRealm(valueType.eResource().getResourceSet())
				.getValueTypeSupport().getValueTypePropertiesProvider().getProvidedPropertiesFor(originalValueType);
		}
		return Collections.emptySet();
	}

	@Override
	public <P extends ValueTypeProperty> Optional<P> getProperty(ValueType type, Class<P> propertyType) {
		if (ValuetypePackage.Literals.VALUE_TYPE_ALIAS.isInstance(type)) {
			ValueType originalValueType = (ValueType) type.eGet(ValuetypePackage.Literals.VALUE_TYPE_ALIAS__ORIGINAL_VALUE_TYPE);
			return ModelingRealmManagement.getInstance().getModelingRealm(type.eResource().getResourceSet())
					.getValueTypeSupport().getValueTypePropertiesProvider().getProperty(originalValueType, propertyType);
		}
		return null;
	}

}
