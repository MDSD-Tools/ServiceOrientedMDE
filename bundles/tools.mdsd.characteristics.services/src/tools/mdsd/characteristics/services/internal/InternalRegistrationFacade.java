package tools.mdsd.characteristics.services.internal;

import tools.mdsd.characteristics.services.ServiceManager;
import tools.mdsd.characteristics.services.ServiceRegistrationFacade;
import tools.mdsd.characteristics.services.ServiceRegistrationFacade.ParameterizedRegistration;
import tools.mdsd.characteristics.services.node.Node;
import tools.mdsd.characteristics.services.node.NodeProducer;

public interface InternalRegistrationFacade<ServiceType> {
    public interface InternalRegistrationFacadeFactory {
        <ServiceType> InternalRegistrationFacade<ServiceType> createInternalRegistrationFacade();
    }
    
    <KeyType, ObjectType> ParameterizedRegistration<ServiceType> createParameterizedRegistration(
            InternalServiceRegistration<KeyType, ObjectType, ServiceType> from);
    
    ServiceRegistrationFacade<ServiceType> createFacade();
    ServiceRegistrationFacade<ServiceType> createNestedFacade(NodeProducer<ServiceType> enclosingProducer);

    void accept(Node<ServiceType> createdNode);
    
    ServiceManager<ServiceType> buildRegistry();
    
}
