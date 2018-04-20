package tools.mdsd.characteristics.support.api;

import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * The Characteristics Modeling Realm encapsulates the support infrastructure 
 * for one resource set. Its purpose is to allow for different model instances
 * to use different property resolution mechanisms. 
 * 
 * @author Sebastian Krach
 *
 */
public interface ICharacteristicsModelingRealm {
	public ResourceSet getResourceSet();
	
	public IValueTypeSupport getValueTypeSupport();

}
