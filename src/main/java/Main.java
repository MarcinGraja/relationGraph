import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;

import Graph.Dependency;
import Parser.MethodCallFinder;

public class Main {

    public static void main(String[] args) {
        JFileChooser chose = new JFileChooser();
        chose.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chose.setMultiSelectionEnabled(true);
        chose.setCurrentDirectory(new File(System.getProperty("user.dir")+"/src/main/java/parser"));

        JFrame jframe = new JFrame();

        int r = chose.showOpenDialog(jframe);
        File[] files;
        MethodCallFinder finder= new MethodCallFinder();
        if (r == JFileChooser.APPROVE_OPTION) {
            files = chose.getSelectedFiles();
            finder.setBuilder(files);
        }
        finder.getDependencies();

    }
}