package tools.mdsd.characteristics.services.eclipse;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import org.eclipse.core.runtime.IExtensionRegistry;
import tools.mdsd.characteristics.services.Defaults;
import tools.mdsd.characteristics.services.Service;
import tools.mdsd.characteristics.services.ServiceTypeRegistryAccess;

public class ExtensionRegistryBasedServiceTypeRegistryAccessImpl
        implements ServiceTypeRegistryAccess {
    private IExtensionRegistry extensionRegistry;

    public ExtensionRegistryBasedServiceTypeRegistryAccessImpl(
            IExtensionRegistry extensionRegistry) {
        this.extensionRegistry = extensionRegistry;
    }

    @Override
    public Collection<Class<?>> getRegisteredServiceTypes() {
        return Arrays
                .stream(extensionRegistry
                        .getConfigurationElementsFor(Defaults.SERVICE_TYPE_EXTENSION_POINT_ID))
                .map(conf -> conf.getAttribute(Defaults.SERVICE_TYPE_EXTENSION_SERVICE_ATTRIBUTE))
                .map(this::retrieveClass).collect(Collectors.toList());
    }

    @Override
    public Collection<Class<?>> getRegisteredImplementations(
            Class<?> serviceType) {
        String serviceName = serviceType.getName();

        return Arrays
                .stream(extensionRegistry.getConfigurationElementsFor(
                        Defaults.SERVICE_IMPLEMENTATION_EXTENSION_POINT_ID))
                .filter(conf -> conf.getAttribute(Defaults.SERVICE_IMPLEMENTATION_SERVICE_ATTRIBUTE)
                        .equals(serviceName))
                .map(conf -> conf.getAttribute(Defaults.SERVICE_TYPE_EXTENSION_SERVICE_ATTRIBUTE))
                .map(this::retrieveClass).map(Class.class::cast).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private <T extends Service<T>> Class<T> retrieveClass(String className) {
        try {
            return (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
