package tools.mdsd.somde.realm.guicebased.impl;

import com.google.inject.Guice;
import com.google.inject.Injector;
import tools.mdsd.somde.realm.ServiceOrientedModelingRealm;
import tools.mdsd.somde.realm.guicebased.BootstrapModule;
import tools.mdsd.somde.realm.impl.RealmFactoryImpl;

public class GuiceAwareRealmFactoryImpl extends RealmFactoryImpl {
    
    Injector injector;
    
    public GuiceAwareRealmFactoryImpl() {
        injector = Guice.createInjector(BootstrapModule.getBootstrapModule());
    }
    
       
    @Override
    public ServiceOrientedModelingRealm createServiceOrientedModelingRealm() {
        return injector.getInstance(ServiceOrientedModelingRealm.class);
    }
}
