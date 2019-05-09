/**
 */
package tools.mdsd.characteristics.characteristic.provider;


import org.eclipse.emf.common.notify.AdapterFactory;

import tools.mdsd.characteristics.characteristic.Characteristic;

/**
 * This is the item provider adapter for a {@link tools.mdsd.characteristics.characteristic.Characteristic} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CharacteristicItemProvider extends CharacteristicItemProviderGen
{
	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public CharacteristicItemProvider(AdapterFactory adapterFactory)
	{
		super(adapterFactory);
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object)
	{
		String label = ((Characteristic)object).getEntityName();
		return label == null || label.length() == 0 ?
			getString("_UI_Characteristic_type") :
			getString("_UI_Characteristic_label") + " " + label;
	}


}
