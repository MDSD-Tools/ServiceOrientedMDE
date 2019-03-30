/**
 */
package tools.mdsd.characteristics.instance.provider;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IChildCreationExtender;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import tools.mdsd.characteristics.characteristic.Characteristic;
import tools.mdsd.characteristics.characteristic.Characterizing;
import tools.mdsd.characteristics.characteristic.provider.CharacteristicBasedVirtualStructuralFeature;
import tools.mdsd.characteristics.instance.CharacteristicApplication;
import tools.mdsd.characteristics.instance.Characterizable;
import tools.mdsd.characteristics.instance.InstanceFactory;
import tools.mdsd.characteristics.instance.InstancePackage;

/**
 * This is the item provider adapter for a {@link tools.mdsd.characteristics.instance.Characterizable} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated NOT
 */
public class CharacterizableItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CharacterizableItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addAppliedCharacteristicsPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Applied Characteristics feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addAppliedCharacteristicsPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Characterizable_appliedCharacteristics_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Characterizable_appliedCharacteristics_feature", "_UI_Characterizable_type"),
				 InstancePackage.Literals.CHARACTERIZABLE__APPLIED_CHARACTERISTICS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
		for (Characterizing z: ((Characterizable) object).getAppliedCharacteristics()) {
			Characteristic c = z.computeCharacteristic();
			itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 String.format("<<char>> %s", c.getName()),
				 getString("_UI_PropertyDescriptor_description", "_UI_Characterizable_appliedCharacteristics_feature", "_UI_Characterizable_type"),
				 new CharacteristicBasedVirtualStructuralFeature((EObject) object, c),
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
		}
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			/* childrenFeatures.add(InstancePackage.Literals.CHARACTERIZABLE__MANIFESTATIONS);
			childrenFeatures.add(InstancePackage.Literals.CHARACTERIZABLE__LOCAL_APPLICATIONS); */
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_Characterizable_type");
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Characterizable.class)) {
		case InstancePackage.CHARACTERIZABLE__MANIFESTATIONS:
		case InstancePackage.CHARACTERIZABLE__LOCAL_APPLICATIONS:
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
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(InstancePackage.Literals.CHARACTERIZABLE__MANIFESTATIONS,
				InstanceFactory.eINSTANCE.createCharacteristicManifestation()));

		// deactivated as the containment reference is managed through the derived feature
		// InstancePackage.Literals.CHARACTERIZABLE__APPLIED_CHARACTERISTICS
		/*newChildDescriptors.add
			(createChildParameter
				(InstancePackage.Literals.CHARACTERIZABLE__LOCAL_APPLICATIONS,
				 InstanceFactory.eINSTANCE.createCharacteristicApplication()));*/
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

	/**
	 * This creates a primitive {@link org.eclipse.emf.edit.command.AddCommand}.
	 */
	@Override
	protected Command createAddCommand(EditingDomain domain, EObject owner, EStructuralFeature feature,
			Collection<?> collection, int index) {
		if (feature != InstancePackage.Literals.CHARACTERIZABLE__APPLIED_CHARACTERISTICS) {
			return super.createAddCommand(domain, owner, feature, collection, index); 
		} else {
			Collection<?> newCollection = collection.stream()
					.map(Characterizing.class::cast)
					.map(c -> {
						CharacteristicApplication cca = 
								InstanceFactory.eINSTANCE.createCharacteristicApplication();
						cca.setCharacteristic(c);
						return cca;
					})
					.collect(Collectors.toCollection(() -> new BasicEList<CharacteristicApplication>()));
			
			return super.createAddCommand(domain, owner, 
					(EStructuralFeature) InstancePackage.Literals.CHARACTERIZABLE__LOCAL_APPLICATIONS, 
					newCollection, index).chain(CLEAR_ITEM_PROPERTY_DESCRIPTORS);
		}
	}
	
	@Override
	protected Command createMoveCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value,
			int index) {
		return IdentityCommand.INSTANCE;
	}
	
	@Override
	protected Command createRemoveCommand(EditingDomain domain, EObject owner, EStructuralFeature feature,
			Collection<?> collection) {
		if (feature != InstancePackage.Literals.CHARACTERIZABLE__APPLIED_CHARACTERISTICS) {
			return super.createRemoveCommand(domain, owner, feature, collection); 
		} else {
			Collection<?> deleteCollection = ((List<?>)((EObject)owner).eGet(
					InstancePackage.Literals.CHARACTERIZABLE__LOCAL_APPLICATIONS)).stream()
				.map(CharacteristicApplication.class::cast)
				.filter(c -> collection.contains(c.getCharacteristic()))
				.collect(Collectors.toCollection(() -> new BasicEList<CharacteristicApplication>()));
			return super.createRemoveCommand(domain, owner, 
					(EStructuralFeature) InstancePackage.Literals.CHARACTERIZABLE__LOCAL_APPLICATIONS, 
					deleteCollection).chain(CLEAR_ITEM_PROPERTY_DESCRIPTORS);
		}
	}
	
	protected ItemPropertyDescriptorsClearCommand CLEAR_ITEM_PROPERTY_DESCRIPTORS =
			new ItemPropertyDescriptorsClearCommand();
	class ItemPropertyDescriptorsClearCommand extends AbstractCommand {

		@Override
		protected boolean prepare() { 
			return true; 
		};
		
		@Override
		public void undo() {
			// Intentionally left blank
		};
		
		@Override
		public void redo() {
			execute();
		}
		
		@Override
		public void execute() {
			CharacterizableItemProvider.this.itemPropertyDescriptors = null;
			CharacterizableItemProvider.this.childrenFeatures = null;
		}
		
	}

}
