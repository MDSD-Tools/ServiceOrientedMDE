package tools.mdsd.characteristics.ui.eclipse.properties;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import tools.mdsd.characteristics.ui.eclipse.CharacteristicsEnabledProjectNature;

public class CharacteristicsEnabledProjectPropertyPage extends FieldEditorPreferencePage 
	implements IWorkbenchPropertyPage {

	private IProject project;
	
	@Override
	protected IPreferenceStore doGetPreferenceStore() {
		return new ScopedPreferenceStore(new ProjectScope(project), 
				CharacteristicsEnabledProjectNature.NATURE_ID);
	}

	@Override
	protected void createFieldEditors() {
		EMFURIFieldEditor modelFile = new EMFURIFieldEditor(
				CharacteristicsEnabledProjectNature.MODELING_REAL_MODEL_URI_CONFIGURATION_STRING, 
				"&Characteristics Modeling Realm Model URI", getFieldEditorParent());
		modelFile.setEmptyStringAllowed(false);
		addField(modelFile);
	}

	@Override
	public IAdaptable getElement() {
		return this.project;
	}

	@Override
	public void setElement(IAdaptable element) {
		this.project = element.getAdapter(IProject.class);
	}


}