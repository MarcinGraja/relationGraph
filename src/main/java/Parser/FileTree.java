package Parser;

import Graph.GraphEdge;
import Graph.GraphNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileTree {
    private Scope scope = new Scope();
    private String fileName;
    public FileTree(File input, HashSet<String> classes) throws FileNotFoundException {
        fileName=input.getName();
        Scanner scanner = new Scanner(input);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()){
            stringBuilder.append(scanner.nextLine());
        }
        String fileString = stringBuilder.toString();
        scope.createScope(fileString, classes.stream().filter(p -> !input.getName().equals(p + ".java")).collect(Collectors.toCollection(HashSet::new)));
        //int currentPosition = fileString.indexOf("{");
        //while (currentPosition!=-1){
           // scopes.addLast(new Scope());
          //  currentPosition = fileString.indexOf("{",
         //           scopes.getLast().createScope(fileString, classes);
       // }
    }
    public GraphEdge[] getDependencies()
    {
        HashMap<String,Integer> nazwa=scope.funkcja();
        LinkedList<GraphEdge> dependencies=new LinkedList<>();
        for(String key : nazwa.keySet()){
            dependencies.add(new GraphEdge(new GraphNode(fileName), new GraphNode(key), nazwa.get(key)));
        }
        return dependencies.toArray(new GraphEdge[0]);
    }

    public String getFileName() {
        return fileName;
    }
}
