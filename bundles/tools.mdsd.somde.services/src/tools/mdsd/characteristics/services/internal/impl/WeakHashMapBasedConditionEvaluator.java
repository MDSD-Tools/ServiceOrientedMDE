package tools.mdsd.characteristics.services.internal.impl;

import java.util.Map;
import java.util.WeakHashMap;
import tools.mdsd.characteristics.services.Service.RegistrationType;
import tools.mdsd.characteristics.services.internal.ServiceStreamProvider;

public class WeakHashMapBasedConditionEvaluator<ObjectType, KeyType, ServiceType> extends AbstractConditionEvaluator<ObjectType, KeyType, ServiceType> {
    private Map<KeyType, ServiceStreamProvider<ServiceType>> guardedProviders;

    public WeakHashMapBasedConditionEvaluator(
            RegistrationType<ObjectType, KeyType> registrationType,
            Map<KeyType, ServiceStreamProvider<ServiceType>> guardedProviders) {
        super(registrationType);
        this.guardedProviders = new WeakHashMap<>(guardedProviders);
    }

    @Override
    protected ServiceStreamProvider<ServiceType> findServiceProvider(KeyType key) {
        return guardedProviders.getOrDefault(key, ServiceStreamProvider.empty());
    }
}
