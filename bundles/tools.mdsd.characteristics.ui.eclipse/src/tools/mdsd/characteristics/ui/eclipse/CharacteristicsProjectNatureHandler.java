package tools.mdsd.characteristics.ui.eclipse;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public abstract class CharacteristicsProjectNatureHandler extends AbstractHandler {
	public static class EnableCharacteristicsProjectNatureHandler extends CharacteristicsProjectNatureHandler {
		@Override
		String[] adaptNatures(String[] oldNatures) {
			String[] newNatures = new String[oldNatures.length + 1];
            newNatures[oldNatures.length] = CharacteristicsEnabledProjectNature.NATURE_ID;
            System.arraycopy(oldNatures, 0, newNatures, 0, oldNatures.length);
            return newNatures;
		}
	}
	
	public static class DisableCharacteristicsProjectNatureHandler extends CharacteristicsProjectNatureHandler {
		@Override
		String[] adaptNatures(String[] oldNatures) {
			List<String> natures = Arrays.asList(oldNatures);
			natures.remove(CharacteristicsEnabledProjectNature.NATURE_ID);
			return natures.toArray(new String[oldNatures.length - 1]);
		}
	}
	
	abstract String[] adaptNatures(String[] oldNatures);

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// similar to:
		// https://www.vogella.com/tutorials/EclipseProjectNatures/article.html#add-a-convert-handler
		
		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		if (currentSelection instanceof IStructuredSelection) {

            Object firstElement = ((IStructuredSelection) currentSelection).getFirstElement();

            IAdapterManager adapterManager = Platform.getAdapterManager();
            IResource resourceAdapter = adapterManager.getAdapter(firstElement, IResource.class);

            if (resourceAdapter != null) {
                IResource resource = resourceAdapter;
                IProject project = resource.getProject();
                try {
                    IProjectDescription description = project.getDescription();
                    String[] newNatures = adaptNatures(description.getNatureIds()); 
                    IWorkspace workspace = ResourcesPlugin.getWorkspace();
                    IStatus status = workspace.validateNatureSet(newNatures);

                    // only apply new nature, if the status is ok
                    if (status.getCode() == IStatus.OK) {
                        description.setNatureIds(newNatures);
                        project.setDescription(description, null);
                    }
                    return status;
                } catch (CoreException e) {
                    throw new ExecutionException(e.getMessage(), e);
                }
            }
		}
		return Status.OK_STATUS;
	}

}
