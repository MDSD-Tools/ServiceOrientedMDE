package tools.mdsd.characteristics;

import org.eclipse.emf.common.util.URI;

public final class CharacteristicsConstants {
	public static final String URI_SCHEME = "characteristics";
	public static final String URI_DEFAULT_IDENTIFIER = "/module/tools.mdsd.characteristics.provider.baseproperties.BasePropertiesExtensionModule";
	
	public static final URI REALM_INSTANCE_OR_DEFAULT = URI.createURI(String.join(":", URI_SCHEME, URI_DEFAULT_IDENTIFIER)); 

	private CharacteristicsConstants() {}
	
	

}
