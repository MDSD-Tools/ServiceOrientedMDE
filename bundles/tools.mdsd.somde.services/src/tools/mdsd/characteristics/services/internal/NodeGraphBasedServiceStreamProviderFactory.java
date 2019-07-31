package tools.mdsd.characteristics.services.internal;

import tools.mdsd.characteristics.services.node.Node;

public interface NodeGraphBasedServiceStreamProviderFactory {

    <ServiceType> ServiceStreamProvider<ServiceType> createServiceStreamProvider(
            Node<ServiceType> rootNode);

}
