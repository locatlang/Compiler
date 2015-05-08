package io.github.locatlang.compiler.parser.handler;

import io.github.locatlang.compiler.parser.CommentContainer;
import io.github.locatlang.compiler.parser.ParseCharEvent;

/**
 * Created by creeps on 08/05/15.
 */
public class LineCommentEventHandler extends ParserEventHandler {
	@Override
	public int handle(ParseCharEvent event) {
		boolean cnd0 = event.getCharacter().equals("/");
		boolean cnd1 = event.getCharacterByOffset(1).equals("/");
		if( cnd0 && cnd1 ) {
			int endIndex = event.getIndexOfNext("\n");
			if( endIndex < event.getIndex() ) {
				return event.getFullLength() - event.getIndex();
			}
			int endOffset = endIndex - event.getIndex();
			event.setContainer(new CommentContainer(event.getAfterIndex(endOffset)));
			event.setSplit(true);
			return endOffset;
		}
		return 0;
	}
}
