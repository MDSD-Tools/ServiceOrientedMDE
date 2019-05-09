/**
 */
package tools.mdsd.characteristics.characteristic.presentation;

import tools.mdsd.characteristics.CharacteristicsConstants;

/**
 * This is the custom code extension to the Characteristic model editor.
 */
public class CharacteristicEditor extends CharacteristicEditorGen {

	@Override
	public void createModel() {
		super.createModel();

		editingDomain.getResourceSet().getResource(CharacteristicsConstants.BASE_VALUE_TYPE_MODEL, true);
	}
}
