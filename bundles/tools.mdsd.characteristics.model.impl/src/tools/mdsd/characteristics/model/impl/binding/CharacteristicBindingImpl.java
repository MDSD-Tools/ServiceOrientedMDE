package tools.mdsd.characteristics.model.impl.binding;

import javax.inject.Inject;
import tools.mdsd.characteristics.manifestation.Manifestation;
import tools.mdsd.characteristics.services.api.ManifestationProvider;
import tools.mdsd.characteristics.services.api.ManifestationProviderSelector;

public class CharacteristicBindingImpl
        extends tools.mdsd.characteristics.binding.impl.CharacteristicBindingImpl {

    @Inject
    protected ManifestationProviderSelector providerSelector;

    private ManifestationProvider manifestationProvider;

    @Override
    public Manifestation getManifestation() {
        if (manifestationProvider == null) {
            if (providerSelector != null) {
                manifestationProvider = providerSelector
                        .selectProvider(getCharacteristic().computeCharacteristic(), getTarget(), this);
            } else {
                return super.getManifestation();
            }
        }
        return manifestationProvider.provideManifestation(this);
    }

}
