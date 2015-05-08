package io.github.locatlang.compiler.parser.handler;

import io.github.locatlang.compiler.exception.InfiniteStringException;
import io.github.locatlang.compiler.parser.InstructionContainer;
import io.github.locatlang.compiler.parser.ParseCharEvent;

/**
 * Created by creeps on 08/05/15.
 */
public class RegExStringEventHandler extends ParserEventHandler {
	@Override
	public int handle(ParseCharEvent event) {
		if( event.getCharacter().equals("/") ) {
			if( event.getCharacterByOffset(1).equals("/") ) {
				//this is a comment!
				return 0;
			}
			int endIndex = event.getIndexOfNext("/") + 1;
			if( endIndex < event.getIndex() ) {
				throw new InfiniteStringException();
			}
			int endOffset = endIndex - event.getIndex();
			event.setContent(event.getAfterIndex(endOffset));
			return endOffset;
		}
		return 0;
	}
}
