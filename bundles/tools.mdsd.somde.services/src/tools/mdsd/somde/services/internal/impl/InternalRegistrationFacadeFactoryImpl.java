package tools.mdsd.somde.services.internal.impl;

import javax.inject.Inject;
import tools.mdsd.somde.services.internal.InternalRegistrationFacade;
import tools.mdsd.somde.services.internal.NodeGraphBasedServiceStreamProviderFactory;
import tools.mdsd.somde.services.internal.InternalRegistrationFacade.InternalRegistrationFacadeFactory;
import tools.mdsd.somde.services.node.NodeFactory;

public class InternalRegistrationFacadeFactoryImpl implements InternalRegistrationFacadeFactory {

    private NodeFactory nodeFactory;
    private NodeGraphBasedServiceStreamProviderFactory providerFactory;

    @Inject
    public InternalRegistrationFacadeFactoryImpl(NodeFactory nodeFactory,
            NodeGraphBasedServiceStreamProviderFactory providerFactory) {
        this.nodeFactory = nodeFactory;
        this.providerFactory = providerFactory;
    }

    @Override
    public <ServiceType> InternalRegistrationFacade<ServiceType> createInternalRegistrationFacade() {
        return new RootNodeServiceRegistrationFacadeImpl<ServiceType>(nodeFactory, providerFactory);
    }

}
