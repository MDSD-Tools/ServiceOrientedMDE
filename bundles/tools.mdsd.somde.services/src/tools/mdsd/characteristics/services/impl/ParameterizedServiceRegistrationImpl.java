package tools.mdsd.characteristics.services.impl;

import tools.mdsd.characteristics.services.ServiceRegistrationFacade;
import tools.mdsd.characteristics.services.ServiceRegistrationFacade.ParameterizedRegistration;
import tools.mdsd.characteristics.services.internal.InternalRegistrationFacade;
import tools.mdsd.characteristics.services.node.NodeFactory;
import tools.mdsd.characteristics.services.node.NodeProducer;

public class ParameterizedServiceRegistrationImpl<ServiceType>
        implements ParameterizedRegistration<ServiceType> {

    private NodeProducer<ServiceType> nodeProducer;
    private NodeFactory nodeFactory;
    private InternalRegistrationFacade<ServiceType> internalFacade;

    public ParameterizedServiceRegistrationImpl(NodeProducer<ServiceType> nodeProducer,
            NodeFactory nodeFactory,
            InternalRegistrationFacade<ServiceType> internalFacade) {
        this.nodeProducer = nodeProducer;
        this.nodeFactory = nodeFactory;
        this.internalFacade = internalFacade;
    }

    @Override
    public void register(ServiceType service) {
        internalFacade.accept(nodeProducer.createNode(nodeFactory, nodeFactory.createNodeLiteral(service)));
    }

    @Override
    public ServiceRegistrationFacade<ServiceType> and() {
        return internalFacade.createNestedFacade(nodeProducer);
    }

}
