package tools.mdsd.characteristics.services.node.impl;

import java.util.List;
import tools.mdsd.characteristics.services.Service.ArgumentSelector;
import tools.mdsd.characteristics.services.Service.RegistrationType;
import tools.mdsd.characteristics.services.node.ConditionGuard;
import tools.mdsd.characteristics.services.node.ConditionTest;
import tools.mdsd.characteristics.services.node.Node;

public class ConditionTestNodeImpl<ObjectType, KeyType, NodeType> extends
        AbstractComposedNodeImpl<NodeType> implements ConditionTest<ObjectType, KeyType, NodeType> {

    private ArgumentSelector<ObjectType> argumentSelector;
    private RegistrationType<ObjectType, KeyType> registrationType;

    public ConditionTestNodeImpl(ArgumentSelector<ObjectType> argumentSelector,
            RegistrationType<ObjectType, KeyType> registrationType) {
        this.argumentSelector = argumentSelector;
        this.registrationType = registrationType;
    }

    @Override
    public RegistrationType<ObjectType, KeyType> getConditionType() {
        return registrationType;
    }

    @Override
    public ArgumentSelector<ObjectType> getArgumentSelector() {
        return argumentSelector;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public List<ConditionGuard<KeyType, NodeType>> getConditionGuards() {
        return (List) children;
    }
    
    @Override
    protected void doAddChild(Node<NodeType> node) {
        if (node instanceof ConditionGuard<?, ?>) {
            children.add(node);
        }
        
    }




}
