package io.github.locatlang.compiler.exception;

/**
 * Created by creeps on 08/05/15.
 */
public class InfiniteCommentException extends IllegalStateException {
	public InfiniteCommentException() {
		super("Comment is not closed anywhere");
	}
}
