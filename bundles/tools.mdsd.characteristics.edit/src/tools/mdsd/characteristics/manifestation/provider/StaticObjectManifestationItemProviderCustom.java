package tools.mdsd.characteristics.manifestation.provider;

import java.util.List;
import java.util.WeakHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;

import com.google.common.collect.Streams;

import tools.mdsd.characteristics.manifestation.ManifestationPackage;

public class StaticObjectManifestationItemProviderCustom extends StaticObjectManifestationItemProvider {
	protected WeakHashMap<EObject, IItemPropertyDescriptor> valueTypeItemPropertyDescriptors;

	public StaticObjectManifestationItemProviderCustom(AdapterFactory adapterFactory) {
		super(adapterFactory);
		valueTypeItemPropertyDescriptors = new WeakHashMap<>();
	}
	
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (ManifestationPackage.eINSTANCE.getStaticObjectManifestation().isInstance(object)) {
			EObject eobject = (EObject) object;
			EObject valuetype = (EObject) eobject.eGet(ManifestationPackage.eINSTANCE.getManifestation_Valuetype());
			if (valuetype != null) {
				return Streams.concat(Stream.of(this.getOrCreateValueItemPropertyDescriptor(valuetype)), super.getPropertyDescriptors(object).stream())
						.collect(Collectors.toList());
			}
		}
		return super.getPropertyDescriptors(object);
	}
	
	protected IItemPropertyDescriptor getOrCreateValueItemPropertyDescriptor(EObject valuetype) {
		return valueTypeItemPropertyDescriptors.computeIfAbsent(valuetype, this::createValueItemPropertyDescriptor);
	}
	
	protected IItemPropertyDescriptor createValueItemPropertyDescriptor(EObject valuetype) {
		return new ValueTypeAwareManifestationObjectItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), 
				getResourceLocator(),
				getString("_UI_StaticManifestation_value_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_StaticManifestation_value_feature",
						"_UI_StaticManifestation_type"),
				ManifestationPackage.Literals.STATIC_MANIFESTATION__VALUE,
				ManifestationPackage.Literals.MANIFESTATION__VALUETYPE, true,
				null, null);
	}
	
	@Override
	protected void addValueObjectPropertyDescriptor(Object object) {
		// Empty on purpose; Value object should not be visible in property sheet
		
	}
	
	@Override
	protected void addValuePropertyDescriptor(Object object) {
		// Empty on purpose as property descriptor is dynamically added based on value type
	}
	
	

}
