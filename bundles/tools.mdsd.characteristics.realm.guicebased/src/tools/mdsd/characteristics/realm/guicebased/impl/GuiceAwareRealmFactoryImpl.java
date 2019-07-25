package tools.mdsd.characteristics.realm.guicebased.impl;

import com.google.inject.Guice;
import com.google.inject.Injector;
import tools.mdsd.characteristics.realm.CharacteristicsModelingRealm;
import tools.mdsd.characteristics.realm.impl.RealmFactoryImpl;
import tools.mdsd.characteristics.realm.guicebased.BootstrapModule;

public class GuiceAwareRealmFactoryImpl extends RealmFactoryImpl {
    
    Injector injector;
    
    public GuiceAwareRealmFactoryImpl() {
        injector = Guice.createInjector(BootstrapModule.getBootstrapModule());
    }
    
       
    @Override
    public CharacteristicsModelingRealm createCharacteristicsModelingRealm() {
        return injector.getInstance(CharacteristicsModelingRealm.class);
    }
}
