package tools.mdsd.characteristics.services.node.impl;

import tools.mdsd.characteristics.services.Service.ArgumentSelector;
import tools.mdsd.characteristics.services.Service.RegistrationType;
import tools.mdsd.characteristics.services.node.Node;
import tools.mdsd.characteristics.services.node.NodeFactory;

public class NodeFactoryImpl implements NodeFactory {

    @Override
    public <NodeType> Node<NodeType> createContainerNode() {
        return new ContainerNodeImpl<NodeType>();
    }

    @Override
    public <NodeType> Node<NodeType> createNodeLiteral(NodeType nodeType) {
        return new NodeLiteralImpl<>(nodeType);
    }

    @Override
    public <KeyType, ObjectType, NodeType> Node<NodeType> createGuardedNode(
            RegistrationType<ObjectType, KeyType> conditionType,
            ArgumentSelector<ObjectType> argumentSelector, KeyType key, Node<NodeType> node) {
        Node<NodeType> result = new ConditionTestNodeImpl<ObjectType, KeyType, NodeType>(argumentSelector, conditionType);
        Node<NodeType> guard = new ConditionGuardNodeImpl<KeyType,NodeType>(key); 
        result.addChild(guard);
        guard.addChild(node);
        return result;
    }

}
