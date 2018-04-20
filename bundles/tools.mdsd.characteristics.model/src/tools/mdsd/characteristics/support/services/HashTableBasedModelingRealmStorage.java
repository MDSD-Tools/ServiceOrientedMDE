package tools.mdsd.characteristics.support.services;

import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import tools.mdsd.characteristics.support.api.ICharacteristicsModelingRealm;
import tools.mdsd.characteristics.support.api.IModelingRealmStorage;

@Component(scope=ServiceScope.SINGLETON)
public class HashTableBasedModelingRealmStorage implements IModelingRealmStorage {
	protected final Map<ResourceSet, ICharacteristicsModelingRealm> storage = new WeakHashMap<>(); 

	@Override
	public Optional<ICharacteristicsModelingRealm> getModelingRealm(ResourceSet resourceSet) {
		return Optional.ofNullable(storage.get(resourceSet));
	}

	@Override
	public void putModelingRealm(ICharacteristicsModelingRealm realm,
			ResourceSet resourceSet) {
		storage.put(resourceSet, realm);
	}

}
