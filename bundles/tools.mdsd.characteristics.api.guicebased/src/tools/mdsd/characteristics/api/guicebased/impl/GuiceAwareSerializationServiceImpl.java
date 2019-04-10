package tools.mdsd.characteristics.api.guicebased.impl;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;

import com.google.inject.Key;
import com.google.inject.TypeLiteral;

import tools.mdsd.characteristics.api.ServiceRegistration;
import tools.mdsd.characteristics.api.StaticManifestationSerializationService;
import tools.mdsd.characteristics.api.StaticManifestationSerializing;
import tools.mdsd.characteristics.api.guicebased.GuiceInjectorAware;
import tools.mdsd.characteristics.api.impl.SerializationServiceImpl;
import tools.mdsd.characteristics.valuetype.ValueType;

public class GuiceAwareSerializationServiceImpl extends SerializationServiceImpl implements GuiceInjectorAware.AwareEObject {
	HashMap<ValueType, StaticManifestationSerializing> valueTypeBasedSerializers;
	HashMap<Class<?>, StaticManifestationSerializing> propertyBasedSerializers;
	HashMap<EClassifier, StaticManifestationSerializing> eclassBasedSerializers;
	
	
	@Override
	public EList<StaticManifestationSerializationService> getStaticManifestationSerializationServices() {
		if (staticManifestationSerializationServices == null) {
			super.getStaticManifestationSerializationServices().addAll(
					this.resolveInstance(Key.get(new TypeLiteral<Set<StaticManifestationSerializationService>>() {})));
		}
		return super.getStaticManifestationSerializationServices();
	}
	
	@Override
	public String serializeStatic(ValueType valueType, Object manifestationObject) {
		if (valueTypeBasedSerializers == null) {
			initializeRegistry();
		}
		return getSerializer(valueType).serializeStatic(valueType, manifestationObject);
	}
	
	@Override
	public Object deserializeStatic(ValueType valueType, String serialized) {
		if (valueTypeBasedSerializers == null) {
			initializeRegistry();
		}
		return getSerializer(valueType).deserializeStatic(valueType, serialized);
	}
	
	protected StaticManifestationSerializing getSerializer(ValueType vt) {
		return valueTypeBasedSerializers.computeIfAbsent(vt, valt -> {
			Optional<Class<?>> property = propertyBasedSerializers.keySet().stream().filter(vt::hasProperty).findAny();
			return property.map(propertyBasedSerializers::get)
					.orElseGet(() -> valt.eClass().getEAllSuperTypes().stream().filter(eclassBasedSerializers::containsKey)
						.map(eclassBasedSerializers::get).findFirst().orElseGet(() -> null));
		});
	}
	
	protected synchronized void initializeRegistry() {
		if (valueTypeBasedSerializers == null) {
			valueTypeBasedSerializers = new HashMap<ValueType, StaticManifestationSerializing>();
			propertyBasedSerializers = new HashMap<Class<?>, StaticManifestationSerializing>();
			eclassBasedSerializers = new HashMap<EClassifier, StaticManifestationSerializing>();
			
			for (StaticManifestationSerializationService s: getStaticManifestationSerializationServices()) {
				for (ServiceRegistration r: s.getRegistrations()) {
					r.getForEClass().stream().forEach(e -> eclassBasedSerializers.put(e,  s));
					r.getForProperty().stream().forEach(p -> propertyBasedSerializers.put(p, s));
					r.getForType().stream().forEach(t -> valueTypeBasedSerializers.put(t, s));
				}
			}
		}
	}

}
