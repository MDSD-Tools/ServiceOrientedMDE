package tools.mdsd.characteristics.edit.support;

import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;

import tools.mdsd.characteristics.binding.BindingPackage;
import tools.mdsd.characteristics.binding.CharacteristicBinding;
import tools.mdsd.characteristics.characteristic.Characteristic;
import tools.mdsd.characteristics.characteristic.provider.CharacteristicBasedVirtualStructuralFeature;
import tools.mdsd.characteristics.manifestation.SingleValue;

public class CharacteristicBasedItemPropertyDescriptor extends ItemPropertyDescriptor {
	
	public static final CharacteristicBasedItemPropertyDescriptor create(AdapterFactory adapterFactory, 
			Characteristic characteristic) {
		ResourceLocator resourceLocator = null; //TODO lookup using adapter factory via Characteristic
		boolean isSettable = true; //Assumption for now
		Object staticImage = null; //Resolve appropriately
		EStructuralFeature feature = new CharacteristicBasedVirtualStructuralFeature(characteristic);
		return new CharacteristicBasedItemPropertyDescriptor(adapterFactory, resourceLocator, 
				characteristic.getEntityName(),
				characteristic.getEntityName(),  
				feature, isSettable, staticImage, characteristic);
	}
	
	protected final Characteristic characteristic;

	protected CharacteristicBasedItemPropertyDescriptor(AdapterFactory adapterFactory,
			ResourceLocator resourceLocator,
			String displayName, String description,
			EStructuralFeature feature,
			boolean isSettable,
			Object staticImage,
			Characteristic characteristic)
	{
		super(adapterFactory, resourceLocator, displayName, description, feature, isSettable, staticImage);
		this.characteristic = characteristic;
	}
	
	@Override
	protected Object getValue(EObject object, EStructuralFeature feature) {
		//TODO add appropriate converter here, to support non-single-value manifestations
		// Lookup should be possible via adapter factory
		
		Object list = object.eGet(
				BindingPackage.eINSTANCE.getCharacterizationContext_Bindings());
		if (list != null && list instanceof List<?>) {
			@SuppressWarnings("unchecked")
			List<CharacteristicBinding> bindings = (List<CharacteristicBinding>) list;
			
			return bindings.stream().filter(b -> b.getCharacteristic().equals(characteristic)).findAny()
				.map(CharacteristicBinding::getManifestation)
				.filter(SingleValue.class::isInstance) // TODO remove this assumption
				.map(SingleValue.class::cast)
				.map(SingleValue::getValue)
				.orElse(null);
		}
		return super.getValue(object, feature);
	}
}
