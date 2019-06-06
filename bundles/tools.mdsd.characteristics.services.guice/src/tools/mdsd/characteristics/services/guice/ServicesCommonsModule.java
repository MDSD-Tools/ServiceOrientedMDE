package tools.mdsd.characteristics.services.guice;

import com.google.inject.AbstractModule;
import tools.mdsd.characteristics.services.ServiceManagerFactory;
import tools.mdsd.characteristics.services.ServiceWrapperFactory;
import tools.mdsd.characteristics.services.impl.ServiceManagerFactoryImpl;
import tools.mdsd.characteristics.services.impl.ServiceWrapperImpl;
import tools.mdsd.characteristics.services.internal.InternalRegistrationFacade.InternalRegistrationFacadeFactory;
import tools.mdsd.characteristics.services.internal.NodeGraphBasedServiceStreamProviderFactory;
import tools.mdsd.characteristics.services.internal.impl.InternalRegistrationFacadeFactoryImpl;
import tools.mdsd.characteristics.services.internal.impl.NodeGraphBasedServiceStreamProviderFactoryImpl;
import tools.mdsd.characteristics.services.node.NodeFactory;
import tools.mdsd.characteristics.services.node.impl.NodeFactoryImpl;

public class ServicesCommonsModule extends AbstractModule {
    
    @Override
    protected void configure() {
        bind(ServiceManagerFactory.class).to(ServiceManagerFactoryImpl.class);
        bind(ServiceWrapperFactory.class).toInstance(ServiceWrapperImpl.FACTORY);
        bind(InternalRegistrationFacadeFactory.class).to(InternalRegistrationFacadeFactoryImpl.class);
        bind(NodeFactory.class).to(NodeFactoryImpl.class);
        bind(NodeGraphBasedServiceStreamProviderFactory.class).to(NodeGraphBasedServiceStreamProviderFactoryImpl.class);
    }

}
