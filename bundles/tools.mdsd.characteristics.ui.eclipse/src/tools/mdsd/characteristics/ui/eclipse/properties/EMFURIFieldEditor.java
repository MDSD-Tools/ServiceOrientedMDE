package tools.mdsd.characteristics.ui.eclipse.properties;

import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;

public class EMFURIFieldEditor extends StringFieldEditor {
	
	private final URIConverter validityChecker = (new ResourceSetImpl()).getURIConverter();
	
	public EMFURIFieldEditor() { }
	
	public EMFURIFieldEditor(String name, String labelText, int width,
            int strategy, Composite parent) {
        super(name, labelText, width, strategy, parent);
    }
	public EMFURIFieldEditor(String name, String labelText, int width,
            Composite parent) {
        this(name, labelText, width, VALIDATE_ON_KEY_STROKE, parent);
    }
	
	public EMFURIFieldEditor(String name, String labelText, Composite parent) {
        this(name, labelText, UNLIMITED, parent);
    }
	
	protected boolean doCheckState() {
		try {
			URI uri = URI.createURI(getStringValue());
			return validityChecker.exists(uri, Collections.emptyMap());
		} catch (IllegalArgumentException e) { 
			return false;					
		}
	};
	
	
	
	

}
