package tools.mdsd.characteristics.edit.support.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderDecorator;

/**
 * Extends the ItemProviderDecorator implementation with delegations required by
 * some of the applications in EMF.edit code.
 * 
 * This class can be used as base class for custom decorator implementations.
 *
 */
public class AdapterItemProviderDecorator extends ItemProviderDecorator implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource, Adapter {

	public AdapterItemProviderDecorator(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public Notifier getTarget() {
		return ((Adapter) getDecoratedItemProvider()).getTarget();
	}

	@Override
	public void setTarget(final Notifier newTarget) {
		((Adapter) getDecoratedItemProvider()).setTarget(newTarget);
	}
}