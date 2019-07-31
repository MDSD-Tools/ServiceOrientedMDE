package tools.mdsd.characteristics.realm;

import java.util.function.Supplier;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

public class ModelingRealmResolvingAdapterFactory extends AdapterFactoryImpl {
    private Supplier<DependencyResolvingAdapter> adapterSupplier;

    public ModelingRealmResolvingAdapterFactory(Supplier<DependencyResolvingAdapter> adapterSupplier) {
        this.adapterSupplier = adapterSupplier;
    }
    
    @Override
    protected Adapter createAdapter(Notifier target) {
        return adapterSupplier.get();
    }
    
    @Override
    public boolean isFactoryForType(Object type) {
        return type == DependencyResolvingAdapter.class;
    }

}
