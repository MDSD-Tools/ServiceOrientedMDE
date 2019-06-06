package tools.mdsd.characteristics.services.impl;

import tools.mdsd.characteristics.services.Service;
import tools.mdsd.characteristics.services.ServiceManager;
import tools.mdsd.characteristics.services.ServiceManagerFactory;
import tools.mdsd.characteristics.services.ServiceRegistrationFacade;
import tools.mdsd.characteristics.services.internal.InternalRegistrationFacade;
import tools.mdsd.characteristics.services.internal.InternalRegistrationFacade.InternalRegistrationFacadeFactory;

public class ServiceManagerFactoryImpl implements ServiceManagerFactory {

    private InternalRegistrationFacadeFactory facadeFactory;

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
