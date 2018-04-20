package tools.mdsd.characteristics.support.api;

import org.eclipse.emf.ecore.resource.ResourceSet;

public interface IValueTypeSupportFactory {
	
	IValueTypeSupport createValueTypeSupport(ResourceSet resourceSet);

}
