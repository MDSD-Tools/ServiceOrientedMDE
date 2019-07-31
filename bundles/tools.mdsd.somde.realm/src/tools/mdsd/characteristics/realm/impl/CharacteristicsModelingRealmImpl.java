/**
 */
package tools.mdsd.characteristics.realm.impl;

import tools.mdsd.characteristics.realm.DependencyResolvingAdapter;
import tools.mdsd.characteristics.realm.ModelingRealmResolvingAdapterFactory;

public class CharacteristicsModelingRealmImpl extends CharacteristicsModelingRealmImplGen
{
    public CharacteristicsModelingRealmImpl() {
        (new ModelingRealmResolvingAdapterFactory(this::getResolvingAdapter)).adapt(this,
                DependencyResolvingAdapter.class);

    }
    
    protected DependencyResolvingAdapter getResolvingAdapter() {
        return null;
    }
	

} //CharacteristicsModelingRealmImpl
