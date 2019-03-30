package tools.mdsd.characteristics.valuetype.properties.providers;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import tools.mdsd.characteristics.manifestation.Manifestation;
import tools.mdsd.characteristics.support.api.IValueTypePropertiesProvider;
import tools.mdsd.characteristics.valuetype.EnumValueType;
import tools.mdsd.characteristics.valuetype.ValueType;
import tools.mdsd.characteristics.valuetype.ValueTypeProperty;
import tools.mdsd.characteristics.valuetype.properties.BasicProperties.EDataTypeBased;
import tools.mdsd.characteristics.valuetype.properties.BasicProperties.FiniteSetValueType;
import tools.mdsd.characteristics.valuetype.util.ValuetypeSwitch;

@Component(scope=ServiceScope.BUNDLE)
public class EnumValueTypePropertiesProvider implements IValueTypePropertiesProvider {
	private static abstract class FiniteSetValueTypeProxy extends EObjectImpl implements FiniteSetValueType {}

	protected static ValuetypeSwitch<Stream<Class<? extends ValueTypeProperty>>> PROVIDED_PROPERTY_TYPES_SWITCH = 
			new ValuetypeSwitch<Stream<Class<? extends ValueTypeProperty>>>() {
		public Stream<Class<? extends ValueTypeProperty>> defaultCase(EObject object) {
			return Stream.empty();
		};
		
		public Stream<Class<? extends ValueTypeProperty>> caseEnumValueType(EnumValueType object) {
			return Stream.of(FiniteSetValueType.class);
		};
	};
	
	protected static ValuetypeSwitch<FiniteSetValueType> VALUE_TYPE_SWITCH = 
			new ValuetypeSwitch<FiniteSetValueType>() {
		public FiniteSetValueType defaultCase(EObject object) {
			return null;
		};
		
		public FiniteSetValueType caseEnumValueType(EnumValueType object) {
			return new FiniteSetValueTypeProxy() {

				@Override
				public EList<Manifestation> getValues() {
					return null;
				}

				@Override
				public EList<Manifestation> values() {
					return null;
				}

				@Override
				public Iterator<Manifestation> iterator() {
					return null;
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
			return Optional.of((P) VALUE_TYPE_SWITCH.doSwitch(type));
		}
		return Optional.empty();
	}
}
