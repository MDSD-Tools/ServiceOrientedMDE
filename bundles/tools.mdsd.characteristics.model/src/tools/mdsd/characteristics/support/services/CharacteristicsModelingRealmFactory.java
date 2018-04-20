package tools.mdsd.characteristics.support.services;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ServiceScope;

import tools.mdsd.characteristics.support.api.ICharacteristicsModelingRealm;
import tools.mdsd.characteristics.support.api.IModelingRealmFactory;
import tools.mdsd.characteristics.support.api.IValueTypeSupport;
import tools.mdsd.characteristics.support.api.IValueTypeSupportFactory;

@Component(scope=ServiceScope.SINGLETON)
public class CharacteristicsModelingRealmFactory implements IModelingRealmFactory {
	
	@Reference(cardinality=ReferenceCardinality.MANDATORY, policy=ReferencePolicy.STATIC)
	protected IValueTypeSupportFactory valueTypeSupportFactory = null;

	@Override
	public ICharacteristicsModelingRealm createModelingRealm(ResourceSet resourceSet) {
		IValueTypeSupport support = valueTypeSupportFactory.createValueTypeSupport(resourceSet);
		return new ICharacteristicsModelingRealm() {
			
			@Override
			public IValueTypeSupport getValueTypeSupport() {
				return support;
			}
			
			@Override
			public ResourceSet getResourceSet() {
				// TODO Auto-generated method stub
				return resourceSet;
			}
		};
	}

}
