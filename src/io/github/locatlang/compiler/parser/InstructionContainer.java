package io.github.locatlang.compiler.parser;

/**
 * Created by creeps on 07/05/15.
 */
public class InstructionContainer extends Container {
	private String content;
	public InstructionContainer(String content) {
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	public String toString() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
