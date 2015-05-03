package io.github.locatlang.compiler.node;

/**
 * Created by Locercus on 4/5/2015.
 */
public class ConstructorNode extends Node {
    private MethodProtection protection;
    private ParameterNode[] parameters;
    private Node[] contents;

    public ConstructorNode(MethodProtection protection, ParameterNode[] parameters, Node[] contents) {
        this.protection = protection;
        this.parameters = parameters;
        this.contents   = contents;
    }

    public MethodProtection getProtection() {
        return protection;
    }

    public void setProtection(MethodProtection protection) {
        this.protection = protection;
    }

    public ParameterNode[] getParameters() {
        return parameters;
    }

    public void setParameters(ParameterNode[] parameters) {
        this.parameters = parameters;
    }

    public Node[] getContents() {
        return contents;
    }

    public void setContents(Node[] contents) {
        this.contents = contents;
    }
}
