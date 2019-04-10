package tools.mdsd.characteristics.edit.support;

import org.eclipse.emf.ecore.EStructuralFeature;

import tools.mdsd.characteristics.binding.BindingPackage;

public final class CharacteristicsSupportExtension {
	private CharacteristicsSupportExtension() {
	}

	protected static final EStructuralFeature[] FILTERED_FEATURES = {
			BindingPackage.eINSTANCE.getCharacterizationContext_IncludesContext(),
			BindingPackage.eINSTANCE.getCharacterizationContext_RefinesContext() };

	public static void registerCharacteristicSupportExtensions(
			ExtensibleDispatchingItemProviderDecoratorFactory extFact) {
		extFact.registerDecoratorFactoryProvider(BindingPackage.eINSTANCE.getCharacterizationContext(),
				(target, type, fact) -> new FeatureFilteringItemProviderDecorator(fact, FILTERED_FEATURES));
		extFact.registerDecoratorFactoryProvider(BindingPackage.eINSTANCE.getCharacterizationContext(),
				CharacteristicsContextItemProviderDecorator.PROVIDER);
	}

}
