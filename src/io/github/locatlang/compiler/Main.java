package io.github.locatlang.compiler;

import io.github.locatlang.compiler.node.Node;
import io.github.locatlang.compiler.node.StringNode;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    if( args.length == 1 ) {
		    //argument is (supposed to be) path to folder or file that will be parsed
		    //TODO: Do something
		    FileManager fm = new FileManager(args[0]);
		    List<File> lcfiles = fm.initSearch();
		    System.out.println("-- Found files --");
		    int index = 0;
		    for( File i : lcfiles ) {
			    System.out.println(index + " - " + i.getAbsolutePath());
			    index++;
		    }
		    System.out.println("-- Choose file to parse --");
		    boolean gotIndex = false;
		    int dindex = 0;
		    while( !gotIndex ) {
		        System.out.print("Enter index: ");
		        Scanner reader = new Scanner(System.in);
				String inp = reader.nextLine();
			    if( inp.matches("\\d+") ) {
				    int in = Integer.parseInt(inp);
				    if( in < 0 || in > (lcfiles.size() - 1) ) {
					    System.out.println("No such file");
				    } else {
					    gotIndex = true;
					    dindex = in;
				    }
			    } else {
				    System.out.println("Input must match \\d+");
			    }
		    }
		    File thing = lcfiles.get(dindex);
		    System.out.println("-- Reading file " + thing.getAbsolutePath() + " --");
		    String[] lines = new String[]{};
		    try {
		        lines = fm.readFile(thing);
		    } catch(IOException e) {
			    e.printStackTrace();
			    System.exit(-1);
		    }
		    System.out.println("-- Read " + Arrays.asList(lines).size() + " lines --");
		    System.out.println("-- Parsing file --");
		    Parser ps = new Parser(lines);
		    String cl = ps.combineLines(lines);
		    List<Node> nodes = ps.splitCombined(cl);
		    System.out.println("");
		    System.out.println("-- Parsing complete --");
		    for( Node i : nodes ) {
			    System.out.println("~ " + ((StringNode) i).getValue().replaceAll("\n","\u21aa"));
		    }
			System.out.println("-- Init semantics --");
			String[] instructions = new String[nodes.size()];
			for(int i = 0; i < nodes.size(); i++) {
				if(nodes.get(i) instanceof StringNode) {
					instructions[i] = ((StringNode) nodes.get(i)).getValue();
				}
			}
			HashMap<File, String[]> hashmap =  new HashMap<File, String[]>();
			hashmap.put(thing, instructions);
			SemanticsParser semanticsParser = new SemanticsParser(hashmap);
			HashMap<File, List<Node>> semantics = semanticsParser.initParse();
			System.out.println("-- Done --");
	    } else if( args.length > 1 ) {
			//arguments are (supposed to be) pairs of <key> <value>
		    if( args.length % 2 != 0 ) {
			    System.out.println("Arguments must be <key> <value> pairs");
			    System.exit(0);
		    }
		    //TODO: Do something
	    } else {
		    System.out.println("No arguments specified");
		    System.exit(0);
	    }
    }
}
