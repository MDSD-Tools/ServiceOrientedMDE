package tools.mdsd.characteristics.api.guicebased.impl;

import java.util.Optional;
import java.util.Set;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;

import com.google.inject.Key;
import com.google.inject.TypeLiteral;

import tools.mdsd.characteristics.api.ServiceRegistration;
import tools.mdsd.characteristics.api.ValueTypePropertiesProvider;
import tools.mdsd.characteristics.api.guicebased.GuiceInjectorAware;
import tools.mdsd.characteristics.api.impl.ValueTypePropertiesServiceImpl;
import tools.mdsd.characteristics.valuetype.ValueType;

public class GuiceValueTypePropertiesServiceImpl extends ValueTypePropertiesServiceImpl implements GuiceInjectorAware.AwareEObject {
	
	@Override
	public EList<ServiceRegistration> getRegistrations() {
		return ECollections.emptyEList();
	}
	
	@Override
	public EList<ValueTypePropertiesProvider> getValueTypePropertiesProvider() {
		if (this.valueTypePropertiesProvider == null) {
			super.getValueTypePropertiesProvider().addAll(
				this.resolveInstance(Key.get(new TypeLiteral<Set<ValueTypePropertiesProvider>>() {})));
		}
		return super.getValueTypePropertiesProvider();
	}
	
	@Override
	public <T> Optional<T> getProperty(Class<T> propertyType, ValueType valueType) {
		return this.getValueTypePropertiesProvider().stream()
				.map(vt -> vt.getProperty(propertyType, valueType))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.findAny();
	}


}
