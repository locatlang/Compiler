package io.github.locatlang.compiler.node;

/**
 * Created by Locercus on 4/5/2015.
 */
public class CharNode extends Node {
    private char value;

    public CharNode(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }
}
