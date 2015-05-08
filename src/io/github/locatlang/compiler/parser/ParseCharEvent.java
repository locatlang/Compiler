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
	public ParseCharEvent(String character, String fileContent, int index) {
		this.character = character;
		this.fileContent = fileContent;
		this.index = index;
	}
	public String getCharacter() {
		return character;
	}
	public String getCharacterByOffset(int offset) {
		int off = index + offset;
		return fileContent.substring(off, off+1);
	}
	public int getIndexOfNext(String characters) {
		return fileContent.indexOf(characters, index);
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
}
