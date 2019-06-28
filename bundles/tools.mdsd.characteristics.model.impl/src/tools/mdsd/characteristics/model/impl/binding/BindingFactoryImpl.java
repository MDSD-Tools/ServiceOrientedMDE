package tools.mdsd.characteristics.model.impl.binding;

import tools.mdsd.characteristics.binding.CharacteristicBinding;

public class BindingFactoryImpl extends tools.mdsd.characteristics.binding.impl.BindingFactoryImpl {

    @Override
    public CharacteristicBinding createCharacteristicBinding() {
        return new CharacteristicBindingImpl();
    }

}
