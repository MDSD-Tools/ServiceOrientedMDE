package tools.mdsd.characteristics.services.node;

public interface ConditionGuard<KeyType, NodeType> extends Node<NodeType> {

    KeyType getKey();

    @Override
    default boolean equalsWithoutChildren(Node<NodeType> other) {
        if (other instanceof ConditionGuard<?, ?>) {
            return getKey().equals(((ConditionGuard<?, ?>) other).getKey());
        }
        return Node.super.equalsWithoutChildren(other);
    }

    @Override
    default <T> T accept(NodeVisitor<NodeType, T> visitor) {
        return visitor.visitConditionGuard(this);
    }


}
