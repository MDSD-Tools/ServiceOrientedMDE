package tools.mdsd.characteristics.services.impl;

import tools.mdsd.characteristics.services.Service.RegistrationType;
import tools.mdsd.characteristics.services.internal.InternalRegistrationFacade;
import tools.mdsd.characteristics.services.internal.InternalServiceRegistration;
import tools.mdsd.characteristics.services.ServiceRegistrationFacade;
import tools.mdsd.characteristics.services.node.NodeFactory;
import tools.mdsd.characteristics.services.node.NodeProducer;

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
