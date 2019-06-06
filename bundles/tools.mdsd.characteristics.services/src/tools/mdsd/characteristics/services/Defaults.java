package tools.mdsd.characteristics.services;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import tools.mdsd.characteristics.services.Service.ArgumentSelector;
import tools.mdsd.characteristics.services.Service.RegistrationType;
import tools.mdsd.modelingfoundations.identifier.Identifier;

public class Defaults {

    private Defaults() {}

    public static final RegistrationType<Object, Boolean> ALWAYS_TRUE =
            new RegistrationType<>(Boolean.class, x -> Boolean.TRUE);
    public static final RegistrationType<EObject, EClass> ECLASS_FILTER =
            new RegistrationType<>(EClass.class, EObject::eClass);
    public static final RegistrationType<Identifier, String> IDENTIFIER_FILTER =
            new RegistrationType<>(String.class, Identifier::getId);
    public static final RegistrationType<Object, String> CLASSNAME_FILTER =
            new RegistrationType<>(String.class, x -> x.getClass().getName());
    public static final RegistrationType<Object, Object> OBJECT_IDENTITY =
            new RegistrationType<>(Object.class, x -> x);

    public static final ArgumentSelector<Object> ARGUMENT_0 = args -> args[0];
    public static final ArgumentSelector<Object> ARGUMENT_1 = args -> args[1];

    @SuppressWarnings("unchecked")
    public static <T> ArgumentSelector<T> Argument(int argumentId) {
        switch (argumentId) {
            case 0:
                return (ArgumentSelector<T>) ARGUMENT_0;
            case 1:
                return (ArgumentSelector<T>) ARGUMENT_1;
            default:
                throw new UnsupportedOperationException(
                        "Dispatch only by first two parameters possible");
        }
    }

    public static final String SERVICE_TYPE_EXTENSION_POINT_ID =
            "tools.mdsd.characteristics.services.service";
    public static final String SERVICE_TYPE_EXTENSION_SERVICE_ATTRIBUTE = "serviceType";
    public static final String SERVICE_IMPLEMENTATION_EXTENSION_POINT_ID =
            "tools.mdsd.characteristics.services.implementation";
    public static final String SERVICE_IMPLEMENTATION_SERVICE_ATTRIBUTE = "serviceType";
    public static final String SERVICE_IMPLEMENTATION_CLASS_ATTRIBUTE = "serviceType";


}
