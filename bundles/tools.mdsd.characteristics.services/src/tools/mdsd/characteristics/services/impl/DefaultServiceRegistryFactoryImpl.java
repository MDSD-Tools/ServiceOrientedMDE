package tools.mdsd.characteristics.services.impl;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import tools.mdsd.characteristics.services.ServiceRegistration;
import tools.mdsd.characteristics.services.ServiceRegistry;
import tools.mdsd.characteristics.services.ServiceRegistryFactory;

public class DefaultServiceRegistryFactoryImpl implements ServiceRegistryFactory {
    static final Map<Class<?>, Function<Object, Object>> KEY_FUNCTIONS = Map.ofEntries(
            Map.entry(Object.class, Function.identity()),
            Map.entry(EClass.class, x -> ((EObject) x).eClass()),
            Map.entry(Class.class, Object::getClass));

    @Override
    public Collection<Class<?>> getSupportedKeyTypes() {
        return KEY_FUNCTIONS.keySet();
    }

    @Override
    public <KeyType, ServiceType, ObjectType> ServiceRegistry<KeyType, ServiceType, ObjectType> createRegistry(
            Iterable<ServiceRegistration<? extends ServiceType, KeyType>> services,
            Class<KeyType> keyType) {
        if (!getSupportedKeyTypes().contains(keyType)) {
            throw new IllegalArgumentException("Unsupported key type");
        }
        return new ServiceRegistryImpl<KeyType, ServiceType, ObjectType>(services, keyType, 
                (Function<ObjectType, KeyType>) KEY_FUNCTIONS.get(keyType));
    }

}
