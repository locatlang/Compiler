package io.github.locatlang.compiler.exception;

/**
 * Created by creeps on 08/05/15.
 */
public class InfiniteStringException extends IllegalStateException {
	public InfiniteStringException() {
		super("String has no end");
	}
}
