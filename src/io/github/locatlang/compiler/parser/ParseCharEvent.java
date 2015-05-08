package io.github.locatlang.compiler.parser;

import java.util.List;

/**
 * Created by creeps on 07/05/15.
 */
public class ParseCharEvent {
	private String character;
	private String fileContent;
	private int index;
	private Container container;
	private boolean split = false;
	private String content;
	private String lastObject;
	private boolean statement = false;
	private boolean closeStatement = false;
	public ParseCharEvent(String character, String fileContent, int index, String lastObject) {
		this.character = character;
		this.fileContent = fileContent;
		this.index = index;
		this.lastObject = lastObject;
	}
	public String getCharacter() {
		return character;
	}
	public String getCharacterByOffset(int offset) {
		int off = index + offset;
		if( fileContent.length() <= off ) {
			return "";
		}
		return fileContent.substring(off, off+1);
	}
	public int getIndexOfNext(String characters) {
		return fileContent.indexOf(characters, index + 1);
	}
	public int getIndex() {
		return index;
	}
	public int getFullLength() {
		return fileContent.length();
	}
	public void setContainer(Container container) {
		this.container = container;
	}
	public Container getContainer() {
		return container;
	}
	public String getAfterIndex(int offset) {
		return fileContent.substring(index, index + offset);
	}
	public void setSplit(boolean split) {
		this.split = split;
	}
	public boolean getSplit() {
		return split;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	public String getLastObject() {
		return lastObject;
	}
	public void setStatement(boolean isStatement) {
		statement = isStatement;
	}
	public boolean getStatement() {
		return statement;
	}
	public void setCloseStatement(boolean closeStatement) {
		this.closeStatement = closeStatement;
	}
	public boolean getCloseStatement() {
		return closeStatement;
	}
	public String getFullString() {
		return fileContent;
	}
	public String getFullStringAfterIndex() {
		return fileContent.substring(index);
	}
}
