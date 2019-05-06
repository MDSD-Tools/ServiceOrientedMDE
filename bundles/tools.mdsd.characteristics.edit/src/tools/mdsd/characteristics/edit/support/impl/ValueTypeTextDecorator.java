package tools.mdsd.characteristics.edit.support.impl;

import org.eclipse.emf.common.notify.AdapterFactory;

import tools.mdsd.characteristics.edit.support.util.AdapterItemProviderDecorator;
import tools.mdsd.characteristics.valuetype.ValueType;

public class ValueTypeTextDecorator extends AdapterItemProviderDecorator {

	public ValueTypeTextDecorator(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	@Override
	public String getText(Object object) {
		if (object instanceof ValueType) {
			String label = ((ValueType)object).getEntityName();
			if (label != null && label.length() > 0) {
				return "«ValueType» " + label;
			}
		}
		return super.getText(object);
	}

}
