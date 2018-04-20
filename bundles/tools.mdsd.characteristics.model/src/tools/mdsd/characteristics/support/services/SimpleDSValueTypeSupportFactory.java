package tools.mdsd.characteristics.support.services;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.FieldOption;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ServiceScope;

import tools.mdsd.characteristics.support.api.IValueTypePropertiesProvider;
import tools.mdsd.characteristics.support.api.IValueTypeSupport;
import tools.mdsd.characteristics.support.api.IValueTypeSupportFactory;
import tools.mdsd.characteristics.valuetype.ValueType;
import tools.mdsd.characteristics.valuetype.ValueTypeProperty;

@Component(scope=ServiceScope.SINGLETON)
public class SimpleDSValueTypeSupportFactory implements IValueTypeSupportFactory {
	
	@Reference(policy=ReferencePolicy.DYNAMIC,
			cardinality=ReferenceCardinality.MULTIPLE,
			fieldOption=FieldOption.UPDATE)
	protected Set<IValueTypePropertiesProvider> providers = new HashSet<>();

	@Override
	public IValueTypeSupport createValueTypeSupport(ResourceSet resourceSet) {
		return new CachingValueTypeSupport();
	}
	
	private class CachingValueTypeSupport implements IValueTypeSupport, IValueTypePropertiesProvider {
		private Map<ValueType, Map<Class<? extends ValueTypeProperty>, IValueTypePropertiesProvider>> mapping = new WeakHashMap<>();
		
		@Override
		public IValueTypePropertiesProvider getValueTypePropertiesProvider() {
			return this;
		}

		@Override
		public Set<Class<? extends ValueTypeProperty>> getProvidedPropertiesFor(ValueType valueType) {
			return mapping.computeIfAbsent(valueType, vt -> {
				Map<Class<? extends ValueTypeProperty>, IValueTypePropertiesProvider> vtmapping = 
						providers.stream().map(vtp -> Map.entry(vtp.getProvidedPropertiesFor(vt), vtp))
					.flatMap(tup -> tup.getKey().stream().map(cls -> Map.entry(cls, tup.getValue())))
					.collect(Collectors.toMap(v -> v.getKey(), v -> v.getValue()));
				
				return vtmapping;
			}).keySet();
		}

		@Override
		public <P extends ValueTypeProperty> Optional<P> getProperty(ValueType type, Class<P> propertyType) {
			return mapping.get(type).get(propertyType).getProperty(type, propertyType);
		}
		
	}

}
