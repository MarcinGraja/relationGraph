package Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileTree {
    private Scope scope = new Scope();
    public FileTree(File input, HashSet<String> classes) throws FileNotFoundException {
        Scanner scanner = new Scanner(input);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()){
            stringBuilder.append(scanner.nextLine());
        }
        String fileString = stringBuilder.toString();
        scope.createScope(fileString, classes.stream().filter(p -> p.equals(input.getName() + ".java")).collect(Collectors.toCollection(HashSet::new)));
        //int currentPosition = fileString.indexOf("{");
        //while (currentPosition!=-1){
           // scopes.addLast(new Scope());
          //  currentPosition = fileString.indexOf("{",
         //           scopes.getLast().createScope(fileString, classes);
       // }
    }
}
