package io.github.locatlang.compiler.parser.handler;

import io.github.locatlang.compiler.Parser;
import io.github.locatlang.compiler.exception.InfiniteStatementException;
import io.github.locatlang.compiler.parser.Container;
import io.github.locatlang.compiler.parser.ParseCharEvent;
import io.github.locatlang.compiler.parser.StatementContainer;

/**
 * Created by creeps on 08/05/15.
 */
public class BracketsHandler extends ParserEventHandler {
	private Parser ps;
	public BracketsHandler() {
		ps = new Parser();
	}

	@Override
	public int handle(ParseCharEvent event) {
		if( event.getCharacter().equals("{") ) {
			event.setStatement(true);
			int endIndex;
			Object[] xparsed = ps.parseStatement(event.getFullStringAfterIndex());
			Container[] parsed = ((Container[]) xparsed[0]);
			int length = ((Integer) xparsed[1]);
			StatementContainer cn = new StatementContainer(event.getLastObject(), parsed);
			endIndex = event.getIndex() + length;
			if( endIndex < event.getIndex() ) {
				throw new InfiniteStatementException();
			}
			int endOffset = endIndex - event.getIndex();
			event.setContent(event.getAfterIndex(endOffset));
			event.setContainer(cn);
			event.setSplit(true);
			return endOffset;
		} else if( event.getCharacter().equals("}") ) {
			event.setCloseStatement(true);
			return 1;
		}
		return 0;
	}
}
