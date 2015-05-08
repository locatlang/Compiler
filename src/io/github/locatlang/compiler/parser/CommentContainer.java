package io.github.locatlang.compiler.parser;

/**
 * Created by creeps on 07/05/15.
 */
public class CommentContainer extends Container {
	private String comment;
	public CommentContainer(String comment) {
		this.comment = comment;
	}
	public String getComment() {
		return comment;
	}
	@Override
	public String toString() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
