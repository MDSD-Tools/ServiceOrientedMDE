package tools.mdsd.somde.services.internal.impl;

import tools.mdsd.somde.services.Service.RegistrationType;
import tools.mdsd.somde.services.internal.ConditionEvaluator;
import tools.mdsd.somde.services.internal.ServiceStreamProvider;

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
