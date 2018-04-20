/**
 */
package tools.mdsd.characteristics.examples.simpleresourceenvironment;

import org.eclipse.emf.common.util.EList;

import tools.mdsd.characteristics.instance.Characterizable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link tools.mdsd.characteristics.examples.simpleresourceenvironment.ResourceContainer#getResources <em>Resources</em>}</li>
 * </ul>
 *
 * @see tools.mdsd.characteristics.examples.simpleresourceenvironment.SimpleresourceenvironmentPackage#getResourceContainer()
 * @model
 * @generated
 */
public interface ResourceContainer extends Characterizable {
	/**
	 * Returns the value of the '<em><b>Resources</b></em>' containment reference list.
	 * The list contents are of type {@link tools.mdsd.characteristics.examples.simpleresourceenvironment.Resource}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resources</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resources</em>' containment reference list.
	 * @see tools.mdsd.characteristics.examples.simpleresourceenvironment.SimpleresourceenvironmentPackage#getResourceContainer_Resources()
	 * @model containment="true"
	 * @generated
	 */
	EList<Resource> getResources();

} // ResourceContainer
