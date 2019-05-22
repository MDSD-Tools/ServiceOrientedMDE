/**
 */
package tools.mdsd.characteristics.examples.pcmzero.presentation;

import org.eclipse.core.resources.IResource;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;

import tools.mdsd.characteristics.edit.support.CharacteristicsEditSupportExtension;
import tools.mdsd.characteristics.edit.support.ExtensibleDispatchingItemProviderDecoratorFactory;
import tools.mdsd.characteristics.ui.eclipse.CharacteristicsEclipseUISupportExtension;

/**
 * This is an example of a Pcmzero model editor.
 */
public class PcmzeroEditor
	extends PcmzeroEditorGen
{
	
	@Override
	protected void initializeEditingDomain() {
		super.initializeEditingDomain();
		
		ExtensibleDispatchingItemProviderDecoratorFactory exFact = 
				new ExtensibleDispatchingItemProviderDecoratorFactory(adapterFactory);
		CharacteristicsEditSupportExtension.registerCharacteristicSupportExtensions(exFact);
		
		adapterFactory = new ComposedAdapterFactory(exFact);
		editingDomain.setAdapterFactory(adapterFactory);
	}
	
	@Override
	public void createModel() {
		super.createModel();
		
		CharacteristicsEclipseUISupportExtension.loadModelingRealm(getEditorInput().getAdapter(IResource.class), 
				editingDomain.getResourceSet());
		
	}
}
