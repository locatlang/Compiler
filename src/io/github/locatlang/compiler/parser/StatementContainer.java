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
	public String toString(String prefix) {
		StringBuilder sb = new StringBuilder();
		sb.append(prefix);
		sb.append(details);
		sb.append(prefix);
		sb.append("{");
		for( Container i : content ) {
			String il = i.toString(prefix);
			il = il.replaceAll("^(" + prefix + ")[\\s\\n\\r]+","");
			il = il.replaceAll("[\\s\\n\\r]+$","");
			sb.append(prefix + il);
		}
		sb.append(prefix);
		sb.append("}");
		return sb.toString();
	}
	public String toString() {
		return toString("");
	}
}
