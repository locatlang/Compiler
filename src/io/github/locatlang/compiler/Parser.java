package io.github.locatlang.compiler;

import io.github.locatlang.compiler.node.CharNode;
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
public class Parser {
	private String in;
	public Parser(String[] input) {
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
	public void splitCombined(String clines) {
		char[] chars = clines.toCharArray();
		boolean openQuotes = false, openML = false, openChar = false;
		String lastString = "";
		String lastChar = "";
		List<Node> nodes = new ArrayList<Node>();
		for( char _c : chars ) {
			String c = "" + _c;
			if( openQuotes || openML || openChar ) {
				lastString += c;
				//TODO: Add checking if characters aren't invalid
				if( openChar && lastString.length() > 1 ) {
					//TODO: Throw error
				}
				if( openQuotes && c.equals("\n") ) {
					//TODO: Throw error
				}
				if( lastChar == "\\" && !Arrays.asList(escapes).contains(c) ) {
					//TODO: Throw error
				}
				//TODO: add support for \u0000 etc
				continue;
			}
			if( c.equals("\"") ) {
				//don't need to check if openML etc are false since the above skips this code
				if( !openQuotes ) {
					openQuotes = true;
				} else if( !openML && !openChar ) {
					openQuotes = false;
					nodes.add(new StringNode(lastString));
					lastString = "";
				}
			} else if( c.equals("`") ) {
				if( !openML ) {
					openML = true;
				} else if( !openQuotes && !openChar ) {
					openML = false;
					//TODO: use special class instead of StringNode
					nodes.add(new StringNode(lastString));
					lastString = "";
				}
			} else if( c.equals("'") ) {
				if( !openChar ) {
					openChar = true;
				} else if( !openQuotes && !openML ) {
					openChar = false;
					nodes.add(new CharNode(lastString.charAt(0)));
					lastString = "";
				}
			}
			lastChar = c;
		}
	}
}
