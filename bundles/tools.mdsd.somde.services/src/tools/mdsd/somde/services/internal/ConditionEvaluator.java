package tools.mdsd.somde.services.internal;

public interface ConditionEvaluator<ObjectType, ServiceType> {
    ServiceStreamProvider<ServiceType> selectSuitableServiceStreamProvider(ObjectType parameter);
}
