package tools.mdsd.characteristics.utils;

import org.eclipse.emf.ecore.EObject;

import tools.mdsd.characteristics.CharacteristicsConstants;
import tools.mdsd.characteristics.api.ApiPackage;
import tools.mdsd.characteristics.api.CharacteristicsModelingRealm;

public interface CharacteristicsAPIAware extends EObject {
	
	default CharacteristicsModelingRealm getAPI() {
		//TODO: Speed-up lookup by caching api using eclipse / emf adapter concept
		return eResource().getResourceSet().getResources().stream()
			.filter(r -> r.getURI().scheme().equals(CharacteristicsConstants.URI_SCHEME))
			.findAny().map(r -> r.getContents().get(0))
			.filter(ApiPackage.eINSTANCE.getCharacteristicsModelingRealm()::isInstance)
			.map(CharacteristicsModelingRealm.class::cast)
			.orElseThrow(() -> new IllegalStateException("Characteristics api has not been loaded. The model cannot be properly edited"));
	}

}
