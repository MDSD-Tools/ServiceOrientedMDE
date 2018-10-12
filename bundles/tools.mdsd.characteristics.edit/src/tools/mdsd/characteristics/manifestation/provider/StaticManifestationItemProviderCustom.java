package tools.mdsd.characteristics.manifestation.provider;

import org.eclipse.emf.common.notify.AdapterFactory;

import tools.mdsd.characteristics.manifestation.ManifestationPackage;
import tools.mdsd.characteristics.manifestation.StaticManifestation;

public class StaticManifestationItemProviderCustom extends StaticManifestationItemProvider {

	public StaticManifestationItemProviderCustom(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	@Override
	public String getText(Object object) {
		if (ManifestationPackage.eINSTANCE.getStaticManifestation().isInstance(object)) {
			return ((StaticManifestation) object).getValue().toString(); 
		}
		return super.getText(object);
	}

}
