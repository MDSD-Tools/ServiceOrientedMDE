package tools.mdsd.characteristics.api.guicebased;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "tools.mdsd.characteristics.api.guicebased"; //$NON-NLS-1$
	

	// The shared instance
	private static Activator plugin;
	private static Injector injector;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		injector = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	public static Injector getInjector() {
		if (injector == null) {
			synchronized (Activator.class) {
				if (injector == null) {
					injector = Guice.createInjector(new ApiFactoryModule());
				}
			}
		}
		return injector;
	}

}

