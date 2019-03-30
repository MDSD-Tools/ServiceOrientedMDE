/**
 */
package tools.mdsd.characteristics.instance.provider;


import java.util.Optional;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;

import tools.mdsd.characteristics.characteristic.Characteristic;
import tools.mdsd.characteristics.characteristic.Characterizing;
import tools.mdsd.characteristics.instance.CharacteristicManifestation;
import tools.mdsd.characteristics.instance.provider.CharacteristicManifestationItemProvider;
import tools.mdsd.characteristics.instance.util.InstanceSwitch;

/**
 * This is the item provider adapter for a {@link tools.mdsd.characteristics.instance.CharacteristicManifestation} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CharacteristicManifestationItemProviderCustom extends CharacteristicManifestationItemProvider
{
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CharacteristicManifestationItemProviderCustom(AdapterFactory adapterFactory)
	{
		super(adapterFactory);
	}

	

	protected InstanceSwitch<Optional<String>> LABEL_SWITCH = new InstanceSwitch<Optional<String>>() {
		@Override
		public Optional<String> caseCharacteristicManifestation(CharacteristicManifestation object) {
			return Optional.ofNullable(object.getCharacteristic())
					.map(Characterizing::computeCharacteristic)
					.map(Characteristic::getName);
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
