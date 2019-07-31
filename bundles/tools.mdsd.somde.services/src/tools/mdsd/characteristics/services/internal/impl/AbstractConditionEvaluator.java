package tools.mdsd.characteristics.services.internal.impl;

import tools.mdsd.characteristics.services.Service.RegistrationType;
import tools.mdsd.characteristics.services.internal.ConditionEvaluator;
import tools.mdsd.characteristics.services.internal.ServiceStreamProvider;

public abstract class AbstractConditionEvaluator<ObjectType, KeyType, ServiceType>  implements ConditionEvaluator<ObjectType, ServiceType> {

    private RegistrationType<ObjectType, KeyType> registrationType;

    public AbstractConditionEvaluator(RegistrationType<ObjectType, KeyType> registrationType) {
        this.registrationType = registrationType;
    }
    
    @Override
    public ServiceStreamProvider<ServiceType> selectSuitableServiceStreamProvider(ObjectType parameter) {
        return findServiceProvider(registrationType.apply(parameter));
    }
    
    abstract protected ServiceStreamProvider<ServiceType> findServiceProvider(KeyType key);
}
