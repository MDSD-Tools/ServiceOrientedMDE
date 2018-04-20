/**
 */
package tools.mdsd.characteristics.examples.simpleresourceenvironment;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see tools.mdsd.characteristics.examples.simpleresourceenvironment.SimpleresourceenvironmentPackage
 * @generated
 */
public interface SimpleresourceenvironmentFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SimpleresourceenvironmentFactory eINSTANCE = tools.mdsd.characteristics.examples.simpleresourceenvironment.impl.SimpleresourceenvironmentFactoryImpl
			.init();

	/**
	 * Returns a new object of class '<em>Resource Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Container</em>'.
	 * @generated
	 */
	ResourceContainer createResourceContainer();

	/**
	 * Returns a new object of class '<em>Resource</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource</em>'.
	 * @generated
	 */
	Resource createResource();

	/**
	 * Returns a new object of class '<em>Processing Resource</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Processing Resource</em>'.
	 * @generated
	 */
	ProcessingResource createProcessingResource();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SimpleresourceenvironmentPackage getSimpleresourceenvironmentPackage();

} //SimpleresourceenvironmentFactory
