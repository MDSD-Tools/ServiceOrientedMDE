package tools.mdsd.somde.services.internal;

import tools.mdsd.somde.services.node.Node;

public interface NodeGraphBasedServiceStreamProviderFactory {

    <ServiceType> ServiceStreamProvider<ServiceType> createServiceStreamProvider(
            Node<ServiceType> rootNode);

}
