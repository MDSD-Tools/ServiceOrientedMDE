package tools.mdsd.characteristics.api.guicebased.impl;

import javax.inject.Inject;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import com.google.inject.Injector;

public class GuiceInjectorAwareResourceFactoryImpl implements Resource.Factory {

	private Injector injector;

	@Inject
	public GuiceInjectorAwareResourceFactoryImpl(Injector injector) {
		this.injector = injector;
	}
	
	@Override
	public Resource createResource(URI uri) {
		return new GuiceInjectorAwareResourceImpl(uri, injector);
	}

}
