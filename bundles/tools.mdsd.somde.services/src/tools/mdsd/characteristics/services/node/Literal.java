package tools.mdsd.characteristics.services.node;

import java.util.Collections;
import java.util.List;

public interface Literal<NodeType> extends Node<NodeType> {
    
    NodeType getNodeLiteral();
    
    @Override
    default List<? extends Node<NodeType>> getChildren() {
        return Collections.emptyList();
    }
    
    @Override
    default void addChild(Node<NodeType> node) {
        throw new UnsupportedOperationException("Nodes can not be added to leafs of a tree");
    }
    
    @Override
    default void addAllChildren(List<? extends Node<NodeType>> nodes) {
        throw new UnsupportedOperationException("Nodes can not be added to leafs of a tree");
    }
    
    @Override
    default boolean equalsWithoutChildren(Node<NodeType> other) {
        if (other instanceof Literal<?>) {
            return getNodeLiteral().equals(((Literal<?>) other).getNodeLiteral());
        }
        return Node.super.equalsWithoutChildren(other);
    }
    
    @Override
    default <T> T accept(NodeVisitor<NodeType, T> visitor) {
        return visitor.visitLiteral(this);
    }

}
