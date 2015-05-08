package io.github.locatlang.compiler.parser.handler;

import io.github.locatlang.compiler.parser.InstructionContainer;
import io.github.locatlang.compiler.parser.ParseCharEvent;

/**
 * Created by creeps on 08/05/15.
 */
public class SemicolonHandler extends ParserEventHandler {
	@Override
	public int handle(ParseCharEvent event) {
		if( event.getCharacter().equals(";") ) {
			event.setContainer(new InstructionContainer(event.getLastObject() + ";"));
			event.setSplit(true);
			return 1;
		}
		return 0;
	}
}
