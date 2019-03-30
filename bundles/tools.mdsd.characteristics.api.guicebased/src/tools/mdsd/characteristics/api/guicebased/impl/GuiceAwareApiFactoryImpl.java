package tools.mdsd.characteristics.api.guicebased.impl;

import tools.mdsd.characteristics.api.CharacteristicsModelingRealm;
import tools.mdsd.characteristics.api.ValueTypePropertiesService;
import tools.mdsd.characteristics.api.impl.ApiFactoryImpl;

public class GuiceAwareApiFactoryImpl extends ApiFactoryImpl {
	
	@Override
	public CharacteristicsModelingRealm createCharacteristicsModelingRealm() {
		return new GuiceAwareCharacteristicsModelingRealmImpl();
	}
	
	@Override
	public ValueTypePropertiesService createValueTypePropertiesService() {
		return new GuiceValueTypePropertiesServiceImpl();
	}
	
	

}
