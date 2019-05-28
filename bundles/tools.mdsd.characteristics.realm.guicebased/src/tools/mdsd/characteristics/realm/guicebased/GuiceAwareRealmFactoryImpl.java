package tools.mdsd.characteristics.realm.guicebased;

import tools.mdsd.characteristics.realm.CharacteristicsModelingRealm;
import tools.mdsd.characteristics.realm.guicebased.impl.GuiceAwareCharacteristicsModelingRealmImpl;
import tools.mdsd.characteristics.realm.impl.RealmFactoryImpl;

public class GuiceAwareRealmFactoryImpl extends RealmFactoryImpl {
    @Override
    public CharacteristicsModelingRealm createCharacteristicsModelingRealm() {
        return new GuiceAwareCharacteristicsModelingRealmImpl();
    }
}
