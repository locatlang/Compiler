package io.github.locatlang.compiler;

import io.github.locatlang.compiler.node.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Locercus on 6/5/2015.
 */
public class SemanticsParser {
    HashMap<File, String[]> files;
    public SemanticsParser(HashMap<File, String[]> files) {
        this.files = files;
    }

    public HashMap<File, List<Node>> initParse() {
        HashMap<File, List<Node>> hashMap = new HashMap<File, List<Node>>();
        for(File file : files.keySet()) {
            String[] instructions = files.get(file);
            List<Node> nodes = new ArrayList<Node>();

            for(String instruction : instructions)
                nodes.addAll(parseInstruction(instruction));

            hashMap.put(file, nodes);
        }

        return hashMap;
    }

    private List<Node> parseInstruction(String instruction) {
        List<Node> nodes = new ArrayList<Node>();

        // PackageNode
        // ImportNode
        Matcher matcher = makeMatcher("^\\s*(package|import)\\s+(\\w+(?:\\.\\w+)*(?:\\.\\*|));$", instruction);
        if(matcher.matches()) {
            String[] namespace = matcher.group(2).split("\\.");
            if(matcher.group(1).equals("package")) {
                nodes.add(new PackageNode(namespace));
                System.out.println("Found PackageNode with namespace " + matcher.group(2));
            }
            else { // import
                nodes.add(new ImportNode(namespace));
                System.out.println("Found ImportNode with namespace " + matcher.group(2));
            }

            return nodes;
        }

        // ClassNode
        matcher = makeMatcher("^\\s*(public|private|protected)\\s+(static|main|)\\s*class\\s+(\\w+)\\s*(?:extends\\s*(\\w+)\\s*|)(?:implements\\s*((?:\\w+)(?:\\s*,\\s*\\w+)*)\\s*|)\\{$", instruction);
        if(matcher.matches()) {
            ClassProtection protection = ClassProtection.PUBLIC;
            switch(matcher.group(1)) {
                case "public":
                    protection = ClassProtection.PUBLIC;
                    break;
                case "private":
                    protection = ClassProtection.PRIVATE;
                    break;
                case "protected":
                    protection = ClassProtection.PROTECTED;
            }

            ClassScope scope = ClassScope.DEFAULT;
            switch(matcher.group(2)) {
                case "static":
                    scope = ClassScope.STATIC;
                    break;
                case "main":
                    scope = ClassScope.MAIN;
            }

            String implementString = matcher.group(5);
            if(implementString == null)
                implementString = "";
            String[] implement = implementString.split("\\s*,\\s*");

            ClassNode node = new ClassNode(protection, scope, matcher.group(3), new Node[0], matcher.group(4), implement);

            nodes.add(node);
            System.out.println("Found ClassNode " + matcher.group(3));

            return nodes;
        }

        // MethodNode
        matcher = makeMatcher("^\\s*(public|private|protected)\\s+(static|)\\s*(\\w+(?:<.+>|)(?:\\[\\]|))\\s+(\\w+)\\s*\\(\\s*.*\\s*\\)\\s*\\{", instruction);
        if(matcher.matches()) {
            MethodProtection protection = MethodProtection.PUBLIC;
            switch(matcher.group(1)) {
                case "public":
                    protection = MethodProtection.PUBLIC;
                    break;
                case "private":
                    protection = MethodProtection.PRIVATE;
                    break;
                case "protected":
                    protection = MethodProtection.PROTECTED;
            }

            MethodScope scope = MethodScope.DEFAULT;
            if(matcher.group(1) != null)
                scope = MethodScope.STATIC;

            // TODO: Finish


            return nodes;
        }



        return nodes;
    }

    private Matcher makeMatcher(String regex, String subject) {
        return Pattern.compile(regex).matcher(subject);
    }
}
