package tools.mdsd.characteristics.realm.guicebased.impl;

import java.util.function.Supplier;
import org.eclipse.emf.common.notify.Notifier;
import com.google.inject.Injector;
import tools.mdsd.characteristics.realm.DependencyResolvingAdapter;

public class GuiceInjectorDependencyResolvingAdapter extends DependencyResolvingAdapter {
    
    private Supplier<Injector> injectorSupplier;

    public GuiceInjectorDependencyResolvingAdapter(Supplier<Injector> injectorSupplier) {
        this.injectorSupplier = injectorSupplier;
    }

    @Override
    protected void doInject(Notifier object) {
        injectorSupplier.get().injectMembers(object);
    }

}
