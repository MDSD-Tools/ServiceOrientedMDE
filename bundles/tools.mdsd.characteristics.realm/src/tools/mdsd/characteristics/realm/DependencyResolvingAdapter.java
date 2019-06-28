package tools.mdsd.characteristics.realm;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.util.EContentAdapter;

public abstract class DependencyResolvingAdapter extends EContentAdapter {
    @Override
    protected void addAdapter(Notifier notifier) {
        doInject(notifier);
        super.addAdapter(notifier);
    }

    abstract protected void doInject(Notifier object); 

}
