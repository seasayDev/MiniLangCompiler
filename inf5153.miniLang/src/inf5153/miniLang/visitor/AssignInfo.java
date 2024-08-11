package inf5153.miniLang.visitor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AssignInfo {
    private final String definedVariable;
    private final Set<String> usedVariables;

    public AssignInfo(String definedVariable, Set<String> usedVariables) {
        if (definedVariable == null || usedVariables == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        this.definedVariable = definedVariable;
        this.usedVariables = new HashSet<>(usedVariables);
    }

    public String getDefinedVariable() {
        return definedVariable;
    }

    public Set<String> getUsedVariables() {
        return Collections.unmodifiableSet(usedVariables);
    }
}