package tools.mdsd.characteristics;

import org.eclipse.emf.common.util.URI;

public final class CharacteristicsConstants {
	public static final String URI_SCHEME = "characteristics";
	public static final String URI_DEFAULT_IDENTIFIER = "/module/tools.mdsd.characteristics.api.baseproperties.BasePropertiesExtensionModule";
	
	public static final URI REALM_INSTANCE_OR_DEFAULT = URI.createURI(String.join(":", URI_SCHEME, URI_DEFAULT_IDENTIFIER));
	
	public static final URI BASE_VALUE_TYPE_MODEL = URI.createURI("pathmap://CHARACTERISTICS_RESOURCES/basetypes.valuetype");

	private CharacteristicsConstants() {}
	
	

}
