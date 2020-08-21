package Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FileTree {
    LinkedList<Scope> scopes;
    public FileTree(File input) throws FileNotFoundException {
        Scanner scanner = new Scanner(input);
        String line;
        while (scanner.hasNextLine()){
            line = scanner.nextLine();
            if (line.contains("{"))
            {
                scopes.addLast(new Scope(scanner));
            }
        }
    }
}
