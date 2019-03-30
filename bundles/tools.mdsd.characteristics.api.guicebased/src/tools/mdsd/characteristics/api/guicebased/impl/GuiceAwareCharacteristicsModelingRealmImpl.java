package tools.mdsd.characteristics.api.guicebased.impl;

import tools.mdsd.characteristics.api.ApiPackage;
import tools.mdsd.characteristics.api.ValueTypePropertiesService;
import tools.mdsd.characteristics.api.guicebased.GuiceInjectorAware;
import tools.mdsd.characteristics.api.impl.CharacteristicsModelingRealmImpl;

public class GuiceAwareCharacteristicsModelingRealmImpl
		extends CharacteristicsModelingRealmImpl implements GuiceInjectorAware.AwareEObject {
	
	@Override
	public ValueTypePropertiesService getValueTypePropertiesService() {
		if (valueTypePropertiesService == null) {
			this.eSet(ApiPackage.eINSTANCE.getCharacteristicsModelingRealm_ValueTypePropertiesService(), 
					resolveInstance(ValueTypePropertiesService.class));
		}
		return super.getValueTypePropertiesService();
	}
}
