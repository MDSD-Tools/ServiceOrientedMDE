package tools.mdsd.characteristics.realm;

import java.util.function.Supplier;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

public class ModelingRealmResolvingAdapterFactory extends AdapterFactoryImpl {
    protected abstract class PropagatingAdapter extends AdapterImpl {
        @Override
        public void notifyChanged(Notification msg) {
            if (msg.getNotifier() instanceof EObject) {
                
            } else if (msg.getNotifier() instanceof Resource) {
                if (msg.getFeatureID(Resource.class) == Resource.RESOURCE__RESOURCE_SET) {
                    ModelingRealmResolvingAdapterFactory.this.adapt((Notifier) msg.getNewValue(), DependencyResolvingAdapter.class);
                }
            }
        }
    }
    
    private Supplier<DependencyResolvingAdapter> adapterSupplier;

    public ModelingRealmResolvingAdapterFactory(Supplier<DependencyResolvingAdapter> adapterSupplier) {
        this.adapterSupplier = adapterSupplier;
    }
    
    @Override
    protected Adapter createAdapter(Notifier target) {
        if (target instanceof EObject) {
            
        } else if (target instanceof Resource) {
            
        } else if (target instanceof ResourceSet) {
            return adapterSupplier.get();
        }
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean isFactoryForType(Object type) {
        return type == DependencyResolvingAdapter.class;
    }

}
