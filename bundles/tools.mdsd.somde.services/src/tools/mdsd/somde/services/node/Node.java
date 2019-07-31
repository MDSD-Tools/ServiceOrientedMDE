package tools.mdsd.somde.services.node;

import java.util.List;
import tools.mdsd.somde.services.node.NodeVisitor.NodeVisitorAcceptor;

public interface Node<NodeType> extends NodeVisitorAcceptor<NodeType> {
    
    List<? extends Node<NodeType>> getChildren();
    
    void addChild(Node<NodeType> node);
    void addAllChildren(List<? extends Node<NodeType>> nodes);
    
    
    default boolean equalsWithoutChildren(Node<NodeType> other) {
        return false;
    }
    
    @Override
    default <T> T accept(NodeVisitor<NodeType, T> visitor) {
        return visitor.visitNode(this);
    }

}
