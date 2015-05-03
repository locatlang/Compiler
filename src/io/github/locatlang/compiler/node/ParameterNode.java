package io.github.locatlang.compiler.node;

/**
 * Created by Locercus on 4/5/2015.
 */
public class ParameterNode extends Node {
    private String className;
    private String variableName;
    private String argumentMatch;

    public ParameterNode(String className, String variableName, String argumentMatch) {
        this.className     = className;
        this.variableName  = variableName;
        this.argumentMatch = argumentMatch;
    }

    public ParameterNode(String className, String variableName) {
        this.className     = className;
        this.variableName  = variableName;
        this.argumentMatch = null;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getArgumentMatch() {
        return argumentMatch;
    }

    public void setArgumentMatch(String argumentMatch) {
        this.argumentMatch = argumentMatch;
    }
}
