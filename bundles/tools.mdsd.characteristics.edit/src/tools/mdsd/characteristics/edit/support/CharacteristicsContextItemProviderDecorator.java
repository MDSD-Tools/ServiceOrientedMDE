package tools.mdsd.characteristics.edit.support;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.WeakHashMap;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemProviderDecorator;

import tools.mdsd.characteristics.binding.BindingPackage;
import tools.mdsd.characteristics.binding.CharacteristicBinding;
import tools.mdsd.characteristics.binding.CharacterizationContext;
import tools.mdsd.characteristics.characteristic.provider.CharacteristicBasedVirtualStructuralFeature;
import tools.mdsd.characteristics.edit.support.ExtensibleDispatchingItemProviderDecoratorFactory.ItemProviderDecoratorFactoryProvider;
import tools.mdsd.characteristics.manifestation.ManifestationFactory;
import tools.mdsd.characteristics.manifestation.ManifestationPackage;
import tools.mdsd.characteristics.manifestation.SingleValue;

public class CharacteristicsContextItemProviderDecorator extends AdapterItemProviderDecorator {
	public static final ExtensibleDispatchingItemProviderDecoratorFactory.ItemProviderDecoratorFactoryProvider PROVIDER = new ItemProviderDecoratorFactoryProvider() {

		@Override
		public IItemProviderDecorator createItemProviderDecorator(Object target, Object Type, AdapterFactory fact) {
			if (target instanceof CharacterizationContext)
				return new CharacteristicsContextItemProviderDecorator(fact);
			throw new IllegalArgumentException("Should not have been called with wrong object type");
		}
	};

	WeakHashMap<CharacterizationContext, List<IItemPropertyDescriptor>> characteristicBasedDescriptors = new WeakHashMap<>();

	public CharacteristicsContextItemProviderDecorator(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		List<IItemPropertyDescriptor> result = super.getPropertyDescriptors(object);
		if ((object instanceof CharacterizationContext)) {
			CharacterizationContext ctx = (CharacterizationContext) object;
			List<IItemPropertyDescriptor> characteristicBased = getCharacteristicBasedDescriptors(ctx);
			result.addAll(characteristicBased);
		}
		return result;
	}
	
	@Override
	public IItemPropertyDescriptor getPropertyDescriptor(Object object, Object propertyId) {
		if ((object instanceof CharacterizationContext)) {
			CharacterizationContext ctx = (CharacterizationContext) object;
			List<IItemPropertyDescriptor> characteristicBased = getCharacteristicBasedDescriptors(ctx);
			return characteristicBased.stream().filter(ipd -> propertyId.equals(ipd.getId(object)))
					.findAny().orElseGet(() -> super.getPropertyDescriptor(object, propertyId));
		}
		return super.getPropertyDescriptor(object, propertyId);
	}
	
	@Override
	public Command createCommand(Object object, EditingDomain domain, Class<? extends Command> commandClass,
			CommandParameter commandParameter) {
		if (commandParameter != null && commandParameter.owner instanceof CharacterizationContext) {
			CharacterizationContext ctx = (CharacterizationContext) commandParameter.owner;
			Optional<CharacteristicBasedVirtualStructuralFeature> optFeature = getPropertyDescriptors(object).stream()
				.filter(ipd -> ipd.getFeature(object).equals(commandParameter.feature))
				.map(ipd -> ipd.getFeature(object))
				.filter(CharacteristicBasedVirtualStructuralFeature.class::isInstance)
				.map(CharacteristicBasedVirtualStructuralFeature.class::cast)
				.findAny();
			if (optFeature.isPresent()) {
				CharacteristicBasedVirtualStructuralFeature feature = optFeature.get();
				if (commandClass == SetCommand.class) {
					Optional<CharacteristicBinding> optBinding = ctx.getBindings()
							.stream().filter(b -> b.getCharacteristic().equals(feature.getCharacteristic()))
							.findAny();
					if (optBinding.isPresent()) {
						CompoundCommand result = new CompoundCommand();
						CharacteristicBinding binding = optBinding.get();
						SingleValue manifestation = ManifestationFactory.eINSTANCE.createSingleValue();
						result.append(super.createCommand(binding, domain, commandClass, 
								new CommandParameter(binding, 
										BindingPackage.eINSTANCE.getManifestationContainer_Manifestation(), 
										manifestation)));
						result.append(super.createCommand(manifestation, domain, commandClass, 
								new CommandParameter(manifestation,
										ManifestationPackage.eINSTANCE.getStaticManifestation_Value(),
										commandParameter.getValue())));
						return result;
					}
					
				}				
			}
		}
		return super.createCommand(object, domain, commandClass, commandParameter);
	}
	
	protected List<IItemPropertyDescriptor> getCharacteristicBasedDescriptors(CharacterizationContext ctx) {
		return characteristicBasedDescriptors.computeIfAbsent(ctx, 
				this::createCharacteristicBasedDescriptors);
	}
	
	protected List<IItemPropertyDescriptor> createCharacteristicBasedDescriptors(CharacterizationContext ctx) {
		List<IItemPropertyDescriptor> result = new LinkedList<>();
		for (CharacteristicBinding binding : ctx.getBindings()) {
			result.add(CharacteristicBasedItemPropertyDescriptor.create(
					((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), 
					binding.getCharacteristic().computeCharacteristic()));
		}
		return result;
	}

}
