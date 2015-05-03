package io.github.locatlang.compiler.node;

/**
 * Created by Locercus on 4/5/2015.
 */
public class LessThanOperatorNode extends Node {
    private Node left;
    private Node right;

    public LessThanOperatorNode(Node left, Node right) {
        this.left  = left;
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
