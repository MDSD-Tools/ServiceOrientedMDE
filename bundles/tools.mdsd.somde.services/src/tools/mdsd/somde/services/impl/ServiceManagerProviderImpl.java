package tools.mdsd.somde.services.impl;

import java.util.Set;
import javax.inject.Inject;
import javax.inject.Provider;
import tools.mdsd.somde.services.Service;
import tools.mdsd.somde.services.ServiceManager;
import tools.mdsd.somde.services.ServiceManagerFactory;

public class ServiceManagerProviderImpl<T extends Service<T>> implements Provider<ServiceManager<T>>{
    
    @Inject ServiceManagerFactory factory;
    @Inject Provider<Set<T>> servicesProvider;
    
    @Override
    public ServiceManager<T> get() {
        return factory.createServiceManager(servicesProvider.get());
    }
    
}
