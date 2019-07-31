package tools.mdsd.somde.services.impl;

import javax.inject.Inject;
import tools.mdsd.somde.services.Service;
import tools.mdsd.somde.services.ServiceManager;
import tools.mdsd.somde.services.ServiceManagerFactory;
import tools.mdsd.somde.services.ServiceRegistrationFacade;
import tools.mdsd.somde.services.internal.InternalRegistrationFacade;
import tools.mdsd.somde.services.internal.InternalRegistrationFacade.InternalRegistrationFacadeFactory;

public class ServiceManagerFactoryImpl implements ServiceManagerFactory {

    private InternalRegistrationFacadeFactory facadeFactory;

    @Inject
    public ServiceManagerFactoryImpl(InternalRegistrationFacadeFactory facadeFactory) {
        this.facadeFactory = facadeFactory;
    }

    public <T extends Service<T>> ServiceManager<T> createServiceManager(Iterable<T> services) {
        InternalRegistrationFacade<T> internalFacade =
                facadeFactory.createInternalRegistrationFacade();

        ServiceRegistrationFacade<T> facade = internalFacade.createFacade();
        for (T service : services) {
            service.registerService(facade);
        }

        return internalFacade.buildRegistry();
    }

}
