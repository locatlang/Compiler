package io.github.locatlang.compiler;

import io.github.locatlang.compiler.exception.IllegalCharLengthException;
import io.github.locatlang.compiler.exception.UnescapedBackslashException;
import io.github.locatlang.compiler.node.Node;
import io.github.locatlang.compiler.node.StringNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by creeps on 04/05/15.
 */
public class lParser {
	private String in;
	public lParser(String[] input) {
		in = combineLines(input);
	}

	public String combineLines(String[] lines) {
		StringBuilder output = new StringBuilder();
		boolean first = true;
		for( String i : lines ) {
			if(!first) {
				output.append("\n");
			}
			first = false;
			output.append(i);
		}
		return output.toString();
	}

	public void jmSplitCombined(String clines) {
		//splits input into groups of 3 characters
		Pattern comp = Pattern.compile("(?:(...)|(.+))");
		Matcher cm = comp.matcher(clines);
		List<String> pchunks = new ArrayList<String>();
		while(cm.find()) {
			pchunks.add(cm.group());
		}
		String[] chunks = pchunks.toArray(new String[pchunks.size()]);
		boolean skipQuotes = false;
		boolean skipMulti = false;
		for(String chunk : chunks) {
			Pattern quotepattern = Pattern.compile("[^\\\\](\")");
			Matcher quotematcher = quotepattern.matcher(chunk);
			int qmatches = 0;
			while (quotematcher.find()) {
				qmatches++;
			}
			Pattern mlpattern = Pattern.compile("[^\\\\](`)");
			Matcher mlmatcher = mlpattern.matcher(chunk);
			int mlmatches = 0;
			while (mlmatcher.find() && qmatches%2 == 0 ) {
				mlmatches++;
			}
			if( mlmatches % 2 != 0 ) {
				qmatches = 0;
			}
			if (qmatches % 2 != 0) {
				skipQuotes = !skipQuotes;
			}
			if (mlmatches % 2 != 0) {
				skipMulti = !skipMulti;
			}
			System.out.println("CHUNK " + chunk + "");
			if (skipMulti || skipQuotes) {
				System.out.println("SKIPPED");
			}
		}
	}
	String[] escapes = new String[]{
			"b",
			"t",
			"n",
			"v",
			"f",
			"r",
			"\"",
			"`",
			"\\",
			"0",
			"u"
	};
	public List<Node> splitCombined(String clines) {
		char[] chars = clines.toCharArray();
		boolean openQuotes = false, openML = false, openChar = false;
		boolean openComment = false, multiComment = false;
		String lastString = "";
		String lastObject = "";
		String lastChar = "";
		List<Node> nodes = new ArrayList<>();
		for( char _c : chars ) {
			String c = "" + _c;
			if( openComment ) {
				lastObject += c;
				if( c.equals("\n") && !multiComment ) {
					openComment = false;
					nodes.add(new StringNode(lastObject));
					lastObject = "";
				} else if( c.equals("/") && lastChar.equals("*") && multiComment ) {
					openComment = false;
					nodes.add(new StringNode(lastObject));
					lastObject = "";
				}
				continue;
			}
			if( openQuotes || openML || openChar ) {
				lastObject += c;
				if( openQuotes && c.equals("\"") ) {
					openQuotes = false;
//					nodes.add(new StringNode(lastString));
//					lastString = "";
//					lastObject += c;
					continue;
				} else if( openML && c.equals("`") ) {
					openML = false;
					//TODO: use special class instead of StringNode
//					nodes.add(new StringNode(lastString));
//					lastString = "";
//					lastObject += c;
					continue;
				} else if( openChar && c.equals("'") ) {
					if( lastString.length() != 1 &&
							!lastString.substring(0,1).equals("\\") &&
							Arrays.asList(escapes).contains(lastString.substring(1,2)) ) {
						throw new IllegalCharLengthException();
					}
					openChar = false;
//					nodes.add(new CharNode(lastString.charAt(0)));
//					lastString = "";
//					lastObject += c;
					continue;
				}
				lastString += c;
				//TODO: Add checking if characters aren't invalid
				if( openQuotes && c.equals("\n") ) {
					//TODO: Throw error
				}
				if( lastChar == "\\" && !Arrays.asList(escapes).contains(c) ) {
					throw new UnescapedBackslashException();
				}
				//TODO: add support for \u0000 etc
				continue;
			}
			if( c.equals("/") && lastChar.equals("/") ) {
				lastObject = lastObject.substring(0, lastObject.length() - 1);
				nodes.add(new StringNode(lastObject));
				lastObject = "//";
				openComment = true;
				multiComment = false;
				continue;
			} else if( c.equals("*") && lastChar.equals("/") ) {
				lastObject = lastObject.substring(0, lastObject.length() - 1);
				nodes.add(new StringNode(lastObject));
				lastObject = "/*";
				openComment = true;
				multiComment = true;
				continue;
			}
			if( c.equals(" ") ) {
				if( lastChar.equals(" ") ) {
					continue;
				}
			}
			if( c.equals("\t") ) {
				continue;
			}

			if( c.equals(";") || c.equals("{") || c.equals("}") ) {
				lastObject += c;
				nodes.add(new StringNode(lastObject));
				lastObject = "";
			} else if( c.equals("\"") ) {
				//don't need to check if openML etc are false since the above skips this code
				if( !openQuotes ) {
					lastObject += c;
					openQuotes = true;
//					nodes.add(new StringNode(lastObject));
//					lastObject = "";
				}
			} else if( c.equals("`") ) {
				if( !openML ) {
					lastObject += c;
					openML = true;
//					nodes.add(new StringNode(lastObject));
//					lastObject = "";
				}
			} else if( c.equals("'") ) {
				if( !openChar ) {
					lastObject += c;
					openChar = true;
//					nodes.add(new StringNode(lastObject));
//					lastObject = "";
				}
			} else {
				lastObject += c;
			}
			lastChar = c;
		}
		nodes.add(new StringNode(lastObject));
		return nodes;
	}
}