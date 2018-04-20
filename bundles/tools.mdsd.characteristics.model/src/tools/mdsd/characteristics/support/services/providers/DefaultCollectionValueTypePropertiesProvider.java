package tools.mdsd.characteristics.support.services.providers;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import tools.mdsd.characteristics.support.api.IValueTypePropertiesProvider;
import tools.mdsd.characteristics.valuetype.CollectionValueType;
import tools.mdsd.characteristics.valuetype.ValueType;
import tools.mdsd.characteristics.valuetype.ValueTypeProperty;
import tools.mdsd.characteristics.valuetype.properties.BasicProperties.ParametricValueType;
import tools.mdsd.characteristics.valuetype.util.ValuetypeSwitch;

@Component(scope=ServiceScope.BUNDLE)
public class DefaultCollectionValueTypePropertiesProvider implements IValueTypePropertiesProvider {
	protected static ValuetypeSwitch<Stream<Class<? extends ValueTypeProperty>>> PROVIDED_PROPERTY_TYPES_SWITCH = 
			new ValuetypeSwitch<Stream<Class<? extends ValueTypeProperty>>>() {
		public Stream<Class<? extends ValueTypeProperty>> defaultCase(EObject object) {
			return Stream.empty();
		};
		
		public Stream<Class<? extends ValueTypeProperty>> caseCollectionValueType(CollectionValueType object) {
			return Stream.of(
				tools.mdsd.characteristics.valuetype.properties.BasicProperties.CollectionValueType.class,
				ParametricValueType.class);
		};
	};

	@Override
	public Set<Class<? extends ValueTypeProperty>> getProvidedPropertiesFor(ValueType valueType) {
		return PROVIDED_PROPERTY_TYPES_SWITCH.doSwitch(valueType).collect(Collectors.toSet());
	}

	@Override
	public <P extends ValueTypeProperty> Optional<P> getProperty(ValueType type, Class<P> propertyType) {
		// TODO Auto-generated method stub
		return null;
	}

}
