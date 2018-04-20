/**
 */
package tools.mdsd.characteristics.examples.simpleresourceenvironment.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import tools.mdsd.characteristics.examples.simpleresourceenvironment.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SimpleresourceenvironmentFactoryImpl extends EFactoryImpl implements SimpleresourceenvironmentFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SimpleresourceenvironmentFactory init() {
		try {
			SimpleresourceenvironmentFactory theSimpleresourceenvironmentFactory = (SimpleresourceenvironmentFactory) EPackage.Registry.INSTANCE
					.getEFactory(SimpleresourceenvironmentPackage.eNS_URI);
			if (theSimpleresourceenvironmentFactory != null) {
				return theSimpleresourceenvironmentFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SimpleresourceenvironmentFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleresourceenvironmentFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case SimpleresourceenvironmentPackage.RESOURCE_CONTAINER:
			return createResourceContainer();
		case SimpleresourceenvironmentPackage.RESOURCE:
			return createResource();
		case SimpleresourceenvironmentPackage.PROCESSING_RESOURCE:
			return createProcessingResource();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceContainer createResourceContainer() {
		ResourceContainerImpl resourceContainer = new ResourceContainerImpl();
		return resourceContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Resource createResource() {
		ResourceImpl resource = new ResourceImpl();
		return resource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessingResource createProcessingResource() {
		ProcessingResourceImpl processingResource = new ProcessingResourceImpl();
		return processingResource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleresourceenvironmentPackage getSimpleresourceenvironmentPackage() {
		return (SimpleresourceenvironmentPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SimpleresourceenvironmentPackage getPackage() {
		return SimpleresourceenvironmentPackage.eINSTANCE;
	}

} //SimpleresourceenvironmentFactoryImpl
