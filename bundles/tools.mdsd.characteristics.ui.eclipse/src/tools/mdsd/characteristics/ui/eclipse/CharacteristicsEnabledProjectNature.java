package tools.mdsd.characteristics.ui.eclipse;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

public class CharacteristicsEnabledProjectNature implements IProjectNature {
	
	// ID of the natures, which consists of Bundle-SymbolicName + ID
    public static final String NATURE_ID = "tools.mdsd.characteristics.ui.eclipse.characteristicsenabled";

    private IProject project;

	@Override
	public void configure() throws CoreException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deconfigure() throws CoreException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IProject getProject() {
		return project;
	}

	@Override
	public void setProject(IProject project) {
		this.project = project;
	}

}
