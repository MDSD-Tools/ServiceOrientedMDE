package tools.mdsd.characteristics.services;

import javax.inject.Provider;

public interface ServiceManagerProviderAccess <ServiceType> {
    Provider<ServiceManager<ServiceType>> getManagerProvider();
}
