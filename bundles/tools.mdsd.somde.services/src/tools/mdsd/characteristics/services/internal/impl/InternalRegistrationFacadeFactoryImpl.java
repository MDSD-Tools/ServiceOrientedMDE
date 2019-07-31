package tools.mdsd.characteristics.services.internal.impl;

import javax.inject.Inject;
import tools.mdsd.characteristics.services.internal.InternalRegistrationFacade;
import tools.mdsd.characteristics.services.internal.InternalRegistrationFacade.InternalRegistrationFacadeFactory;
import tools.mdsd.characteristics.services.internal.NodeGraphBasedServiceStreamProviderFactory;
import tools.mdsd.characteristics.services.node.NodeFactory;

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
