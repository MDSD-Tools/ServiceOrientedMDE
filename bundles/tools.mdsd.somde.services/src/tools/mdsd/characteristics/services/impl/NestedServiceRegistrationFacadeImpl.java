package tools.mdsd.characteristics.services.impl;

import tools.mdsd.characteristics.services.ServiceManager;
import tools.mdsd.characteristics.services.internal.InternalRegistrationFacade;
import tools.mdsd.characteristics.services.node.Node;
import tools.mdsd.characteristics.services.node.NodeFactory;
import tools.mdsd.characteristics.services.node.NodeProducer;

public class NestedServiceRegistrationFacadeImpl<ServiceType> extends AbstractServiceRegistrationFacadeImpl<ServiceType> {

    protected InternalRegistrationFacade<ServiceType> internalFacade;
    protected NodeProducer<ServiceType> enclosingProducer;

    public NestedServiceRegistrationFacadeImpl(NodeProducer<ServiceType> enclosingProducer,
            NodeFactory nodeFactory,
            InternalRegistrationFacade<ServiceType> internalFacade) {
        super(nodeFactory);
        this.enclosingProducer = enclosingProducer;
        this.internalFacade = internalFacade;
    }

    @Override
    public void accept(Node<ServiceType> createdNode) {
        internalFacade.accept(enclosingProducer.createNode(nodeFactory, createdNode));
    }
    
    @Override
    public ServiceManager<ServiceType> buildRegistry() {
        return internalFacade.buildRegistry();
    }

}
