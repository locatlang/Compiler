package io.github.locatlang.compiler.node;

/**
 * Created by Locercus on 4/5/2015.
 */
public class NodeChain extends Node {
    private Node[] nodes;

    public NodeChain(Node[] nodes) {
        this.nodes = nodes;
    }

    public Node[] getNodes() {
        return nodes;
    }

    public void setNodes(Node[] nodes) {
        this.nodes = nodes;
    }
}
