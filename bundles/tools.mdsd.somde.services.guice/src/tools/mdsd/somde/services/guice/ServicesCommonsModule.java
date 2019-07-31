package tools.mdsd.somde.services.guice;

import com.google.inject.AbstractModule;
import tools.mdsd.somde.services.ServiceManagerFactory;
import tools.mdsd.somde.services.impl.ServiceManagerFactoryImpl;
import tools.mdsd.somde.services.internal.NodeGraphBasedServiceStreamProviderFactory;
import tools.mdsd.somde.services.internal.InternalRegistrationFacade.InternalRegistrationFacadeFactory;
import tools.mdsd.somde.services.internal.impl.InternalRegistrationFacadeFactoryImpl;
import tools.mdsd.somde.services.internal.impl.NodeGraphBasedServiceStreamProviderFactoryImpl;
import tools.mdsd.somde.services.node.NodeFactory;
import tools.mdsd.somde.services.node.impl.NodeFactoryImpl;

public class ServicesCommonsModule extends AbstractModule {
    
    @Override
    protected void configure() {
        bind(ServiceManagerFactory.class).to(ServiceManagerFactoryImpl.class);
        bind(InternalRegistrationFacadeFactory.class).to(InternalRegistrationFacadeFactoryImpl.class);
        bind(NodeFactory.class).to(NodeFactoryImpl.class);
        bind(NodeGraphBasedServiceStreamProviderFactory.class).to(NodeGraphBasedServiceStreamProviderFactoryImpl.class);
    }

}
