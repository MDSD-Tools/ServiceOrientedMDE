package tools.mdsd.characteristics.support;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ServiceScope;

import tools.mdsd.characteristics.support.api.ICharacteristicsModelingRealm;
import tools.mdsd.characteristics.support.api.IModelingRealmFactory;
import tools.mdsd.characteristics.support.api.IModelingRealmStorage;

@Component(scope=ServiceScope.SINGLETON, service=ModelingRealmManagement.class)
public class ModelingRealmManagement {
	
	private static ModelingRealmManagement INSTANCE = null;
	
	@Reference(cardinality=ReferenceCardinality.MANDATORY, policy=ReferencePolicy.STATIC)
	protected IModelingRealmFactory factory = null;
	
	@Reference(cardinality=ReferenceCardinality.MANDATORY, policy=ReferencePolicy.STATIC)
	protected IModelingRealmStorage storage = null;
	
	
	@Activate
	public void activate() {
		if (ModelingRealmManagement.INSTANCE != null) {
			throw new IllegalStateException ("Double initialization.");
		}
		ModelingRealmManagement.INSTANCE = this;
	}
	
	@Deactivate
	public void deactivate() {
		ModelingRealmManagement.INSTANCE = null;
	}
	
	public ICharacteristicsModelingRealm getModelingRealm(ResourceSet resourceSet) {
		return storage.getModelingRealm(resourceSet).orElseGet(() -> {
			ICharacteristicsModelingRealm realm = factory.createModelingRealm(resourceSet);
			storage.putModelingRealm(realm, resourceSet);
			return realm;
		});
	}
	
	public static ModelingRealmManagement getInstance() {
		if (ModelingRealmManagement.INSTANCE == null) {
			synchronized (ModelingRealmManagement.class) {
				// TODO Set up without Declarative Services
			}
		}
		
		return ModelingRealmManagement.INSTANCE;
	}
}
