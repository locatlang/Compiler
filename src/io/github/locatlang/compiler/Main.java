package io.github.locatlang.compiler;

import io.github.locatlang.compiler.node.Node;
import io.github.locatlang.compiler.node.StringNode;
import io.github.locatlang.compiler.parser.Container;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    if( args.length == 1 ) {
		    if (args[0].equals("--help")) {
				System.out.println("-- Locat Compiler Help --\n");
			    System.out.println("If only one argument is specified:");
			    System.out.println("path:");
			    System.out.println("This will compile the file or folder specified in the\npath and" +
					    " create an output file by the same name or a folder by the name \"output\"");
			    System.out.println("\nFlags:");
			    System.out.println("Use by doing: --key value, or -k for single-character flags\n");
			    System.out.println("in:  path to input file or folder");
			    System.out.println("out: path to output file or folder");
		    } else {
			    //argument is (supposed to be) path to folder or file that will be parsed
			    //TODO: Do something
			    FileManager fm = new FileManager(args[0]);
			    List<File> lcfiles = fm.initSearch();
			    System.out.println("-- Found files --");
			    int index = 0;
			    for (File i : lcfiles) {
				    System.out.println(index + " - " + i.getAbsolutePath());
				    index++;
			    }
			    System.out.println("-- Choose file to parse --");
			    boolean gotIndex = false;
			    int dindex = 0;
			    while (!gotIndex) {
				    System.out.print("Enter index: ");
				    Scanner reader = new Scanner(System.in);
				    String inp = reader.nextLine();
				    if (inp.matches("\\d+")) {
					    int in = Integer.parseInt(inp);
					    if (in < 0 || in > (lcfiles.size() - 1)) {
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
			    } catch (IOException e) {
				    e.printStackTrace();
				    System.exit(-1);
			    }
			    System.out.println("-- Read " + Arrays.asList(lines).size() + " lines --");
			    System.out.println("-- Parsing file --");
			    Parser ps = new Parser();
			    Container[] nodes = ps.parse(lines);
			    System.out.println("");
			    System.out.println("-- Parsing complete --");
			    for (Container i : nodes) {
				    System.out.println("~ " + i.toString().replaceAll("\n", "\\\\n"));
			    }
			    System.exit(0);
			    /*
			    System.out.println("-- Init semantics --");
			    String[] instructions = new String[nodes.size()];
			    for (int i = 0; i < nodes.size(); i++) {
				    if (nodes.get(i) instanceof StringNode) {
					    instructions[i] = ((StringNode) nodes.get(i)).getValue();
				    }
			    }
			    HashMap<File, String[]> hashmap = new HashMap<File, String[]>();
			    hashmap.put(thing, instructions);
			    SemanticsParser semanticsParser = new SemanticsParser(hashmap);
			    HashMap<File, List<Node>> semantics = semanticsParser.initParse();
			    System.out.println("-- Done --");*/
		    }
	    } else if( args.length > 1 ) {
			//arguments are (supposed to be) pairs of --<key> <value (can include spaces)>
		    HashMap<String,String> arguments = new HashMap<>();
		    String lastKey = "";
		    String lastValue = null;
		    for( String i : args ) {
				if( i.startsWith("--") ) {
					if( lastValue == null && !lastKey.equals("") ) {
						System.out.println("Argument " + lastKey + " has no value?");
						System.exit(0);
					}
					if( !lastKey.equals("") ) {
						arguments.put(lastKey, lastValue);
					}
					lastKey = i;
					lastValue = null;
					continue;
				}
			    if( lastValue != null ) {
				    lastValue += " ";
				    lastValue += i;
			    } else {
				    lastValue = i;
			    }
		    }
		    if( !lastKey.equals("") && lastValue != null ) {
			    arguments.put(lastKey, lastValue);
		    }
		    for( String k : arguments.keySet() ) {
				String v = arguments.get(k);
			    if( v.startsWith("\"") && v.endsWith("\"") ) {
				    arguments.put(k, v.substring(1, v.length() - 1));
			    }
		    }
		    //actual command
		    String inputPath = null;
		    String outputPath = null;

		    for( String _k : arguments.keySet() ) {
				String v = arguments.get(_k);
			    String k = _k.substring(2);
			    if( k.equals("in") ) {
					inputPath = v;
			    } else if( k.equals("out") ) {
				    outputPath = v;
			    }
		    }
		    if( inputPath == null ) {
				System.out.println("No input path specified (--in <path>)");
			    System.exit(0);
		    } else if( outputPath == null ) {
			    System.out.println("No output path specified (--out <path>)");
			    System.exit(0);
		    }
		    System.out.println("This way of doing things has not been implemented yet.. wait for it!");
	    } else {
		    System.out.println("No arguments specified.\nUse --help for help");
		    System.exit(0);
	    }
    }
}
