package tools.mdsd.somde.services.eclipse.guice;

import java.util.Set;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.util.Types;
import tools.mdsd.somde.services.ServiceTypeRegistryAccess;
import tools.mdsd.somde.services.guice.ServiceWrapperDelegationModule;

public class ExtensibleServiceAccessModule extends AbstractModule {
    @Inject
    ServiceTypeRegistryAccess registryTypeAccess;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    protected void configure() {
        for (Class serviceType : registryTypeAccess.getRegisteredServiceTypes()) {
            var multibinder = Multibinder.newSetBinder(binder(), serviceType);
            registryTypeAccess.getRegisteredImplementations(serviceType)
                    .forEach(type -> multibinder.addBinding().to(type));
            binder().install(new ServiceWrapperDelegationModule<>(serviceType));
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> TypeLiteral<Set<T>> setOf(Class<T> type) {
        return (TypeLiteral<Set<T>>) TypeLiteral.get(Types.setOf(type));
    }

}
