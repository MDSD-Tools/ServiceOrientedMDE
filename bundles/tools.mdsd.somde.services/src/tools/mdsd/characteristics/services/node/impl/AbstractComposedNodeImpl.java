package tools.mdsd.characteristics.services.node.impl;

import java.util.LinkedList;
import java.util.List;
import tools.mdsd.characteristics.services.node.Node;

public abstract class AbstractComposedNodeImpl<NodeType> implements Node<NodeType> {
    List<Node<NodeType>> children = new LinkedList<Node<NodeType>>();

    @Override
    public List<Node<NodeType>> getChildren() {
        return children;
    }

    @Override
    public void addChild(Node<NodeType> node) {
        for (Node<NodeType> child : children) {
            if (child.equalsWithoutChildren(node)) {
                child.addAllChildren(node.getChildren());
            }
        }
        doAddChild(node);
    }
    
    abstract protected void doAddChild(Node<NodeType> node);

    @Override
    public void addAllChildren(List<? extends Node<NodeType>> nodes) {
        nodes.forEach(this::addChild);
    }


}
