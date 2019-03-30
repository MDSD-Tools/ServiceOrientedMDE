package tools.mdsd.characteristics.provider.baseproperties;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import tools.mdsd.characteristics.api.ValueTypePropertiesProvider;
import tools.mdsd.characteristics.valuetype.properties.providers.DefaultBaseValueTypePropertiesProvider;

public class BasePropertiesExtensionModule extends AbstractModule {

		@Override
		protected void configure() {
			Multibinder<ValueTypePropertiesProvider> vtppBinder = 
					Multibinder.newSetBinder(binder(), ValueTypePropertiesProvider.class);
			vtppBinder.addBinding().to(DefaultBaseValueTypePropertiesProvider.class);
		}

}

