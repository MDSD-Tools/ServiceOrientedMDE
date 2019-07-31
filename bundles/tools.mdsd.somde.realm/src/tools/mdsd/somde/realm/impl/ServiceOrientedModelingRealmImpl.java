/**
 */
package tools.mdsd.somde.realm.impl;

import tools.mdsd.somde.realm.DependencyResolvingAdapter;
import tools.mdsd.somde.realm.ModelingRealmResolvingAdapterFactory;

public class ServiceOrientedModelingRealmImpl extends ServiceOrientedModelingRealmImplGen
{
    public ServiceOrientedModelingRealmImpl() {
        (new ModelingRealmResolvingAdapterFactory(this::getResolvingAdapter)).adapt(this,
                DependencyResolvingAdapter.class);

    }
    
    protected DependencyResolvingAdapter getResolvingAdapter() {
        return null;
    }
	

} //CharacteristicsModelingRealmImpl
