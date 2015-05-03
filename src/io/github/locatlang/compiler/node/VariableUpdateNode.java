package io.github.locatlang.compiler.node;

/**
 * Created by Locercus on 4/5/2015.
 */
public class VariableUpdateNode extends Node {
    private String actualVariableType;
    private String variableName;
    private Node value;

    public VariableUpdateNode(String actualVariableType, String variableName, Node value) {
        this.actualVariableType = actualVariableType;
        this.variableName       = variableName;
        this.value              = value;
    }

    public String getActualVariableType() {
        return actualVariableType;
    }

    public void setActualVariableType(String actualVariableType) {
        this.actualVariableType = actualVariableType;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public Node getValue() {
        return value;
    }

    public void setValue(Node value) {
        this.value = value;
    }
}
