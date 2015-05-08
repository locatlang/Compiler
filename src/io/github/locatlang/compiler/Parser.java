package io.github.locatlang.compiler;

import io.github.locatlang.compiler.parser.Container;
import io.github.locatlang.compiler.parser.InstructionContainer;
import io.github.locatlang.compiler.parser.ParseCharEvent;
import io.github.locatlang.compiler.parser.StatementContainer;
import io.github.locatlang.compiler.parser.handler.DocCommentHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by creeps on 07/05/15.
 */
public class Parser {
	public Parser() {}

	public List<Container> parse(String input) {
		char[] spinput = input.toCharArray();
		List<Container> nodes = new ArrayList<>();
		int index = 0;
		String currentNode = "";
		DocCommentHandler hDocComment = new DocCommentHandler();
		for( char i : spinput ) {
			String c = "" + i;
			ParseCharEvent event = new ParseCharEvent(c, input, index);
			index++;
			int hdcskip = hDocComment.handle(event);

		}
		return nodes;
	}
}
