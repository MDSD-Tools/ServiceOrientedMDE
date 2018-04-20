package tools.mdsd.characteristics.support.api;

import org.eclipse.emf.ecore.resource.ResourceSet;

public interface IModelingRealmFactory {
	public ICharacteristicsModelingRealm createModelingRealm(ResourceSet resourceSet);

}
