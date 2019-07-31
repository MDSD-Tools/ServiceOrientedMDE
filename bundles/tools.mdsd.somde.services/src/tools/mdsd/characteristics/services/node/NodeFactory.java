package tools.mdsd.characteristics.services.node;

import tools.mdsd.characteristics.services.Service.ArgumentSelector;
import tools.mdsd.characteristics.services.Service.RegistrationType;

public interface NodeFactory {
    
    <NodeType> Node<NodeType> createContainerNode();
    
    <NodeType> Node<NodeType> createNodeLiteral(NodeType nodeType);
    
    <KeyType, ObjectType, NodeType> Node<NodeType> createGuardedNode(
            RegistrationType<ObjectType, KeyType> conditionType,
            ArgumentSelector<ObjectType> argumentSelector,
            KeyType key,
            Node<NodeType> node);

}
