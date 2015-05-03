package io.github.locatlang.compiler.node;

/**
 * Created by Locercus on 3/5/2015.
 */
public class PackageNode extends Node {
    private String[] namespace;

    public PackageNode(String[] namespace) {
        this.namespace = namespace;
    }

    public String[] getNamespace() {
        return namespace;
    }

    public void setNamespace(String[] namespace) {
        this.namespace = namespace;
    }
}
