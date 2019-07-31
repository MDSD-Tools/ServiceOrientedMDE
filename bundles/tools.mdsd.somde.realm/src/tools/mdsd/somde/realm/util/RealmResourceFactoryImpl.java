package tools.mdsd.somde.realm.util;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import tools.mdsd.somde.realm.DependencyResolvingAdapter;

public class RealmResourceFactoryImpl extends RealmResourceFactoryImplGen {
    protected static class AdapterPropagatingAdapter extends AdapterImpl {
        @Override
        public void notifyChanged(Notification msg) {
            if (msg.getFeatureID(Resource.class) == Resource.RESOURCE__RESOURCE_SET
                    && msg.getEventType() == Notification.SET && msg.getNewValue() != null
                    && ((Resource) msg.getNotifier()).getContents().size() > 0) {
                propagateAdapter(((Resource) msg.getNotifier()).getResourceSet(),
                        ((Resource) msg.getNotifier()).getContents());
            } else if (msg.getFeatureID(Resource.class) == Resource.RESOURCE__CONTENTS
                    && msg.getEventType() == Notification.ADD
                    && ((Resource) msg.getNotifier()).getResourceSet() != null) {
                propagateAdapter(((Resource) msg.getNotifier()).getResourceSet(),
                        ((Resource) msg.getNotifier()).getContents());
            }
        }

        private void propagateAdapter(ResourceSet resourceSet, EList<EObject> contents) {
            for (var object : contents) {
                var adaptersToRemove = object.eAdapters().stream()
                        .filter(DependencyResolvingAdapter.class::isInstance)
                        .collect(Collectors.toList());
                adaptersToRemove.stream().peek(object.eAdapters()::remove)
                        .filter(Predicate.not(resourceSet.eAdapters()::contains))
                        .forEach(resourceSet.eAdapters()::add);
            }
        }
    }
    
    static AdapterPropagatingAdapter PROPAGATOR = new AdapterPropagatingAdapter(); 

    @Override
    public Resource createResource(URI uri) {
        var res = super.createResource(uri);
        res.eAdapters().add(PROPAGATOR);
        return res;
    }

}
