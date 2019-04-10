package tools.mdsd.characteristics.api.guicebased.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

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
	
	@Override
	protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
		//Intentionally left blank to prevent Resource from throwing UnsupportedOperationException
		//Setting the read-only property did not do the trick
	}
}
