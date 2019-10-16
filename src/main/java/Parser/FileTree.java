package Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FileTree {
    LinkedList<Scope> scopes;

    FileTree(File input, HashSet<String> classes) throws FileNotFoundException {
        Scanner scanner = new Scanner(input);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()){
            stringBuilder.append(scanner.nextLine());
        }
        String fileString = stringBuilder.toString();
        int currentPosition = fileString.indexOf("{");
    }

}
