/**
 */
package tools.mdsd.characteristics.characteristic.provider;


import de.uka.ipd.sdq.identifier.provider.IdentifierItemProvider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IChildCreationExtender;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import tools.mdsd.characteristics.characteristic.Characteristic;
import tools.mdsd.characteristics.characteristic.CharacteristicFactory;
import tools.mdsd.characteristics.characteristic.CharacteristicPackage;

import tools.mdsd.characteristics.instance.InstanceFactory;
import tools.mdsd.characteristics.instance.InstancePackage;

/**
 * This is the item provider adapter for a {@link tools.mdsd.characteristics.characteristic.Characteristic} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CharacteristicItemProvider extends IdentifierItemProvider
{
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CharacteristicItemProvider(AdapterFactory adapterFactory)
	{
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
	{
		if (itemPropertyDescriptors == null)
		{
			super.getPropertyDescriptors(object);

			addCharacteristicPropertyDescriptor(object);
			addManifestationsPropertyDescriptor(object);
			addValueTypePropertyDescriptor(object);
			addNamePropertyDescriptor(object);
			addRefinesCharacteristicsPropertyDescriptor(object);
			addCharacteristicsPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Characteristic feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCharacteristicPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Characterizing_characteristic_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Characterizing_characteristic_feature", "_UI_Characterizing_type"),
				 CharacteristicPackage.Literals.CHARACTERIZING__CHARACTERISTIC,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Manifestations feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addManifestationsPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Characterizable_manifestations_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Characterizable_manifestations_feature", "_UI_Characterizable_type"),
				 InstancePackage.Literals.CHARACTERIZABLE__MANIFESTATIONS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Value Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addValueTypePropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Characteristic_valueType_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Characteristic_valueType_feature", "_UI_Characteristic_type"),
				 CharacteristicPackage.Literals.CHARACTERISTIC__VALUE_TYPE,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Characteristic_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Characteristic_name_feature", "_UI_Characteristic_type"),
				 CharacteristicPackage.Literals.CHARACTERISTIC__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Refines Characteristics feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRefinesCharacteristicsPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Characteristic_refinesCharacteristics_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Characteristic_refinesCharacteristics_feature", "_UI_Characteristic_type"),
				 CharacteristicPackage.Literals.CHARACTERISTIC__REFINES_CHARACTERISTICS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Characteristics feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCharacteristicsPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Characteristic_characteristics_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Characteristic_characteristics_feature", "_UI_Characteristic_type"),
				 CharacteristicPackage.Literals.CHARACTERISTIC__CHARACTERISTICS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
	{
		if (childrenFeatures == null)
		{
			super.getChildrenFeatures(object);
			childrenFeatures.add(InstancePackage.Literals.CHARACTERIZABLE__LOCAL_MANIFESTATIONS);
			childrenFeatures.add(CharacteristicPackage.Literals.CHARACTERISTIC__LOCAL_CHARACTERISTICS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child)
	{
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns Characteristic.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object)
	{
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Characteristic"));
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
		String label = ((Characteristic)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Characteristic_type") :
			getString("_UI_Characteristic_type") + " " + label;
	}
	

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification)
	{
		updateChildren(notification);

		switch (notification.getFeatureID(Characteristic.class))
		{
			case CharacteristicPackage.CHARACTERISTIC__NAME:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case CharacteristicPackage.CHARACTERISTIC__LOCAL_MANIFESTATIONS:
			case CharacteristicPackage.CHARACTERISTIC__LOCAL_CHARACTERISTICS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
	{
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(InstancePackage.Literals.CHARACTERIZABLE__LOCAL_MANIFESTATIONS,
				 InstanceFactory.eINSTANCE.createCharacteristicManifestation()));

		newChildDescriptors.add
			(createChildParameter
				(CharacteristicPackage.Literals.CHARACTERISTIC__LOCAL_CHARACTERISTICS,
				 CharacteristicFactory.eINSTANCE.createCharacteristic()));

		newChildDescriptors.add
			(createChildParameter
				(CharacteristicPackage.Literals.CHARACTERISTIC__LOCAL_CHARACTERISTICS,
				 CharacteristicFactory.eINSTANCE.createCharacteristicReference()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator()
	{
		return ((IChildCreationExtender)adapterFactory).getResourceLocator();
	}

}
