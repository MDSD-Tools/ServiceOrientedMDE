package tools.mdsd.somde.services.node;

import tools.mdsd.somde.services.Service.ArgumentSelector;
import tools.mdsd.somde.services.Service.RegistrationType;

public interface NodeFactory {
    
    <NodeType> Node<NodeType> createContainerNode();
    
    <NodeType> Node<NodeType> createNodeLiteral(NodeType nodeType);
    
    <KeyType, ObjectType, NodeType> Node<NodeType> createGuardedNode(
            RegistrationType<ObjectType, KeyType> conditionType,
            ArgumentSelector<ObjectType> argumentSelector,
            KeyType key,
            Node<NodeType> node);

}
