package tools.mdsd.somde.services.guice;

import javax.inject.Provider;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.util.Types;
import tools.mdsd.somde.services.Service;
import tools.mdsd.somde.services.ServiceManager;
import tools.mdsd.somde.services.impl.ServiceManagerProviderImpl;
import tools.mdsd.somde.services.impl.ServiceWrapperImpl;

public class ServiceWrapperDelegationModule<ServiceType extends Service<ServiceType>>
        extends AbstractModule {

    private final Class<ServiceType> serviceClass;

    public ServiceWrapperDelegationModule(Class<ServiceType> serviceClass) {
        this.serviceClass = serviceClass;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected void configure() {
        bind((TypeLiteral) TypeLiteral
                .get(Types.newParameterizedType(ServiceManager.class, serviceClass)))
                        .toProvider((TypeLiteral<Provider<ServiceManager<ServiceType>>>) TypeLiteral
                                .get(Types.newParameterizedType(ServiceManagerProviderImpl.class,
                                        serviceClass)));
        bind(serviceClass).to(ServiceWrapperImpl.of(serviceClass));
    }

}
