package tools.mdsd.characteristics.edit.support;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.DecoratorAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IChildCreationExtender;
import org.eclipse.emf.edit.provider.IItemProviderDecorator;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import tools.mdsd.characteristics.edit.support.util.AdapterItemProviderDecorator;

public class ExtensibleDispatchingItemProviderDecoratorFactory extends DecoratorAdapterFactory implements IChildCreationExtender {
	@FunctionalInterface
	public interface ItemProviderDecoratorFactoryProvider {
		IItemProviderDecorator createItemProviderDecorator(Object target, Object Type, AdapterFactory factory);
	}
	
	private ListMultimap<EClass, ItemProviderDecoratorFactoryProvider> registeredProviders = 
			ArrayListMultimap.create();
	
	public ExtensibleDispatchingItemProviderDecoratorFactory(AdapterFactory decoratedAdapterFactory) {
		super(decoratedAdapterFactory);
	}
	
	public void registerDecoratorFactoryProvider(EClass forClass, ItemProviderDecoratorFactoryProvider factoryProvider) {
		registeredProviders.put(forClass, factoryProvider);
	}
	
	@Override
	public Object adapt(Object target, Object type) {
		Object adapter = decoratedAdapterFactory.adapt(target, type);
	    if (adapter instanceof IChangeNotifier)
	    {
	      IItemProviderDecorator itemProviderDecorator = itemProviderDecorators.get(adapter);
	      if (itemProviderDecorator == null)
	      {
	    	itemProviderDecorator = createItemProviderDecorator(target, type, (IChangeNotifier) adapter);
	        itemProviderDecorators.put(adapter, itemProviderDecorator);
	        if (itemProviderDecorator.getDecoratedItemProvider() == null)
	        	itemProviderDecorator.setDecoratedItemProvider((IChangeNotifier) adapter);
	      }

	      return itemProviderDecorator;
	    }

	    return adapter;
	}

	protected IItemProviderDecorator createItemProviderDecorator(Object target, Object Type, IChangeNotifier adapter) {
		IItemProviderDecorator result = null;
		if (target instanceof EObject) {
			EClass clazz = ((EObject) target).eClass();
			result = clazz.getEAllSuperTypes().stream().map(registeredProviders::get).flatMap(Collection::stream)
				.<IItemProviderDecorator>reduce(null, (res, prov) -> {
					IItemProviderDecorator decorator = prov.createItemProviderDecorator(target, Type, this);
					decorator.setDecoratedItemProvider(res == null ? adapter : (IChangeNotifier) res);
					return decorator;
				}, (res1, res2) -> res2);
			
		}
		return result != null ? result : new AdapterItemProviderDecorator(this);
	}
	
	@Override
	protected IItemProviderDecorator createItemProviderDecorator(Object target, Object Type) {
		throw new UnsupportedOperationException("Please use alternative creation providing the adapter directly");
	}

	@Override
	public Collection<?> getNewChildDescriptors(Object object, EditingDomain editingDomain) {
		return ((IChildCreationExtender)decoratedAdapterFactory).getNewChildDescriptors(object, editingDomain);
	}

	@Override
	public ResourceLocator getResourceLocator() {
		return ((IChildCreationExtender)decoratedAdapterFactory).getResourceLocator();
	}

}
