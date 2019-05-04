package tools.mdsd.characteristics.api.guicebased;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;

import com.google.inject.Injector;
import com.google.inject.Key;

public interface GuiceInjectorAware {
	
	Injector getInjector();
	
	default <T> T resolveInstance(Key<T> key, EObject thiz) {
		return getInjector(thiz).getInstance(key);
	}
	
	default <T> T resolveInstance(Class<T> clazz, EObject thiz) {
		return getInjector(thiz).getInstance(clazz);
	}
	
	default Injector getInjector(EObject thiz) {
		Notifier container = thiz.eContainer(); 
		container = container != null ? container : thiz.eResource();
		if (container instanceof GuiceInjectorAware) {
			return ((GuiceInjectorAware)container).getInjector();
		}
		throw new IllegalArgumentException("Inconsistent injector hierarchy");
	}
	
	public interface AwareEObject extends GuiceInjectorAware, EObject {
		@Override
		default Injector getInjector() {
			return this.resolveInstance(Injector.class, this);
		}
		
		default <T> T resolveInstance(Key<T> key) {
			return getInjector().getInstance(key);
		}
		
		
		default <T> T resolveInstance(Class<T> clazz) {
			return getInjector().getInstance(clazz);
		}
	}

}
