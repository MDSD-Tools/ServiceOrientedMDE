package tools.mdsd.characteristics.manifestation.impl;

import tools.mdsd.characteristics.utils.CharacteristicsAPIAware;

/**
 * Implements the SingleValue specific deserialization capabilities for the
 * characteristics extension.
 */
public class SingleValueImpl extends SingleValueImplGen implements CharacteristicsAPIAware {
	private Object valueCache = null;

	@Override
	public Object getValue() {
		if (valueCache == null && eResource() != null) {
			synchronized (this) {
				if (valueCache == null) {
					valueCache = getAPI().getSerializationService().deserializeStatic(getContainer().getValuetype(),
							storage);
				}
			}
		}
		return valueCache;
	}

	@Override
	public void setValue(Object newValue) {
		if (eResource() == null || eContainer() == null) {
			throw new IllegalStateException("Value can only be set if manifestation is properly contained");
		}
		if (!getContainer().getValuetype().adheresToValueType(newValue)) {
			throw new IllegalArgumentException("Value must adhere to specified value type");
		}
		valueCache = newValue;
		storage = getAPI().getSerializationService().serializeStatic(getContainer().getValuetype(), valueCache);
	}

}
