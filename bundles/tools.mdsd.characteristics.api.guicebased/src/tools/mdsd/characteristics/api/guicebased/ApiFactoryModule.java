package tools.mdsd.characteristics.api.guicebased;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.multibindings.Multibinder;

import tools.mdsd.characteristics.api.ApiFactory;
import tools.mdsd.characteristics.api.CharacteristicsModelingRealm;
import tools.mdsd.characteristics.api.SerializationService;
import tools.mdsd.characteristics.api.StaticManifestationSerializationService;
import tools.mdsd.characteristics.api.ValueTypePropertiesProvider;
import tools.mdsd.characteristics.api.ValueTypePropertiesService;

public class ApiFactoryModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ApiFactory.class).toInstance(ApiFactory.eINSTANCE);
		
		Multibinder.newSetBinder(binder(), ValueTypePropertiesProvider.class);
		Multibinder.newSetBinder(binder(), StaticManifestationSerializationService.class);
	}
	
	@Provides
	CharacteristicsModelingRealm provideModelingRealm(ApiFactory factory) {
		return factory.createCharacteristicsModelingRealm();
	}
	
	@Provides
	ValueTypePropertiesService provideValueTypePropertiesService(ApiFactory factory) {
		return factory.createValueTypePropertiesService();
	}
	
	@Provides
	SerializationService provideSerializationService(ApiFactory factory) {
		return factory.createSerializationService();
	}

}
