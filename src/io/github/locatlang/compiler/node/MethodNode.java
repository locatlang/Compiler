package io.github.locatlang.compiler.node;

/**
 * Created by Locercus on 4/5/2015.
 */
public class MethodNode extends Node {
    private MethodProtection protection;
    private MethodScope scope;
    private String returnType;
    private ParameterNode[] parameters;
    private Node[] contents;

    public MethodNode(MethodProtection protection, MethodScope scope, String returnType, ParameterNode[] parameters, Node[] contents) {
        this.protection = protection;
        this.scope      = scope;
        this.returnType = returnType;
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

    public MethodScope getScope() {
        return scope;
    }

    public void setScope(MethodScope scope) {
        this.scope = scope;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
}
