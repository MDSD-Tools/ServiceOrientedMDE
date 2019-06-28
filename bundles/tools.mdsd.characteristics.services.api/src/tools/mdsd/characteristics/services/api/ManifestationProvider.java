package tools.mdsd.characteristics.services.api;

import tools.mdsd.characteristics.binding.CharacteristicBinding;
import tools.mdsd.characteristics.manifestation.Manifestation;

public interface ManifestationProvider {
    
    Manifestation provideManifestation(CharacteristicBinding binding);

}
