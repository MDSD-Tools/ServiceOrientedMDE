/**
 */
package tools.mdsd.characteristics.examples.pcmzero.presentation;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;

import tools.mdsd.characteristics.CharacteristicsConstants;
import tools.mdsd.characteristics.edit.support.CharacteristicsSupportExtension;
import tools.mdsd.characteristics.edit.support.ExtensibleDispatchingItemProviderDecoratorFactory;

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
		CharacteristicsSupportExtension.registerCharacteristicSupportExtensions(exFact);
		
		adapterFactory = new ComposedAdapterFactory(exFact);
		editingDomain.setAdapterFactory(adapterFactory);
	}
	
	@Override
	public void createModel() {
		super.createModel();
		
		//FIXME: This is a hack until we have properly resolved API initialization
		Resource res = editingDomain.getResourceSet().getResource(CharacteristicsConstants.REALM_INSTANCE_OR_DEFAULT,
				true);
		
		editingDomain.getResourceToReadOnlyMap().put(res, true);
	}
	
	@Override
	protected void handleActivate() {
		super.handleActivate();
		editingDomain.getResourceToReadOnlyMap().put(
				editingDomain.getResourceSet().getResource(CharacteristicsConstants.REALM_INSTANCE_OR_DEFAULT,
						false), true);
	}
}
