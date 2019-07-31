package tools.mdsd.somde.services.internal;

import tools.mdsd.somde.services.ServiceManager;
import tools.mdsd.somde.services.ServiceRegistrationFacade;
import tools.mdsd.somde.services.ServiceRegistrationFacade.ParameterizedRegistration;
import tools.mdsd.somde.services.node.Node;
import tools.mdsd.somde.services.node.NodeProducer;

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
