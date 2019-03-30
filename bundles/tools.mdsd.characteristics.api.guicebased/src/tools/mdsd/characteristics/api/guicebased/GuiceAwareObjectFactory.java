package tools.mdsd.characteristics.api.guicebased;

import java.util.Optional;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IExecutableExtensionFactory;
import org.eclipse.core.runtime.Platform;

import com.google.inject.Injector;

public class GuiceAwareObjectFactory implements IExecutableExtensionFactory, IExecutableExtension {
	
	private Optional<String> className = Optional.empty(); 

	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
			throws CoreException {
		className = Optional.of(data).filter(String.class::isInstance).map(String.class::cast);
	}

	@Override
	public Object create() throws CoreException {
		String clsName = className.orElseThrow(() -> new RuntimeException("Instantiation failed. No classname provided."));
		Class<?> clazz;
		try {
			clazz = Platform.getBundle(Activator.PLUGIN_ID).loadClass(clsName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		final Injector injector = Activator.getInjector();
		if (injector == null) {
			throw new RuntimeException("Instantiation failed. No injector available.");
		}
		return injector.getInstance(clazz);
	}

}
