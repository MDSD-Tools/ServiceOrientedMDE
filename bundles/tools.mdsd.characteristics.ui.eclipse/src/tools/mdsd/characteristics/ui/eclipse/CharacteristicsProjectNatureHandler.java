package tools.mdsd.characteristics.ui.eclipse;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import com.google.common.collect.Streams;

public abstract class CharacteristicsProjectNatureHandler extends AbstractHandler {
	public static class EnableCharacteristicsProjectNatureHandler extends CharacteristicsProjectNatureHandler {
		@Override
		boolean shouldAdapt(IProjectDescription description) {
			return !description.hasNature(CharacteristicsEnabledProjectNature.NATURE_ID);
		}
		
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
		boolean shouldAdapt(IProjectDescription description) {
			return description.hasNature(CharacteristicsEnabledProjectNature.NATURE_ID);
		}
		
		@Override
		String[] adaptNatures(String[] oldNatures) {
			String[] newNatures = new String[oldNatures.length - 1];
			int idx = Arrays.asList(oldNatures).indexOf(CharacteristicsEnabledProjectNature.NATURE_ID);
			System.arraycopy(oldNatures, 0, newNatures, 0, idx);
			System.arraycopy(oldNatures, idx + 1, newNatures, idx, newNatures.length - idx);
			return newNatures;
		}
	}
	
	abstract boolean shouldAdapt(IProjectDescription description);
	abstract String[] adaptNatures(String[] oldNatures);

	@SuppressWarnings("unchecked")
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		if (currentSelection instanceof IStructuredSelection) {
			
			IAdapterManager adapterManager = Platform.getAdapterManager();
			List<IStatus> status = Streams.stream((Iterator<Object>)((IStructuredSelection)currentSelection).iterator())
				.map(elem -> adapterManager.getAdapter(elem, IResource.class))
				.filter(ra -> ra != null)
				.map(res -> {
					IProject project = res.getProject();
	                try {
	                    IProjectDescription description = project.getDescription();
	                    if (shouldAdapt(description)) {
		                    String[] newNatures = adaptNatures(description.getNatureIds()); 
		                    IWorkspace workspace = ResourcesPlugin.getWorkspace();
		                    IStatus localStatus = workspace.validateNatureSet(newNatures);
	
		                    // only apply new nature, if the status is ok
		                    if (localStatus.getCode() == IStatus.OK) {
		                        description.setNatureIds(newNatures);
		                        project.setDescription(description, null);
		                    }
		                    return localStatus;
	                    }
	                    return Status.OK_STATUS;
	                } catch (CoreException e) {
	                	return new Status(IStatus.ERROR, CharacteristicsEclipseUISupportExtension.PLUGIN_ID, 
	                			e.getMessage(), e);
	                }	
				}).collect(Collectors.toList());
			if (status.stream().allMatch(stat -> stat.getCode() == IStatus.OK)) {
				return Status.OK_STATUS; 
			} else {
				return new MultiStatus(CharacteristicsEclipseUISupportExtension.PLUGIN_ID, IStatus.ERROR, 
						status.toArray(new IStatus[status.size()]), 
						"The command failed due to multiple problems", null);
			}
		}
		return Status.OK_STATUS;
	}

}
