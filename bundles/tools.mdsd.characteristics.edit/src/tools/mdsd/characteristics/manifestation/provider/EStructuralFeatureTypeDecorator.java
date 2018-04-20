package tools.mdsd.characteristics.manifestation.provider;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;

import tools.mdsd.characteristics.manifestation.ManifestationPackage;
import tools.mdsd.characteristics.valuetype.ValueType;
import tools.mdsd.characteristics.valuetype.properties.BasicProperties.CollectionValueType;
import tools.mdsd.characteristics.valuetype.properties.BasicProperties.EDataTypeBased;
import tools.mdsd.characteristics.valuetype.properties.BasicProperties.OrderedValueType;
import tools.mdsd.characteristics.valuetype.properties.BasicProperties.UniqueElementsValueType;
import tools.mdsd.characteristics.valuetype.properties.BasicProperties.ValueTypeWithDefaultValue;

public class EStructuralFeatureTypeDecorator implements EStructuralFeature {
	
	protected static final EDataType MANIFESTATION_OBJECT_TYPE = ManifestationPackage.Literals.EMANIFESTATION_OBJECT;
	
	protected EStructuralFeature delegate;
	private ValueType valueType;

	public EStructuralFeatureTypeDecorator(ValueType valueType, EStructuralFeature delegate) {
		this.valueType = valueType;
		this.delegate = delegate;	
	}

	/**
	 * @return
	 * @see org.eclipse.emf.common.notify.Notifier#eAdapters()
	 */
	public EList<Adapter> eAdapters() {
		return delegate.eAdapters();
	}

	/**
	 * @return
	 * @see org.eclipse.emf.common.notify.Notifier#eDeliver()
	 */
	public boolean eDeliver() {
		return delegate.eDeliver();
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.EModelElement#getEAnnotations()
	 */
	public EList<EAnnotation> getEAnnotations() {
		return delegate.getEAnnotations();
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.ENamedElement#getName()
	 */
	public String getName() {
		return delegate.getName();
	}

	/**
	 * @param deliver
	 * @see org.eclipse.emf.common.notify.Notifier#eSetDeliver(boolean)
	 */
	public void eSetDeliver(boolean deliver) {
		delegate.eSetDeliver(deliver);
	}

	/**
	 * @param notification
	 * @see org.eclipse.emf.common.notify.Notifier#eNotify(org.eclipse.emf.common.notify.Notification)
	 */
	public void eNotify(Notification notification) {
		delegate.eNotify(notification);
	}

	/**
	 * @param value
	 * @see org.eclipse.emf.ecore.ENamedElement#setName(java.lang.String)
	 */
	public void setName(String value) {
		delegate.setName(value);
	}

	/**
	 * @param source
	 * @return
	 * @see org.eclipse.emf.ecore.EModelElement#getEAnnotation(java.lang.String)
	 */
	public EAnnotation getEAnnotation(String source) {
		return delegate.getEAnnotation(source);
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.EStructuralFeature#isTransient()
	 */
	public boolean isTransient() {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType())) {
			return false; 
		}
		return delegate.isTransient();
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.ETypedElement#isOrdered()
	 */
	public boolean isOrdered() {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType())) {
			return this.valueType.hasProperty(OrderedValueType.class);
		}
		return delegate.isOrdered();
	}

