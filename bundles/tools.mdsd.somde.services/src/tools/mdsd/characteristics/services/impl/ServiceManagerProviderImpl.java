package tools.mdsd.characteristics.services.impl;

import java.util.Set;
import javax.inject.Inject;
import javax.inject.Provider;
import tools.mdsd.characteristics.services.Service;
import tools.mdsd.characteristics.services.ServiceManager;
import tools.mdsd.characteristics.services.ServiceManagerFactory;

public class ServiceManagerProviderImpl<T extends Service<T>> implements Provider<ServiceManager<T>>{
    
    @Inject ServiceManagerFactory factory;
    @Inject Provider<Set<T>> servicesProvider;
    
    @Override
    public ServiceManager<T> get() {
        return factory.createServiceManager(servicesProvider.get());
    }
    
}
