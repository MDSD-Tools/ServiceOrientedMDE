package tools.mdsd.characteristics.api.guicebased;

import javax.inject.Inject;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Guice;
import com.google.inject.Module;
import com.google.inject.name.Names;
import com.google.inject.util.Modules;

import tools.mdsd.characteristics.CharacteristicsConstants;
import tools.mdsd.characteristics.api.ApiFactory;

public class CharacteristicsResourceFactory implements Resource.Factory {
	private Injector injector;

	public static interface ModuleProvider {
		Module getModule(URI uri);
	}
	
	@Inject
	public CharacteristicsResourceFactory(Injector injector) {
		this.injector = injector;
		
	}

	@Override
	public Resource createResource(URI uri) {
		if (uri.scheme().equals(CharacteristicsConstants.URI_SCHEME)) {
			ModuleProvider m = injector.getInstance(Key.get(ModuleProvider.class, Names.named(uri.segment(0))));
			
			Injector instanceInjector = Guice.createInjector(Modules.override(new ApiFactoryModule()).with(m.getModule(uri)));
			Resource r = instanceInjector.getInstance(Resource.Factory.class).createResource(uri);
			r.getContents().add(ApiFactory.eINSTANCE.createCharacteristicsModelingRealm());
			return r;
		}
		return null;
	}

}
