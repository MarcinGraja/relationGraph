package Parser;

import Graph.Dependency;
import Graph.NodeFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileTree {
    private Scope scope = new Scope();
    String fileName;
    String fileContent;
    public FileTree(File input, HashSet<String> classes) throws FileNotFoundException {
        fileName=input.getName();
        Scanner scanner = new Scanner(input);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()){
            stringBuilder.append(scanner.nextLine());
        }
        fileContent = stringBuilder.toString();
        scope.createScope(fileContent, classes.stream().filter(p -> !input.getName().equals(p + ".java")).collect(Collectors.toCollection(HashSet::new)));
    }
    public Dependency[] getDependencies()
    {
        HashMap<String,Integer> nazwa=scope.funkcja();
        LinkedList<Dependency> dependencies=new LinkedList<>();
        for(String key : nazwa.keySet()){
            dependencies.add(new Dependency(new NodeFile(fileName), new NodeFile(key), nazwa.get(key)));
        }
        for (Dependency dependency: dependencies){
            dependency.print();
        }
        System.err.println("printed dependencies I hope; FileTree.getDependencies");
        return dependencies.toArray(new Dependency[0]);
    }

    public String getFileName() {
        return fileName;
    }
}
