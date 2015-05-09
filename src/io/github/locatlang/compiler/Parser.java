package io.github.locatlang.compiler;

import io.github.locatlang.compiler.parser.Container;
import io.github.locatlang.compiler.parser.ParseCharEvent;
import io.github.locatlang.compiler.parser.StatementContainer;
import io.github.locatlang.compiler.parser.handler.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by creeps on 07/05/15.
 */
public class Parser {
	public Parser() {}

	public Object[] parseStatement(String xinput, boolean topLevel) {
		String input = xinput;
		if( !topLevel ) {
			input = xinput.substring(1, xinput.length() - 1);
		}
		int index = 0;
		char[] spinput = input.toCharArray();
		List<Container> nodes = new ArrayList<>();

		DocCommentEventHandler hDocComment = new DocCommentEventHandler();
		BlockCommentEventHandler hBlkComment = new BlockCommentEventHandler();
		LineCommentEventHandler hLinComment = new LineCommentEventHandler();
		MultiStringEventHandler hMltString = new MultiStringEventHandler();
		StringEventHandler hNrmString = new StringEventHandler();
		CharEventHandler hChar = new CharEventHandler();
		RegExStringEventHandler hRegString = new RegExStringEventHandler();
		SemicolonHandler hSemicolon = new SemicolonHandler();
		BracketsHandler hBrackets = new BracketsHandler();
		String lastObject = "";
		boolean running = true;
		while( running ) {
			String c = "" + spinput[index];
			System.out.print(c);
			/*try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			ParseCharEvent event = new ParseCharEvent(c, input, index, lastObject);
			int hdcskip = hDocComment.handle(event);
			if( hdcskip > 0 ) {
				index += hdcskip;
			} else {
				int blkskip = hBlkComment.handle(event);
				if( blkskip > 0 ) {
					index += blkskip;
				} else {
					int lncskip = hLinComment.handle(event);
					if( lncskip > 0 ) {
						index += lncskip;
					} else {
						int mlsskip = hMltString.handle(event);
						if( mlsskip > 0 ) {
							index += mlsskip;
						} else {
							int strskip = hNrmString.handle(event);
							if( strskip > 0 ) {
								index += strskip;
							} else {
								int chrskip = hChar.handle(event);
								if( chrskip > 0 ) {
									index += chrskip;
								} else {
									int regskip = hRegString.handle(event);
									if( regskip > 0) {
										index += regskip;
									} else {
										int scskip = hSemicolon.handle(event);
										if( scskip > 0 ) {
											index += scskip;
										} else {
											int smtskip = hBrackets.handle(event);
											if( smtskip > 0 ) {
												index += smtskip;
											} else {
												index++;
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if( event.getCloseStatement() ) {
				running = false;
				break;
			} else if( event.getSplit() ) {
				nodes.add(event.getContainer());
				lastObject = "";
			} else if( event.getContent() != null ) {
				lastObject += event.getContent();
			} else {
				lastObject += c;
			}
			if( index >= input.length() ) {
				running = false;
			}
		}
		return new Object[]{nodes.toArray(new Container[nodes.size()]), index + 1};
	}
	public Object[] parseStatement(String input) {
		return parseStatement(input, false);
	}

	public Container[] parse(String input) {
		return ((Container[]) parseStatement(input, true)[0]);
	}

	public Container[] parse(String[] lines) {
		StringBuilder sb = new StringBuilder();
		for( String i : lines ) {
			String cl = i + "\n";
			sb.append(cl);
		}
		return parse(sb.toString());
	}
}
