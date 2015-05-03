package io.github.locatlang.compiler.node;

/**
 * Created by Locercus on 3/5/2015.
 */
public class ReturnNode extends Node {
    private Node returnValue;

    public ReturnNode(Node returnValue) {
        this.returnValue = returnValue;
    }

    public Node getNamespace() {
        return returnValue;
    }

    public void setNamespace(String[] namespace) {
        this.returnValue = returnValue;
    }
}
