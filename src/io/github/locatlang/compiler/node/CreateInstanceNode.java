package io.github.locatlang.compiler.node;

/**
 * Created by Locercus on 4/5/2015.
 */
public class CreateInstanceNode extends Node {
    private String className;
    private Node[] values;

    public CreateInstanceNode(String className, Node[] values) {
        this.className = className;
        this.values    = values;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Node[] getValues() {
        return values;
    }

    public void setValues(Node[] values) {
        this.values = values;
    }
}
