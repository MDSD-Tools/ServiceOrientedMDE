package tools.mdsd.somde.services.internal.impl;

import java.util.stream.Stream;
import tools.mdsd.somde.services.ServiceManager;
import tools.mdsd.somde.services.impl.AbstractServiceRegistrationFacadeImpl;
import tools.mdsd.somde.services.internal.NodeGraphBasedServiceStreamProviderFactory;
import tools.mdsd.somde.services.internal.ServiceStreamProvider;
import tools.mdsd.somde.services.internal.ServiceStreamProvider.ComposedServiceManager;
import tools.mdsd.somde.services.node.Node;
import tools.mdsd.somde.services.node.NodeFactory;

public class RootNodeServiceRegistrationFacadeImpl<ServiceType> extends AbstractServiceRegistrationFacadeImpl<ServiceType> {

    private Node<ServiceType> rootNode;
    private NodeGraphBasedServiceStreamProviderFactory providerFactory;

    public RootNodeServiceRegistrationFacadeImpl(NodeFactory nodeFactory, NodeGraphBasedServiceStreamProviderFactory providerFactory) {
        super(nodeFactory);
        this.providerFactory = providerFactory;
    }

    @Override
    public void accept(Node<ServiceType> createdNode) {
        if (rootNode == null) {
            rootNode = nodeFactory.createContainerNode();
        }
        rootNode.addChild(createdNode);
    }
    
    @Override
    public ServiceManager<ServiceType> buildRegistry() {
        return new ComposedServiceManager<ServiceType>() {
            ServiceStreamProvider<ServiceType> provider = providerFactory.createServiceStreamProvider(rootNode);

            @Override
            public Stream<ServiceType>getServiceManagers(Object... parameters) {
                return provider.getServiceManagers(parameters);
            }
            
        };
    }
}
