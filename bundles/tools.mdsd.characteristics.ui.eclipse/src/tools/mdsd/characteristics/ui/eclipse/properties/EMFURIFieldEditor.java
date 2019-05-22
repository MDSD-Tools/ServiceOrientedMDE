package tools.mdsd.characteristics.ui.eclipse.properties;

import java.util.Collections;
import org.eclipse.emf.common.ui.dialogs.ResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.preference.StringButtonFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class EMFURIFieldEditor extends StringButtonFieldEditor {

    private final URIConverter validityChecker = (new ResourceSetImpl()).getURIConverter();

    public EMFURIFieldEditor() {}

    public EMFURIFieldEditor(String name, String labelText, Composite parent) {
        super(name, labelText, parent);
        setValidateStrategy(VALIDATE_ON_KEY_STROKE);
        setChangeButtonText("...");
    }

    protected boolean doCheckState() {
        try {
            URI uri = URI.createURI(getStringValue());
            return validityChecker.exists(uri, Collections.emptyMap())
                    && doValidateURI(uri);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
    protected boolean doValidateURI(URI uriToValidate) {
        return true;
    }

    @Override
    protected String changePressed() {
        ResourceDialog resourceDialog =
                new ResourceDialog(getShell(), getLabelText(), SWT.OPEN | SWT.SINGLE);
        resourceDialog.setBlockOnOpen(true);
        if (resourceDialog.open() == ResourceDialog.OK) {
            return resourceDialog.getURIText();
        }
        return null;
    };



}
