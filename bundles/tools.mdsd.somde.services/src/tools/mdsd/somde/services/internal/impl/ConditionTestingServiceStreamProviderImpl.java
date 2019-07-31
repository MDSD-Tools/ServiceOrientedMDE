package tools.mdsd.somde.services.internal.impl;

import java.util.stream.Stream;
import tools.mdsd.somde.services.Service.ArgumentSelector;
import tools.mdsd.somde.services.internal.ConditionEvaluator;
import tools.mdsd.somde.services.internal.ServiceStreamProvider;

public class ConditionTestingServiceStreamProviderImpl<ObjectType, ServiceType>
        implements ServiceStreamProvider<ServiceType> {

    private ArgumentSelector<ObjectType> selector;
    private ConditionEvaluator<ObjectType, ServiceType> evaluator;

    public ConditionTestingServiceStreamProviderImpl(ArgumentSelector<ObjectType> selector,
            ConditionEvaluator<ObjectType, ServiceType> evaluator) {
        this.selector = selector;
        this.evaluator = evaluator;
    }

    @Override
    public Stream<ServiceType> getServiceManagers(Object... parameters) {
        return evaluator.selectSuitableServiceStreamProvider(selector.apply(parameters))
                .getServiceManagers(parameters);
    }

}
