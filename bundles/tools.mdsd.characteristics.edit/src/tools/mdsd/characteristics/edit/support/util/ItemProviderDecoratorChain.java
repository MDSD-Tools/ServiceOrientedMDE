package tools.mdsd.characteristics.edit.support.util;

import java.util.Iterator;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IItemProviderDecorator;

public class ItemProviderDecoratorChain extends AdapterItemProviderDecorator {
	
	private final IItemProviderDecorator head;
	private final IItemProviderDecorator tail;

	public ItemProviderDecoratorChain(AdapterFactory adapterFactory, Iterable<IItemProviderDecorator> decorators) {
		super(adapterFactory);
		
		Iterator<IItemProviderDecorator> iterator = decorators.iterator();
		head = iterator.next();
		IItemProviderDecorator prelimTail = head;
		while (iterator.hasNext()) {
			IItemProviderDecorator next = iterator.next();
			prelimTail.setDecoratedItemProvider((IChangeNotifier) next);
			prelimTail = next;
		}
		tail = prelimTail;
		super.setDecoratedItemProvider((IChangeNotifier) head);
	}
	
	@Override
	public void setDecoratedItemProvider(IChangeNotifier decoratedItemProvider) {
		tail.setDecoratedItemProvider(decoratedItemProvider);
	}

}
