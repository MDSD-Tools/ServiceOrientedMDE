package tools.mdsd.characteristics.edit.support;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;

public class FeatureFilteringItemProviderDecorator extends AdapterItemProviderDecorator {
	private HashSet<EStructuralFeature> featuresToFilter;
	
	public FeatureFilteringItemProviderDecorator(AdapterFactory adapterFactory,
			EStructuralFeature... featuresToFilter) {
		super(adapterFactory);
		this.featuresToFilter = new HashSet<>(Arrays.asList(featuresToFilter));
	}
	
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		return super.getPropertyDescriptors(object).stream()
				.filter(desc -> !featuresToFilter.contains(desc.getFeature(object))).collect(Collectors.toList());
	}

}
