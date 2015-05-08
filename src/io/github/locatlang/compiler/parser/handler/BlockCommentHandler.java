package io.github.locatlang.compiler.parser.handler;

import io.github.locatlang.compiler.exception.InfiniteCommentException;
import io.github.locatlang.compiler.parser.CommentContainer;
import io.github.locatlang.compiler.parser.ParseCharEvent;

/**
 * Created by creeps on 08/05/15.
 */
public class BlockCommentHandler extends CharHandler {
	@Override
	public int handle(ParseCharEvent event) {
		boolean cnd0 = event.getCharacter().equals("/");
		boolean cnd1 = event.getCharacterByOffset(1).equals("*");
		if( cnd0 && cnd1 ) {
			int endIndex = event.getIndexOfNext("*/");
			if( endIndex < event.getIndex() ) {
				throw new InfiniteCommentException();
			}
			int endOffset = endIndex - event.getIndex();
			event.setContainer(new CommentContainer(event.getAfterIndex(endOffset)));
			return endOffset;
		}
		return 0;
	}
}
