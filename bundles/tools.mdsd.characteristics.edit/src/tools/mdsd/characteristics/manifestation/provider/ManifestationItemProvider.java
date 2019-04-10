/**
 */
package tools.mdsd.characteristics.manifestation.provider;


import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

/**
 * This is the item provider adapter for a {@link tools.mdsd.characteristics.manifestation.Manifestation} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ManifestationItemProvider 
	extends ManifestationItemProviderGen
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource
{
	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public ManifestationItemProvider(AdapterFactory adapterFactory)
	{
		super(adapterFactory);
	}
	
	/**
	 * This removes the property descriptor of the invisible Storage feature.
	 * 
	 */
	protected void addStoragePropertyDescriptor(Object object)
	{
		// Intentionally left blank!
	}
}
