package io.github.locatlang.compiler.exception;

/**
 * Created by creeps on 06/05/15.
 */
public class UnescapedBackslashException extends IllegalStateException {
	public UnescapedBackslashException() {
		super("Unescaped backslash must be escaped");
	}
}
