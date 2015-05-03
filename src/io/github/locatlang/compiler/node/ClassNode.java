package io.github.locatlang.compiler.node;

/**
 * Created by Locercus on 3/5/2015.
 */
public class ClassNode extends Node {
    private ClassProtection protection;
    private ClassScope scope;
    private String name;
    private Node[] contents;

    public ClassNode(ClassProtection protection, ClassScope scope, String name, Node[] contents) {
        this.protection = protection;
        this.scope      = scope;
        this.name       = name;
        this.contents   = contents;
    }

    public ClassProtection getProtection() {
        return protection;
    }

    public void setProtection(ClassProtection protection) {
        this.protection = protection;
    }

    public ClassScope getScope() {
        return scope;
    }

    public void setScope(ClassScope scope) {
        this.scope = scope;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node[] getContents() {
        return contents;
    }

    public void setContents(Node[] contents) {
        this.contents = contents;
    }
}
