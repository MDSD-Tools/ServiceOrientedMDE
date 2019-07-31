package tools.mdsd.characteristics.services.node;

import java.util.List;
import tools.mdsd.characteristics.services.Service.ArgumentSelector;
import tools.mdsd.characteristics.services.Service.RegistrationType;

public interface ConditionTest<ObjectType, KeyType, NodeType> extends Node<NodeType> {
    
    RegistrationType<ObjectType, KeyType> getConditionType();
    ArgumentSelector<ObjectType> getArgumentSelector();
    
    List<ConditionGuard<KeyType, NodeType>> getConditionGuards();
    
    @Override
    default boolean equalsWithoutChildren(Node<NodeType> other) {
        if (other instanceof ConditionTest<?, ?, ?>) {
            return getConditionType().equals(((ConditionTest<?,?,?>) other).getConditionType())
                    && getArgumentSelector().equals(((ConditionTest<?,?,?>) other).getArgumentSelector());
        }
        return Node.super.equalsWithoutChildren(other);
    }
    
    @Override
    default <T> T accept(NodeVisitor<NodeType, T> visitor) {
        return visitor.visitConditionTest(this);
    }

}
