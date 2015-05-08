package io.github.locatlang.compiler.exception;

/**
 * Created by creeps on 08/05/15.
 */
public class InfiniteStatementException extends IllegalStateException {
	public InfiniteStatementException() {
		super("Statement {} has no end");
	}
}
