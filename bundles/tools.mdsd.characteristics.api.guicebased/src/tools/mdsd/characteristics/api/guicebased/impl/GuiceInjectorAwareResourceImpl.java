package tools.mdsd.characteristics.api.guicebased.impl;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;

import com.google.inject.Injector;

import tools.mdsd.characteristics.api.guicebased.GuiceInjectorAware;

public class GuiceInjectorAwareResourceImpl extends ResourceImpl implements GuiceInjectorAware {
	
	private Injector injector;

	public GuiceInjectorAwareResourceImpl(URI uri, Injector injector) {
		super(uri);
		this.injector = injector;
	}

	@Override
	public Injector getInjector() {
		return injector;
	}
}
