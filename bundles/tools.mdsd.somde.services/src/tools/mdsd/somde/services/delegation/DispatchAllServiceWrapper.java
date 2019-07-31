package tools.mdsd.somde.services.delegation;

import java.util.function.Function;
import java.util.stream.Collectors;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Pipe;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;
import tools.mdsd.somde.services.Service;
import tools.mdsd.somde.services.ServiceManagerProviderAccess;


@SuppressWarnings("restriction")
public class DispatchAllServiceWrapper {
    @RuntimeType
    public static <ServiceType extends Service<ServiceType>> Object intercept(
            @Pipe Function<ServiceType, Object> forwarder,
            @AllArguments @RuntimeType Object[] parameters,
            @This ServiceManagerProviderAccess<ServiceType> access) {
        return access.getManagerProvider().get().collectServices(parameters).stream().map(forwarder)
                .collect(Collectors.toList());
    }
}
