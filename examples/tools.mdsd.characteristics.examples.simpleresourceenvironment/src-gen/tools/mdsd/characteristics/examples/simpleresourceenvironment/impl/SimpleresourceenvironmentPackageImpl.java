/**
 */
package tools.mdsd.characteristics.examples.simpleresourceenvironment.impl;

import de.uka.ipd.sdq.identifier.IdentifierPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import tools.mdsd.characteristics.characteristic.CharacteristicPackage;

import tools.mdsd.characteristics.examples.simpleresourceenvironment.ProcessingResource;
import tools.mdsd.characteristics.examples.simpleresourceenvironment.Resource;
import tools.mdsd.characteristics.examples.simpleresourceenvironment.ResourceContainer;
import tools.mdsd.characteristics.examples.simpleresourceenvironment.SimpleresourceenvironmentFactory;
import tools.mdsd.characteristics.examples.simpleresourceenvironment.SimpleresourceenvironmentPackage;

import tools.mdsd.characteristics.instance.InstancePackage;

import tools.mdsd.characteristics.manifestation.ManifestationPackage;

import tools.mdsd.characteristics.valuetype.ValuetypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SimpleresourceenvironmentPackageImpl extends EPackageImpl implements SimpleresourceenvironmentPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processingResourceEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see tools.mdsd.characteristics.examples.simpleresourceenvironment.SimpleresourceenvironmentPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SimpleresourceenvironmentPackageImpl() {
		super(eNS_URI, SimpleresourceenvironmentFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link SimpleresourceenvironmentPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SimpleresourceenvironmentPackage init() {
		if (isInited)
			return (SimpleresourceenvironmentPackage) EPackage.Registry.INSTANCE
					.getEPackage(SimpleresourceenvironmentPackage.eNS_URI);

		// Obtain or create and register package
		SimpleresourceenvironmentPackageImpl theSimpleresourceenvironmentPackage = (SimpleresourceenvironmentPackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof SimpleresourceenvironmentPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
						: new SimpleresourceenvironmentPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		ManifestationPackage.eINSTANCE.eClass();
		InstancePackage.eINSTANCE.eClass();
		CharacteristicPackage.eINSTANCE.eClass();
		ValuetypePackage.eINSTANCE.eClass();
		IdentifierPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theSimpleresourceenvironmentPackage.createPackageContents();

		// Initialize created meta-data
		theSimpleresourceenvironmentPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSimpleresourceenvironmentPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SimpleresourceenvironmentPackage.eNS_URI, theSimpleresourceenvironmentPackage);
		return theSimpleresourceenvironmentPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResourceContainer() {
		return resourceContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceContainer_Resources() {
		return (EReference) resourceContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResource() {
		return resourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessingResource() {
		return processingResourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleresourceenvironmentFactory getSimpleresourceenvironmentFactory() {
		return (SimpleresourceenvironmentFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		resourceContainerEClass = createEClass(RESOURCE_CONTAINER);
		createEReference(resourceContainerEClass, RESOURCE_CONTAINER__RESOURCES);

		resourceEClass = createEClass(RESOURCE);

		processingResourceEClass = createEClass(PROCESSING_RESOURCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		InstancePackage theInstancePackage = (InstancePackage) EPackage.Registry.INSTANCE
				.getEPackage(InstancePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		resourceContainerEClass.getESuperTypes().add(theInstancePackage.getCharacterizable());
		resourceEClass.getESuperTypes().add(theInstancePackage.getCharacterizable());
		processingResourceEClass.getESuperTypes().add(this.getResource());

		// Initialize classes and features; add operations and parameters
		initEClass(resourceContainerEClass, ResourceContainer.class, "ResourceContainer", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResourceContainer_Resources(), this.getResource(), null, "resources", null, 0, -1,
				ResourceContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceEClass, Resource.class, "Resource", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(processingResourceEClass, ProcessingResource.class, "ProcessingResource", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //SimpleresourceenvironmentPackageImpl
