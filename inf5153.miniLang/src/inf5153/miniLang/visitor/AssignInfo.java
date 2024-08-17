/**
 * La classe AssignInfo représente une affectation de variable dans un contexte.
 * Elle stocke la variable définie et les variables utilisés dans cette affectation.
 */
package inf5153.miniLang.visitor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AssignInfo {
	private final String definedVariable;
	private final Set<String> usedVariables;

	/**
	 * Constructeur de la classe AssignInfo. Initialise la variable définie et les
	 * variables utilisées.
	 * 
	 * @param definedVariable la variable définie par l'affectation
	 * @param usedVariables   les variables utilisés dans l'affectation
	 */
	public AssignInfo(String definedVariable, Set<String> usedVariables) {
		if (definedVariable == null || usedVariables == null) {
			throw new IllegalArgumentException("Arguments cannot be null");
		}
		this.definedVariable = definedVariable;
		this.usedVariables = new HashSet<>(usedVariables);
	}

	/**
	 * Retourne la variable définie par l'affectation.
	 * 
	 * @return la variable définie
	 */

	public String getDefinedVariable() {
		return definedVariable;
	}

	/**
	 * Retourne un ensemble non modifiable des variables utilisées dans
	 * l'affectation.
	 * 
	 * @return un ensemble des variables utilisées
	 */

	public Set<String> getUsedVariables() {
		return Collections.unmodifiableSet(usedVariables);
	}
}