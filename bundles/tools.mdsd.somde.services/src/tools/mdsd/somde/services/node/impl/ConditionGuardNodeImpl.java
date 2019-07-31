package tools.mdsd.somde.services.node.impl;

import tools.mdsd.somde.services.node.ConditionGuard;
import tools.mdsd.somde.services.node.Node;

public class ConditionGuardNodeImpl<KeyType, NodeType> extends AbstractComposedNodeImpl<NodeType>
        implements ConditionGuard<KeyType, NodeType> {

    private KeyType key;

    public ConditionGuardNodeImpl(KeyType key) {
        this.key = key;
    }


    @Override
    public KeyType getKey() {
        return key;
    }

    @Override
    protected void doAddChild(Node<NodeType> node) {
        children.add(node);
    }

}
