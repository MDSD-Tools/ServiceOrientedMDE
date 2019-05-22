package tools.mdsd.characteristics.edit.support;

import org.eclipse.emf.ecore.EStructuralFeature;

import tools.mdsd.characteristics.binding.BindingPackage;
import tools.mdsd.characteristics.edit.support.impl.CharacteristicsContextItemProviderDecorator;
import tools.mdsd.characteristics.edit.support.impl.ValueTypeTextDecorator;
import tools.mdsd.characteristics.edit.support.util.FeatureFilteringItemProviderDecorator;
import tools.mdsd.characteristics.valuetype.ValuetypePackage;

public final class CharacteristicsEditSupportExtension {
	private CharacteristicsEditSupportExtension() {
	}

	protected static final EStructuralFeature[] FILTERED_FEATURES = {
			BindingPackage.eINSTANCE.getCharacterizationContext_IncludesContext(),
			BindingPackage.eINSTANCE.getCharacterizationContext_RefinesContext() };

	public static void registerCharacteristicSupportExtensions(
			ExtensibleDispatchingItemProviderDecoratorFactory extFact) {
		extFact.registerDecoratorFactoryProvider(ValuetypePackage.eINSTANCE.getValueType(), 
				(target, type, fact) -> new ValueTypeTextDecorator(fact));
		extFact.registerDecoratorFactoryProvider(BindingPackage.eINSTANCE.getCharacterizationContext(),
				(target, type, fact) -> new FeatureFilteringItemProviderDecorator(fact, FILTERED_FEATURES));
		extFact.registerDecoratorFactoryProvider(BindingPackage.eINSTANCE.getCharacterizationContext(),
				CharacteristicsContextItemProviderDecorator.PROVIDER);
	}

}
