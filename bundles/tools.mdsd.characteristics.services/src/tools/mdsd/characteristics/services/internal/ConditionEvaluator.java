package tools.mdsd.characteristics.services.internal;

public interface ConditionEvaluator<ObjectType, ServiceType> {
    ServiceStreamProvider<ServiceType> selectSuitableServiceStreamProvider(ObjectType parameter);
}
