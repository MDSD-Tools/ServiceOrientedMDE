package tools.mdsd.characteristics.services;

import tools.mdsd.characteristics.services.Service.ArgumentSelector;
import tools.mdsd.characteristics.services.Service.RegistrationType;

public interface ServiceRegistrationFacade<ServiceType> {

    public interface ParameterizedRegistration<ServiceType> {
        void register(ServiceType service);

        ServiceRegistrationFacade<ServiceType> and();
    }

    public interface OngoingRegistration<ServiceType, KeyType, ObjectType> {
        OngoingRegistration<ServiceType, KeyType, ObjectType> when(
                ArgumentSelector<ObjectType> parameter);

        ParameterizedRegistration<ServiceType> matches(KeyType key);
    }

    /**
     * Starts a conditional registration.
     * 
     * @param registrationType The RegistrationType which should be used to register the service
     *        with the service management.
     * @return A facade to continue the registration.
     */
    <KeyType, ObjectType> OngoingRegistration<ServiceType, KeyType, ObjectType> using(
            RegistrationType<ObjectType, KeyType> registrationType);

}
