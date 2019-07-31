package tools.mdsd.somde.services.node;

public interface NodeVisitor<NodeType, T> {
    public interface NodeVisitorAcceptor<NodeType> {
        <T> T accept(NodeVisitor<NodeType, T> visitor);
    }
    
    default <ObjectType, KeyType> T visitConditionGuard(ConditionGuard<KeyType, NodeType> toVisit) {
        return visitNode(toVisit);
    }
    
    default <ObjectType, KeyType> T visitConditionTest(ConditionTest<ObjectType, KeyType, NodeType> toVisit) {
        return visitNode(toVisit);
    }
    
    default T visitLiteral(Literal<NodeType> toVisit) {
        return visitNode(toVisit);
    }
    
    default T visitNode(Node<NodeType> nodeType) {
        throw new UnsupportedOperationException("Unknown node type encountered");
    }

}
