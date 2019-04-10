package tools.mdsd.characteristics.edit.support;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.WeakHashMap;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
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
	
	public final class CacheClearingAdapter extends AdapterImpl {
		@Override
		public void notifyChanged(Notification notification) {
			if (BindingPackage.eINSTANCE.getCharacterizationContext_Bindings().equals(notification.getFeature())) {
				characteristicBasedDescriptors.remove(notification.getNotifier());
			} else if (BindingPackage.eINSTANCE.getCharacteristicBinding().isInstance(notification.getNotifier())) {
				EObject ctx = ((CharacteristicBinding)notification.getNotifier()).eContainer();
				characteristicBasedDescriptors.remove(ctx);
			}
		} 
	}

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
			return characteristicBased.stream().filter(ipd -> propertyId.equals(ipd.getId(object))).findAny()
					.orElseGet(() -> super.getPropertyDescriptor(object, propertyId));
		}
		return super.getPropertyDescriptor(object, propertyId);
	}

	@Override
	public Command createCommand(Object object, EditingDomain domain, Class<? extends Command> commandClass,
			CommandParameter commandParameter) {
		Command result = null;
		if (commandParameter != null && commandParameter.owner instanceof CharacterizationContext) {
			CharacterizationContext ctx = (CharacterizationContext) commandParameter.owner;

			if (commandClass == SetCommand.class) {
				result = createSetCommand(object, domain, commandClass, commandParameter, ctx);
			} /*else if (commandClass == AddCommand.class) {
				result = createAddCommand(object, domain, commandClass, commandParameter, ctx);
			}*/
		}
		return result != null ? result : super.createCommand(object, domain, commandClass, commandParameter);
	}

	private Command createSetCommand(Object object, EditingDomain domain, Class<? extends Command> commandClass,
			CommandParameter commandParameter, CharacterizationContext ctx) {
		
		if (commandParameter.getFeature() instanceof CharacteristicBasedVirtualStructuralFeature) {
			CharacteristicBasedVirtualStructuralFeature feature = (CharacteristicBasedVirtualStructuralFeature) commandParameter.getFeature();

			Optional<CharacteristicBinding> optBinding = ctx.getBindings().stream()
					.filter(b -> b.getCharacteristic().equals(feature.getCharacteristic())).findAny();
			if (optBinding.isPresent()) {
				CompoundCommand result = new CompoundCommand();
				CharacteristicBinding binding = optBinding.get();
				SingleValue manifestation = ManifestationFactory.eINSTANCE.createSingleValue();
				result.append(super.createCommand(binding, domain, commandClass, new CommandParameter(binding,
						BindingPackage.eINSTANCE.getManifestationContainer_Manifestation(), manifestation)));
				result.append(super.createCommand(manifestation, domain, commandClass,
						new CommandParameter(manifestation,
								ManifestationPackage.eINSTANCE.getStaticManifestation_Value(),
								commandParameter.getValue())));
				return result;
			}
		}
		return null;
	}

	protected List<IItemPropertyDescriptor> getCharacteristicBasedDescriptors(CharacterizationContext ctx) {
		return characteristicBasedDescriptors.computeIfAbsent(ctx, this::createCharacteristicBasedDescriptors);
	}

	protected List<IItemPropertyDescriptor> createCharacteristicBasedDescriptors(CharacterizationContext ctx) {
		List<IItemPropertyDescriptor> result = new LinkedList<>();
		for (CharacteristicBinding binding : ctx.getBindings()) {
			if (binding.getCharacteristic() != null) {
				result.add(CharacteristicBasedItemPropertyDescriptor.create(
						((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						binding.getCharacteristic().computeCharacteristic()));
			}
			if (binding.eAdapters().stream().noneMatch(CacheClearingAdapter.class::isInstance)) {
				binding.eAdapters().add(new CacheClearingAdapter());
			}
		}
		if (ctx.eAdapters().stream().noneMatch(CacheClearingAdapter.class::isInstance)) {
			ctx.eAdapters().add(new CacheClearingAdapter());
		}
		return result;
	}

}
