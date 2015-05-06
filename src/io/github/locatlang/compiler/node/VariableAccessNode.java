package io.github.locatlang.compiler.node;

/**
 * Created by Locercus on 3/5/2015.
 */
public class VariableAccessNode extends Node {
    private String value;

    public VariableAccessNode(String value) {
        this.value = value;
    }

    public String getReturnValue() {
        return value;
    }

    public void setReturnValue(String value) {
        this.value = value;
    }
}
