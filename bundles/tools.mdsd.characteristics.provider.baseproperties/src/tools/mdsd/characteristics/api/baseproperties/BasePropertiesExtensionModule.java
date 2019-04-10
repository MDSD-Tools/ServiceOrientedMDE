package tools.mdsd.characteristics.api.baseproperties;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import tools.mdsd.characteristics.api.StaticManifestationSerializationService;
import tools.mdsd.characteristics.api.ValueTypePropertiesProvider;
import tools.mdsd.characteristics.api.baseproperties.providers.EDataTypeBasedValueTypePropertiesProvider;
import tools.mdsd.characteristics.api.baseproperties.services.EDataTypeBasedStaticManifestationSerializationService;

public class BasePropertiesExtensionModule extends AbstractModule {

		@Override
		protected void configure() {
			Multibinder<ValueTypePropertiesProvider> vtppBinder = 
					Multibinder.newSetBinder(binder(), ValueTypePropertiesProvider.class);
			vtppBinder.addBinding().to(EDataTypeBasedValueTypePropertiesProvider.class);
			
			Multibinder<StaticManifestationSerializationService> smssBinder = 
					Multibinder.newSetBinder(binder(), StaticManifestationSerializationService.class);
			smssBinder.addBinding().to(EDataTypeBasedStaticManifestationSerializationService.class);
		}

}

