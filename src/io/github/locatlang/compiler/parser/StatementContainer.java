package io.github.locatlang.compiler.parser;

/**
 * Created by creeps on 07/05/15.
 */
public class StatementContainer extends Container {
	private String details;
	private Container[] content;
	public StatementContainer(String details, Container[] content) {
		this.details = details;
		this.content = content;
	}
	public String getDetails() {
		return details;
	}
	public Container[] getContent() {
		return content;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public void setContent(Container[] content) {
		this.content = content;
	}
}
