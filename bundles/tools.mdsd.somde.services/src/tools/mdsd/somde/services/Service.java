package tools.mdsd.somde.services;

import java.util.function.Function;

public interface Service<ST extends Service<ST>> {
    
    public class RegistrationType<ObjectType, KeyType> implements Function<ObjectType, KeyType> {
        private Class<KeyType> keyType;
        private Function<ObjectType, KeyType> keyExtractor;

        public RegistrationType(Class<KeyType> keyType, Function<ObjectType, KeyType> keyExtractor) {
            this.keyType = keyType;
            this.keyExtractor = keyExtractor;
        }
        
        public Class<KeyType> getKeyType() {
            return keyType;
        }

        @Override
        public KeyType apply(ObjectType t) {
            return keyExtractor.apply(t);
        }
    }
    
    @FunctionalInterface
    public interface ArgumentSelector<ObjectType> extends Function<Object[], ObjectType> {
    }
    

    void registerService(ServiceRegistrationFacade<ST> facade);
}
