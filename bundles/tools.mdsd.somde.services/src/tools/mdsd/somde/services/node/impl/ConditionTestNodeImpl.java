package tools.mdsd.somde.services.node.impl;

import java.util.List;
import tools.mdsd.somde.services.Service.ArgumentSelector;
import tools.mdsd.somde.services.Service.RegistrationType;
import tools.mdsd.somde.services.node.ConditionGuard;
import tools.mdsd.somde.services.node.ConditionTest;
import tools.mdsd.somde.services.node.Node;

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
