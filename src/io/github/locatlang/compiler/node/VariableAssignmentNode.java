package io.github.locatlang.compiler.node;

/**
 * Created by Locercus on 4/5/2015.
 */
public class VariableAssignmentNode extends Node {
    private String variableType;
    private String actualVariableType;
    private String variableName;
    private Node value;

    public VariableAssignmentNode(String variableType, String actualVariableType, String variableName, Node value) {
        this.variableType       = variableType;
        this.actualVariableType = actualVariableType;
        this.variableName       = variableName;
        this.value              = value;
    }

    public String getVariableType() {
        return variableType;
    }

    public void setVariableType(String variableType) {
        this.variableType = variableType;
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
