package io.github.locatlang.compiler.node;

/**
 * Created by Locercus on 4/5/2015.
 */
public class MethodCallNode extends Node {
    private String name;
    private Node[] values;

    public MethodCallNode(String name, Node[] values) {
        this.name   = name;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node[] getValues() {
        return values;
    }

    public void setValues(NodeChain[] values) {
        this.values = values;
    }
}
