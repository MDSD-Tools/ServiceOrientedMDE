package tools.mdsd.characteristics.manifestation.provider;

import java.util.Collection;
import java.util.Optional;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;

import tools.mdsd.characteristics.valuetype.ValueType;
import tools.mdsd.characteristics.valuetype.properties.BasicProperties.FiniteSetValueType;

public class ValueTypeAwareManifestationObjectItemPropertyDescriptor extends ItemPropertyDescriptor {
	
	protected EStructuralFeature valueTypeFeature;

	public ValueTypeAwareManifestationObjectItemPropertyDescriptor(AdapterFactory adapterFactory,
			ResourceLocator resourceLocator, String displayName, String description, EStructuralFeature valueFeature,
			EStructuralFeature valueTypeFeature, boolean isSettable, String category, String[] filterFlags) {
		super(adapterFactory, resourceLocator, displayName, description, valueFeature, isSettable, category, filterFlags);
		this.valueTypeFeature = valueTypeFeature;
	}
	
	
	@Override
	public Collection<?> getChoiceOfValues(Object object) {
		return getValueType(object).filter(vt -> vt.hasProperty(FiniteSetValueType.class))
				.map(vt -> vt.getProperty(FiniteSetValueType.class)).map(p -> p.getValues()).orElse(null);
	}
	
	@Override
	public Object getFeature(Object object) {
		return getValueType(object).map(vt -> new EStructuralFeatureTypeDecorator(vt, super.feature))
			.orElse(null);
	}
	
	protected Optional<ValueType> getValueType(Object object) {
		if (!(object instanceof EObject)) return Optional.empty();
		
		
		Object vtObject = ((EObject) object).eGet(valueTypeFeature);
		
		if (vtObject == null || !(vtObject instanceof ValueType)) {
			return Optional.empty();
		}
		
		return Optional.of((ValueType) vtObject);
	}

}
