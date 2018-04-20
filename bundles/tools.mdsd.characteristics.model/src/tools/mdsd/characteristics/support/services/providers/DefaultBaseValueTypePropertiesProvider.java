package tools.mdsd.characteristics.support.services.providers;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import tools.mdsd.characteristics.support.api.IValueTypePropertiesProvider;
import tools.mdsd.characteristics.valuetype.EDataTypeValueType;
import tools.mdsd.characteristics.valuetype.ValueType;
import tools.mdsd.characteristics.valuetype.ValueTypeProperty;
import tools.mdsd.characteristics.valuetype.properties.BasicProperties.EDataTypeBased;
import tools.mdsd.characteristics.valuetype.util.ValuetypeSwitch;

@Component(scope=ServiceScope.BUNDLE)
public class DefaultBaseValueTypePropertiesProvider implements IValueTypePropertiesProvider {
	private static abstract class EDataTypeBasedProxy extends EObjectImpl implements EDataTypeBased {}

	protected static ValuetypeSwitch<Stream<Class<? extends ValueTypeProperty>>> PROVIDED_PROPERTY_TYPES_SWITCH = 
			new ValuetypeSwitch<Stream<Class<? extends ValueTypeProperty>>>() {
		public Stream<Class<? extends ValueTypeProperty>> defaultCase(EObject object) {
			return Stream.empty();
		};
		
		public Stream<Class<? extends ValueTypeProperty>> caseEDataTypeValueType(EDataTypeValueType object) {
			return Stream.of(EDataTypeBased.class);
		};
	};
	
	protected static ValuetypeSwitch<EDataTypeBased> EDATA_TYPE_BASED_VALUE_TYPE_SWITCH = 
			new ValuetypeSwitch<EDataTypeBased>() {
		public EDataTypeBased defaultCase(EObject object) {
			return null;
		};
		
		public EDataTypeBased caseEDataTypeValueType(EDataTypeValueType object) {
			return new EDataTypeBasedProxy() {

				@Override
				public EDataType getDataType() {
					return object.getBaseType();
				}
				
			};
		};
	};

	@Override
	public Set<Class<? extends ValueTypeProperty>> getProvidedPropertiesFor(ValueType valueType) {
		return PROVIDED_PROPERTY_TYPES_SWITCH.doSwitch(valueType).collect(Collectors.toSet());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <P extends ValueTypeProperty> Optional<P> getProperty(ValueType type, Class<P> propertyType) {
		if (propertyType == EDataTypeBased.class) {
			return Optional.of((P) EDATA_TYPE_BASED_VALUE_TYPE_SWITCH.doSwitch(type));
		}
		return null;
	}
}
