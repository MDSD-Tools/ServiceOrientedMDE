package tools.mdsd.somde.services.internal;

import tools.mdsd.somde.services.Defaults;
import tools.mdsd.somde.services.Service.ArgumentSelector;
import tools.mdsd.somde.services.Service.RegistrationType;
import tools.mdsd.somde.services.ServiceRegistrationFacade.OngoingRegistration;
import tools.mdsd.somde.services.ServiceRegistrationFacade.ParameterizedRegistration;
import tools.mdsd.somde.services.node.Node;
import tools.mdsd.somde.services.node.NodeFactory;
import tools.mdsd.somde.services.node.NodeProducer;

public class InternalServiceRegistration<KeyType, ObjectType, ServiceType>
        implements OngoingRegistration<ServiceType, KeyType, ObjectType>,
        NodeProducer<ServiceType> {

    private ArgumentSelector<ObjectType> argumentSelector = Defaults.Argument(0);
    private RegistrationType<ObjectType, KeyType> registrationType;
    private KeyType key;
    private InternalRegistrationFacade<ServiceType> internalFacade;

    public InternalServiceRegistration(RegistrationType<ObjectType, KeyType> registrationType,
            InternalRegistrationFacade<ServiceType> internalFacade) {
        this.registrationType = registrationType;
        this.internalFacade = internalFacade;
    }

    @Override
    public OngoingRegistration<ServiceType, KeyType, ObjectType> when(
            ArgumentSelector<ObjectType> parameter) {
        this.argumentSelector = parameter;
        return this;
    }

    @Override
    public ParameterizedRegistration<ServiceType> matches(KeyType key) {
        this.key = key;
        return internalFacade.createParameterizedRegistration(this);
    }

    @Override
    public <T> Node<T> createNode(NodeFactory factory, Node<T> nestedNode) {
        return factory.createGuardedNode(registrationType, argumentSelector, key, nestedNode);
    }
}
