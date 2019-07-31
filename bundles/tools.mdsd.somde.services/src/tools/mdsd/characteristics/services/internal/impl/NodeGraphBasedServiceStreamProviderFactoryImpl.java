package tools.mdsd.characteristics.services.internal.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import tools.mdsd.characteristics.services.Service.ArgumentSelector;
import tools.mdsd.characteristics.services.Service.RegistrationType;
import tools.mdsd.characteristics.services.internal.ConditionEvaluator;
import tools.mdsd.characteristics.services.internal.NodeGraphBasedServiceStreamProviderFactory;
import tools.mdsd.characteristics.services.internal.ServiceStreamProvider;
import tools.mdsd.characteristics.services.node.ConditionGuard;
import tools.mdsd.characteristics.services.node.ConditionTest;
import tools.mdsd.characteristics.services.node.Literal;
import tools.mdsd.characteristics.services.node.Node;
import tools.mdsd.characteristics.services.node.NodeVisitor;
import tools.mdsd.characteristics.services.node.NodeVisitor.NodeVisitorAcceptor;

public class NodeGraphBasedServiceStreamProviderFactoryImpl
        implements NodeGraphBasedServiceStreamProviderFactory {

    @Override
    public <ServiceType> ServiceStreamProvider<ServiceType> createServiceStreamProvider(
            Node<ServiceType> rootNode) {
        return switchNodeType(rootNode);
    }

    protected <ServiceType> ServiceStreamProvider<ServiceType> switchNodeType(
            NodeVisitorAcceptor<ServiceType> node) {

        return node.accept(new NodeVisitor<ServiceType, ServiceStreamProvider<ServiceType>>() {
            @Override
            public <ObjectType, KeyType> ServiceStreamProvider<ServiceType> visitConditionTest(
                    ConditionTest<ObjectType, KeyType, ServiceType> toVisit) {
                return caseConditionTest(toVisit);
            }

            @Override
            public ServiceStreamProvider<ServiceType> visitLiteral(Literal<ServiceType> toVisit) {
                return new SingleServiceStreamProviderImpl<>(toVisit.getNodeLiteral());
            }

            @Override
            public ServiceStreamProvider<ServiceType> visitNode(Node<ServiceType> toVisit) {
                List<? extends Node<ServiceType>> children = toVisit.getChildren();
                if (children.size() == 1) {
                    return switchNodeType(children.get(0));
                } else if (children.size() > 1) {
                    return new CompositeServiceStreamProviderImpl<>(children.stream()
                            .map(n -> switchNodeType(n)).collect(Collectors.toList()));
                }
                return ServiceStreamProvider.empty();
            }
        });

    }

    protected <ObjectType, KeyType, ServiceType> ServiceStreamProvider<ServiceType> caseConditionTest(
            ConditionTest<ObjectType, KeyType, ServiceType> node) {
        Map<KeyType, ServiceStreamProvider<ServiceType>> collect = node.getConditionGuards()
                .stream().collect(Collectors.toMap(ConditionGuard::getKey, this::switchNodeType));
        ConditionEvaluator<ObjectType, ServiceType> evaluator =
                selectEvaluator(node.getArgumentSelector(), node.getConditionType(), collect);
        return new ConditionTestingServiceStreamProviderImpl<>(node.getArgumentSelector(),
                evaluator);
    }

    private <ObjectType, KeyType, ServiceType> ConditionEvaluator<ObjectType, ServiceType> selectEvaluator(
            ArgumentSelector<ObjectType> argumentSelector,
            RegistrationType<ObjectType, KeyType> conditionType,
            Map<KeyType, ServiceStreamProvider<ServiceType>> collect) {
        if (collect.size() == 0)
            return null;

        if ((conditionType.getKeyType() == Boolean.class) || collect.size() == 1) {
            // Create simple evaluator
            return null;
        } else {
            return new WeakHashMapBasedConditionEvaluator<ObjectType, KeyType, ServiceType>(
                    conditionType, collect);
        }
    }
}
