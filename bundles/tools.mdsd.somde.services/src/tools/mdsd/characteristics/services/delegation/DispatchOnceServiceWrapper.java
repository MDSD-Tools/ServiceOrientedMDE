package tools.mdsd.characteristics.services.delegation;

import java.util.Arrays;
import java.util.function.Function;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Pipe;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;
import tools.mdsd.characteristics.services.Service;
import tools.mdsd.characteristics.services.ServiceManagerProviderAccess;

@SuppressWarnings("restriction")
public class DispatchOnceServiceWrapper {    
    @RuntimeType
    public static <ServiceType extends Service<ServiceType>> Object intercept(
            @Pipe Function<ServiceType, Object> forwarder,
            @AllArguments @RuntimeType Object[] parameters,
            @This ServiceManagerProviderAccess<ServiceType> access) {
        return forwarder.apply(access.getManagerProvider().get().getService(parameters)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("No appropriate service of registered for object %s",
                                Arrays.toString(parameters)))));
    }
}

