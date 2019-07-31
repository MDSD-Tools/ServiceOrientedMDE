package tools.mdsd.somde.services.impl;

import tools.mdsd.somde.services.ServiceManager;
import tools.mdsd.somde.services.internal.InternalRegistrationFacade;
import tools.mdsd.somde.services.node.Node;
import tools.mdsd.somde.services.node.NodeFactory;
import tools.mdsd.somde.services.node.NodeProducer;

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
