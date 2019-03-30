package tools.mdsd.characteristics.api.guicebased;

import org.eclipse.emf.ecore.resource.Resource;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;

import tools.mdsd.characteristics.api.ApiFactory;
import tools.mdsd.characteristics.api.CharacteristicsModelingRealm;
import tools.mdsd.characteristics.api.ValueTypePropertiesService;
import tools.mdsd.characteristics.api.guicebased.CharacteristicsResourceFactory.ModuleProvider;
import tools.mdsd.characteristics.api.guicebased.impl.GuiceInjectorAwareResourceFactoryImpl;
import tools.mdsd.characteristics.api.guicebased.impl.ReflectiveNameBasedModuleProvider;

public class ApiFactoryModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Resource.Factory.class).to(GuiceInjectorAwareResourceFactoryImpl.class);
		bind(ModuleProvider.class).annotatedWith(Names.named("module")).to(ReflectiveNameBasedModuleProvider.class);
		bind(ApiFactory.class).toInstance(ApiFactory.eINSTANCE);
	}
	
	@Provides
	CharacteristicsModelingRealm provideModelingRealm(ApiFactory factory) {
		return factory.createCharacteristicsModelingRealm();
	}
	
	@Provides
	ValueTypePropertiesService provideValueTypePropertiesService(ApiFactory factory) {
		return factory.createValueTypePropertiesService();
	}

}
