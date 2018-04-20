package tools.mdsd.characteristics.support.api;

import java.util.Optional;

import org.eclipse.emf.ecore.resource.ResourceSet;

public interface IModelingRealmStorage {
	public Optional<ICharacteristicsModelingRealm> getModelingRealm(ResourceSet resourceSet);
	
	public void putModelingRealm(ICharacteristicsModelingRealm realm, ResourceSet resourceSet);
}
