package tools.mdsd.characteristics.edit.support;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.DecoratorAdapterFactory;
import org.eclipse.emf.edit.provider.IChildCreationExtender;
import org.eclipse.emf.edit.provider.IItemProviderDecorator;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import tools.mdsd.characteristics.edit.support.util.AdapterItemProviderDecorator;
import tools.mdsd.characteristics.edit.support.util.ItemProviderDecoratorChain;

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
	protected IItemProviderDecorator createItemProviderDecorator(Object target, Object Type) {
		IItemProviderDecorator result = null;
		if (target instanceof EObject) {
			EClass clazz = ((EObject) target).eClass();
			List<IItemProviderDecorator> collect = clazz.getEAllSuperTypes().stream()
				.map(registeredProviders::get).flatMap(Collection::stream)
				.map(prov -> prov.createItemProviderDecorator(target, Type, this))
				.collect(Collectors.toList());
			if (!collect.isEmpty()) {
				result = new ItemProviderDecoratorChain(this,  collect);
			}
		}
		return result != null ? result : new AdapterItemProviderDecorator(this);	
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
