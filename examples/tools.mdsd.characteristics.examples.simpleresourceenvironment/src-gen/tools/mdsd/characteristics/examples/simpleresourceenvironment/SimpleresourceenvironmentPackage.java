/**
 */
package tools.mdsd.characteristics.examples.simpleresourceenvironment;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import tools.mdsd.characteristics.instance.InstancePackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see tools.mdsd.characteristics.examples.simpleresourceenvironment.SimpleresourceenvironmentFactory
 * @model kind="package"
 * @generated
 */
public interface SimpleresourceenvironmentPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "simpleresourceenvironment";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.example.org/simpleresourceenvironment";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "simpleresourceenvironment";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SimpleresourceenvironmentPackage eINSTANCE = tools.mdsd.characteristics.examples.simpleresourceenvironment.impl.SimpleresourceenvironmentPackageImpl
			.init();

	/**
	 * The meta object id for the '{@link tools.mdsd.characteristics.examples.simpleresourceenvironment.impl.ResourceContainerImpl <em>Resource Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.mdsd.characteristics.examples.simpleresourceenvironment.impl.ResourceContainerImpl
	 * @see tools.mdsd.characteristics.examples.simpleresourceenvironment.impl.SimpleresourceenvironmentPackageImpl#getResourceContainer()
	 * @generated
	 */
	int RESOURCE_CONTAINER = 0;

	/**
	 * The feature id for the '<em><b>Local Manifestations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_CONTAINER__LOCAL_MANIFESTATIONS = InstancePackage.CHARACTERIZABLE__LOCAL_MANIFESTATIONS;

	/**
	 * The feature id for the '<em><b>Manifestations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_CONTAINER__MANIFESTATIONS = InstancePackage.CHARACTERIZABLE__MANIFESTATIONS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_CONTAINER__RESOURCES = InstancePackage.CHARACTERIZABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Resource Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_CONTAINER_FEATURE_COUNT = InstancePackage.CHARACTERIZABLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link tools.mdsd.characteristics.examples.simpleresourceenvironment.impl.ResourceImpl <em>Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.mdsd.characteristics.examples.simpleresourceenvironment.impl.ResourceImpl
	 * @see tools.mdsd.characteristics.examples.simpleresourceenvironment.impl.SimpleresourceenvironmentPackageImpl#getResource()
	 * @generated
	 */
	int RESOURCE = 1;

	/**
	 * The feature id for the '<em><b>Local Manifestations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__LOCAL_MANIFESTATIONS = InstancePackage.CHARACTERIZABLE__LOCAL_MANIFESTATIONS;

	/**
	 * The feature id for the '<em><b>Manifestations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__MANIFESTATIONS = InstancePackage.CHARACTERIZABLE__MANIFESTATIONS;

	/**
	 * The number of structural features of the '<em>Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_FEATURE_COUNT = InstancePackage.CHARACTERIZABLE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link tools.mdsd.characteristics.examples.simpleresourceenvironment.impl.ProcessingResourceImpl <em>Processing Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.mdsd.characteristics.examples.simpleresourceenvironment.impl.ProcessingResourceImpl
	 * @see tools.mdsd.characteristics.examples.simpleresourceenvironment.impl.SimpleresourceenvironmentPackageImpl#getProcessingResource()
	 * @generated
	 */
	int PROCESSING_RESOURCE = 2;

	/**
	 * The feature id for the '<em><b>Local Manifestations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESSING_RESOURCE__LOCAL_MANIFESTATIONS = RESOURCE__LOCAL_MANIFESTATIONS;

	/**
	 * The feature id for the '<em><b>Manifestations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESSING_RESOURCE__MANIFESTATIONS = RESOURCE__MANIFESTATIONS;

	/**
	 * The number of structural features of the '<em>Processing Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESSING_RESOURCE_FEATURE_COUNT = RESOURCE_FEATURE_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link tools.mdsd.characteristics.examples.simpleresourceenvironment.ResourceContainer <em>Resource Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Container</em>'.
	 * @see tools.mdsd.characteristics.examples.simpleresourceenvironment.ResourceContainer
	 * @generated
	 */
	EClass getResourceContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link tools.mdsd.characteristics.examples.simpleresourceenvironment.ResourceContainer#getResources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resources</em>'.
	 * @see tools.mdsd.characteristics.examples.simpleresourceenvironment.ResourceContainer#getResources()
	 * @see #getResourceContainer()
	 * @generated
	 */
	EReference getResourceContainer_Resources();

	/**
	 * Returns the meta object for class '{@link tools.mdsd.characteristics.examples.simpleresourceenvironment.Resource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource</em>'.
	 * @see tools.mdsd.characteristics.examples.simpleresourceenvironment.Resource
	 * @generated
	 */
	EClass getResource();

	/**
	 * Returns the meta object for class '{@link tools.mdsd.characteristics.examples.simpleresourceenvironment.ProcessingResource <em>Processing Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Processing Resource</em>'.
	 * @see tools.mdsd.characteristics.examples.simpleresourceenvironment.ProcessingResource
	 * @generated
	 */
	EClass getProcessingResource();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SimpleresourceenvironmentFactory getSimpleresourceenvironmentFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link tools.mdsd.characteristics.examples.simpleresourceenvironment.impl.ResourceContainerImpl <em>Resource Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.mdsd.characteristics.examples.simpleresourceenvironment.impl.ResourceContainerImpl
		 * @see tools.mdsd.characteristics.examples.simpleresourceenvironment.impl.SimpleresourceenvironmentPackageImpl#getResourceContainer()
		 * @generated
		 */
		EClass RESOURCE_CONTAINER = eINSTANCE.getResourceContainer();

		/**
		 * The meta object literal for the '<em><b>Resources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_CONTAINER__RESOURCES = eINSTANCE.getResourceContainer_Resources();

		/**
		 * The meta object literal for the '{@link tools.mdsd.characteristics.examples.simpleresourceenvironment.impl.ResourceImpl <em>Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.mdsd.characteristics.examples.simpleresourceenvironment.impl.ResourceImpl
		 * @see tools.mdsd.characteristics.examples.simpleresourceenvironment.impl.SimpleresourceenvironmentPackageImpl#getResource()
		 * @generated
		 */
		EClass RESOURCE = eINSTANCE.getResource();

		/**
		 * The meta object literal for the '{@link tools.mdsd.characteristics.examples.simpleresourceenvironment.impl.ProcessingResourceImpl <em>Processing Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.mdsd.characteristics.examples.simpleresourceenvironment.impl.ProcessingResourceImpl
		 * @see tools.mdsd.characteristics.examples.simpleresourceenvironment.impl.SimpleresourceenvironmentPackageImpl#getProcessingResource()
		 * @generated
		 */
		EClass PROCESSING_RESOURCE = eINSTANCE.getProcessingResource();

	}

} //SimpleresourceenvironmentPackage
