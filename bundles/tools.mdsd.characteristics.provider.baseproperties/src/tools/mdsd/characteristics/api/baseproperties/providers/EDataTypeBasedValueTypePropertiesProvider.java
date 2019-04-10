package tools.mdsd.characteristics.api.baseproperties.providers;

import java.util.Optional;

import org.eclipse.emf.ecore.EObject;

import tools.mdsd.characteristics.api.impl.ValueTypePropertiesProviderImpl;
import tools.mdsd.characteristics.valuetype.EDataTypeValueType;
import tools.mdsd.characteristics.valuetype.ValueType;
import tools.mdsd.characteristics.valuetype.properties.BasicProperties.EDataTypeBased;
import tools.mdsd.characteristics.valuetype.util.ValuetypeSwitch;

public class EDataTypeBasedValueTypePropertiesProvider extends ValueTypePropertiesProviderImpl {
	
	protected static ValuetypeSwitch<EDataTypeBased> EDATA_TYPE_BASED_VALUE_TYPE_SWITCH = 
			new ValuetypeSwitch<EDataTypeBased>() {
		public EDataTypeBased defaultCase(EObject object) {
			return null;
		};
		
		public EDataTypeBased caseEDataTypeValueType(EDataTypeValueType object) {
			return () -> object.getBaseType();
		};
	};
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> Optional<T> getProperty(Class<T> propertyType, ValueType valueType) {
		if (propertyType == EDataTypeBased.class) {
			return Optional.of((T) EDATA_TYPE_BASED_VALUE_TYPE_SWITCH.doSwitch(valueType));
		}
		return Optional.empty();
	}
}
