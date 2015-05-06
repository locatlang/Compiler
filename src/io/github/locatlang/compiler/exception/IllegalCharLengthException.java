package io.github.locatlang.compiler.exception;

/**
 * Created by creeps on 06/05/15.
 */
public class IllegalCharLengthException extends IllegalStateException {
	public IllegalCharLengthException() {
		super("Char must be one character long");
	}
}