	/**
	 * @param value
	 * @see org.eclipse.emf.ecore.EStructuralFeature#setTransient(boolean)
	 */
	public void setTransient(boolean value) {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType())) {
			throw new UnsupportedOperationException(CharacteristicsEditPlugin.INSTANCE.getString("_Exception_No_set_on_value_type_properties")); 
		}
		delegate.setTransient(value);
	}

	/**
	 * @param value
	 * @see org.eclipse.emf.ecore.ETypedElement#setOrdered(boolean)
	 */
	public void setOrdered(boolean value) {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType())) {
			throw new UnsupportedOperationException(CharacteristicsEditPlugin.INSTANCE.getString("_Exception_No_set_on_value_type_properties")); 
		}
		delegate.setOrdered(value);
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.EStructuralFeature#isVolatile()
	 */
	public boolean isVolatile() {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType())) {
			return false; 
		}
		return delegate.isVolatile();
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.ETypedElement#isUnique()
	 */
	public boolean isUnique() {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType())) {
			return this.valueType.hasProperty(UniqueElementsValueType.class);
		}
		return delegate.isUnique();
	}

	/**
	 * @param value
	 * @see org.eclipse.emf.ecore.EStructuralFeature#setVolatile(boolean)
	 */
	public void setVolatile(boolean value) {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType())) {
			throw new UnsupportedOperationException(CharacteristicsEditPlugin.INSTANCE.getString("_Exception_No_set_on_value_type_properties")); 
		}
		delegate.setVolatile(value);
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.EObject#eClass()
	 */
	public EClass eClass() {
		return delegate.eClass();
	}

	/**
	 * @param value
	 * @see org.eclipse.emf.ecore.ETypedElement#setUnique(boolean)
	 */
	public void setUnique(boolean value) {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType())) {
			throw new UnsupportedOperationException(CharacteristicsEditPlugin.INSTANCE.getString("_Exception_No_set_on_value_type_properties")); 
		}
		delegate.setUnique(value);
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.EStructuralFeature#isChangeable()
	 */
	public boolean isChangeable() {
		return MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType()) || delegate.isChangeable();
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.EObject#eResource()
	 */
	public Resource eResource() {
		return delegate.eResource();
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.ETypedElement#getLowerBound()
	 */
	public int getLowerBound() {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType())) {
			//TODO: Think about a nicer solution
			//Currently, boundaries should be modeled as constraints on value types
			return 0;
		}
		return delegate.getLowerBound();
	}

	/**
	 * @param value
	 * @see org.eclipse.emf.ecore.EStructuralFeature#setChangeable(boolean)
	 */
	public void setChangeable(boolean value) {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType())) {
			throw new UnsupportedOperationException(CharacteristicsEditPlugin.INSTANCE.getString("_Exception_No_set_on_value_type_properties")); 
		}
		delegate.setChangeable(value);
	}

	/**
	 * @param value
	 * @see org.eclipse.emf.ecore.ETypedElement#setLowerBound(int)
	 */
	public void setLowerBound(int value) {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType())) {
			throw new UnsupportedOperationException(CharacteristicsEditPlugin.INSTANCE.getString("_Exception_No_set_on_value_type_properties")); 
		}
		delegate.setLowerBound(value);
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.EStructuralFeature#getDefaultValueLiteral()
	 */
	public String getDefaultValueLiteral() {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType())) {
			//TODO:delegate 
		}
		return delegate.getDefaultValueLiteral();
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.EObject#eContainer()
	 */
	public EObject eContainer() {
		return delegate.eContainer();
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.ETypedElement#getUpperBound()
	 */
	public int getUpperBound() {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType())) {
			//TODO: Think about a nicer solution
			//Currently, boundaries should be modeled as constraints on value types
			return 0; 
		}
		return delegate.getUpperBound();
	}

	/**
	 * @param value
	 * @see org.eclipse.emf.ecore.EStructuralFeature#setDefaultValueLiteral(java.lang.String)
	 */
	public void setDefaultValueLiteral(String value) {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType())) {
			throw new UnsupportedOperationException(CharacteristicsEditPlugin.INSTANCE.getString("_Exception_No_set_on_value_type_properties")); 
		}
		delegate.setDefaultValueLiteral(value);
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.EObject#eContainingFeature()
	 */
	public EStructuralFeature eContainingFeature() {
		return delegate.eContainingFeature();
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.EStructuralFeature#getDefaultValue()
	 */
	public Object getDefaultValue() {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType())) {
			if (this.valueType.hasProperty(ValueTypeWithDefaultValue.class)) {
				return this.valueType.getProperty(ValueTypeWithDefaultValue.class).getDefaultValue(); 
			}
		}
		return delegate.getDefaultValue();
	}

	/**
	 * @param value
	 * @see org.eclipse.emf.ecore.ETypedElement#setUpperBound(int)
	 */
	public void setUpperBound(int value) {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType())) {
			throw new UnsupportedOperationException(CharacteristicsEditPlugin.INSTANCE.getString("_Exception_No_set_on_value_type_properties")); 
		}
		delegate.setUpperBound(value);
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.ETypedElement#isMany()
	 */
	public boolean isMany() {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType())) {
			return this.valueType.hasProperty(CollectionValueType.class);
		}
		return delegate.isMany();
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.EObject#eContainmentFeature()
	 */
	public EReference eContainmentFeature() {
		return delegate.eContainmentFeature();
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.ETypedElement#isRequired()
	 */
	public boolean isRequired() {
		return delegate.isRequired();
	}

	/**
	 * @param value
	 * @see org.eclipse.emf.ecore.EStructuralFeature#setDefaultValue(java.lang.Object)
	 */
	public void setDefaultValue(Object value) {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType())) {
			throw new UnsupportedOperationException(CharacteristicsEditPlugin.INSTANCE.getString("_Exception_No_set_on_value_type_properties")); 
		}
		delegate.setDefaultValue(value);
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.ETypedElement#getEType()
	 */
	public EClassifier getEType() {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType())) {
			if (this.valueType.hasProperty(EDataTypeBased.class)) {
				return this.valueType.getProperty(EDataTypeBased.class).getDataType();
			} else {
				return new ValueTypeEClassifierWrapper(valueType);
			}
		}
		return delegate.getEType();
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.EStructuralFeature#isUnsettable()
	 */
	public boolean isUnsettable() {
		return delegate.isUnsettable();
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.EObject#eContents()
	 */
	public EList<EObject> eContents() {
		return delegate.eContents();
	}

	/**
	 * @param value
	 * @see org.eclipse.emf.ecore.ETypedElement#setEType(org.eclipse.emf.ecore.EClassifier)
	 */
	public void setEType(EClassifier value) {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType())) {
			throw new UnsupportedOperationException(CharacteristicsEditPlugin.INSTANCE.getString("_Exception_No_set_on_value_type_properties")); 
		}
		delegate.setEType(value);
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.ETypedElement#getEGenericType()
	 */
	public EGenericType getEGenericType() {
		return delegate.getEGenericType();
	}

	/**
	 * @param value
	 * @see org.eclipse.emf.ecore.EStructuralFeature#setUnsettable(boolean)
	 */
	public void setUnsettable(boolean value) {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType())) {
			throw new UnsupportedOperationException(CharacteristicsEditPlugin.INSTANCE.getString("_Exception_No_set_on_value_type_properties")); 
		}
		delegate.setUnsettable(value);
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.EObject#eAllContents()
	 */
	public TreeIterator<EObject> eAllContents() {
		return delegate.eAllContents();
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.EStructuralFeature#isDerived()
	 */
	public boolean isDerived() {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType())) {
			return false; 
		}
		return delegate.isDerived();
	}

	/**
	 * @param value
	 * @see org.eclipse.emf.ecore.ETypedElement#setEGenericType(org.eclipse.emf.ecore.EGenericType)
	 */
	public void setEGenericType(EGenericType value) {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType())) {
			throw new UnsupportedOperationException(CharacteristicsEditPlugin.INSTANCE.getString("_Exception_No_set_on_value_type_properties")); 
		}
		delegate.setEGenericType(value);
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.EObject#eIsProxy()
	 */
	public boolean eIsProxy() {
		return delegate.eIsProxy();
	}

	/**
	 * @param value
	 * @see org.eclipse.emf.ecore.EStructuralFeature#setDerived(boolean)
	 */
	public void setDerived(boolean value) {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType())) {
			throw new UnsupportedOperationException(CharacteristicsEditPlugin.INSTANCE.getString("_Exception_No_set_on_value_type_properties")); 
		}
		delegate.setDerived(value);
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.EStructuralFeature#getEContainingClass()
	 */
	public EClass getEContainingClass() {
		return delegate.getEContainingClass();
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.EObject#eCrossReferences()
	 */
	public EList<EObject> eCrossReferences() {
		return delegate.eCrossReferences();
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.EStructuralFeature#getFeatureID()
	 */
	public int getFeatureID() {
		return delegate.getFeatureID();
	}

	/**
	 * @return
	 * @see org.eclipse.emf.ecore.EStructuralFeature#getContainerClass()
	 */
	public Class<?> getContainerClass() {
		return delegate.getContainerClass();
	}

	/**
	 * @param feature
	 * @return
	 * @see org.eclipse.emf.ecore.EObject#eGet(org.eclipse.emf.ecore.EStructuralFeature)
	 */
	public Object eGet(EStructuralFeature feature) {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType()) && EcorePackage.Literals.ETYPED_ELEMENT__ETYPE.equals(feature)) {
			return this.getEType(); 
		}
		return delegate.eGet(feature);
	}

	/**
	 * @param feature
	 * @param resolve
	 * @return
	 * @see org.eclipse.emf.ecore.EObject#eGet(org.eclipse.emf.ecore.EStructuralFeature, boolean)
	 */
	public Object eGet(EStructuralFeature feature, boolean resolve) {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType()) && EcorePackage.Literals.ETYPED_ELEMENT__ETYPE.equals(feature)) {
			return this.getEType(); 
		}
		return delegate.eGet(feature, resolve);
	}

	/**
	 * @param feature
	 * @param newValue
	 * @see org.eclipse.emf.ecore.EObject#eSet(org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
	 */
	public void eSet(EStructuralFeature feature, Object newValue) {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType()) && EcorePackage.Literals.ETYPED_ELEMENT__ETYPE.equals(feature)) {
			throw new UnsupportedOperationException(CharacteristicsEditPlugin.INSTANCE.getString("_Exception_No_set_on_value_type_properties")); 
		}
		delegate.eSet(feature, newValue);
	}

	/**
	 * @param feature
	 * @return
	 * @see org.eclipse.emf.ecore.EObject#eIsSet(org.eclipse.emf.ecore.EStructuralFeature)
	 */
	public boolean eIsSet(EStructuralFeature feature) {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType()) && EcorePackage.Literals.ETYPED_ELEMENT__ETYPE.equals(feature)) {
			return this.valueType != null; 
		}
		return delegate.eIsSet(feature);
	}

	/**
	 * @param feature
	 * @see org.eclipse.emf.ecore.EObject#eUnset(org.eclipse.emf.ecore.EStructuralFeature)
	 */
	public void eUnset(EStructuralFeature feature) {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType()) && EcorePackage.Literals.ETYPED_ELEMENT__ETYPE.equals(feature)) {
			throw new UnsupportedOperationException(CharacteristicsEditPlugin.INSTANCE.getString("_Exception_No_set_on_value_type_properties")); 
		}
		delegate.eUnset(feature);
	}

	/**
	 * @param operation
	 * @param arguments
	 * @return
	 * @throws InvocationTargetException
	 * @see org.eclipse.emf.ecore.EObject#eInvoke(org.eclipse.emf.ecore.EOperation, org.eclipse.emf.common.util.EList)
	 */
	public Object eInvoke(EOperation operation, EList<?> arguments) throws InvocationTargetException {
		if (MANIFESTATION_OBJECT_TYPE.equals(this.delegate.getEType())) {
			throw new UnsupportedOperationException(CharacteristicsEditPlugin.INSTANCE.getString("_Exception_No_invoke_on_value_typed_properties")); 
		}
		return delegate.eInvoke(operation, arguments);
	}

}
