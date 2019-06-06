package tools.mdsd.characteristics.services.basic;

import static tools.mdsd.characteristics.services.Defaults.Argument;
import static tools.mdsd.characteristics.services.Defaults.ECLASS_FILTER;
import static tools.mdsd.characteristics.services.Defaults.OBJECT_IDENTITY;
import tools.mdsd.characteristics.properties.basic.BasicPackage;
import tools.mdsd.characteristics.properties.basic.EDataTypeBased;
import tools.mdsd.characteristics.services.ServiceRegistrationFacade;
import tools.mdsd.characteristics.services.api.ValueTypePropertyProvider;
import tools.mdsd.characteristics.valuetype.EDataTypeValueType;
import tools.mdsd.characteristics.valuetype.ValueType;
import tools.mdsd.characteristics.valuetype.ValuetypePackage;

public class EDataTypeBasedValueTypePropertiesProvider implements ValueTypePropertyProvider {

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getProperty(Class<T> propertyType, ValueType valueType) {
        assert propertyType == EDataTypeBased.class;

        EDataTypeBased result = () -> ((EDataTypeValueType) valueType).getBaseType();
        return (T) result;
    }

    @Override
    public void registerService(ServiceRegistrationFacade<ValueTypePropertyProvider> facade) {
        facade.using(ECLASS_FILTER).when(Argument(1))
                .matches(ValuetypePackage.eINSTANCE.getEDataTypeValueType())
            .and()
                .using(OBJECT_IDENTITY).when(Argument(0))
                .matches(BasicPackage.eINSTANCE.getEDataTypeBased().getClass()).register(this);
    }
}
