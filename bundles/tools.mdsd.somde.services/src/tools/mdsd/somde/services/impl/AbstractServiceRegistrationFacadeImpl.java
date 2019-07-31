package tools.mdsd.somde.services.impl;

import tools.mdsd.somde.services.ServiceRegistrationFacade;
import tools.mdsd.somde.services.Service.RegistrationType;
import tools.mdsd.somde.services.internal.InternalRegistrationFacade;
import tools.mdsd.somde.services.internal.InternalServiceRegistration;
import tools.mdsd.somde.services.node.NodeFactory;
import tools.mdsd.somde.services.node.NodeProducer;

public abstract class AbstractServiceRegistrationFacadeImpl<ServiceType>
        implements ServiceRegistrationFacade<ServiceType>, InternalRegistrationFacade<ServiceType> {

    protected NodeFactory nodeFactory;

    public AbstractServiceRegistrationFacadeImpl(NodeFactory nodeFactory) {
        this.nodeFactory = nodeFactory;
    }

    @Override
    public <KEY, OBJ> OngoingRegistration<ServiceType, KEY, OBJ> using(
            RegistrationType<OBJ, KEY> registrationType) {
        return new InternalServiceRegistration<>(registrationType, this);
    }

    @Override
    public <KeyType, ObjectType> ParameterizedRegistration<ServiceType> createParameterizedRegistration(
            InternalServiceRegistration<KeyType, ObjectType, ServiceType> from) {
        return new ParameterizedServiceRegistrationImpl<>(from, nodeFactory, this);
    }

    @Override
    public ServiceRegistrationFacade<ServiceType> createFacade() {
        return this;
    }

    @Override
    public ServiceRegistrationFacade<ServiceType> createNestedFacade(
            NodeProducer<ServiceType> enclosingProducer) {
        return new NestedServiceRegistrationFacadeImpl<>(enclosingProducer, nodeFactory, this);
    }
}
