/**
 */
package tools.mdsd.characteristics.binding.provider;


import java.util.Optional;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;

import tools.mdsd.characteristics.binding.CharacteristicBinding;
import tools.mdsd.characteristics.binding.util.BindingSwitch;
import tools.mdsd.characteristics.characteristic.Characteristic;
import tools.mdsd.characteristics.characteristic.Characterizing;
/**
 * This is the item provider adapter for a {@link tools.mdsd.characteristics.instance.CharacteristicManifestation} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 */
public class CharacteristicBindingItemProvider extends CharacteristicBindingItemProviderGen
{
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public CharacteristicBindingItemProvider(AdapterFactory adapterFactory)
	{
		super(adapterFactory);
	}

	

	protected BindingSwitch<Optional<String>> LABEL_SWITCH = new BindingSwitch<Optional<String>>() {
		@Override
		public Optional<String> caseCharacteristicBinding(CharacteristicBinding object) {
			return Optional.ofNullable(object.getCharacteristic())
					.map(Characterizing::computeCharacteristic)
					.map(Characteristic::getEntityName);
		}
		
		@Override
		public Optional<String> defaultCase(org.eclipse.emf.ecore.EObject object) {
			return Optional.empty();
		};
		
	};
	
	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object)
	{
		return Optional.of(object)
			.filter(EObject.class::isInstance)
			.map(EObject.class::cast)
			.flatMap(LABEL_SWITCH::doSwitch)
			.orElseGet(() -> getString("_UI_CharacteristicManifestation_type"));
	}
}
