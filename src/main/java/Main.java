import Graph.GraphHandler;
import Parser.ClassFinder;
import Parser.MethodCallFinder;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        JFileChooser chose = new JFileChooser(); //dfsaf
        chose.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chose.setMultiSelectionEnabled(true);
        chose.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int r = chose.showOpenDialog(null);
        File[] files;
        MethodCallFinder finder= new MethodCallFinder();
        if (r == JFileChooser.APPROVE_OPTION) {
            files = chose.getSelectedFiles();
            finder.setBuilder(files);
            GraphHandler classGraph = new GraphHandler();
            classGraph.build(ClassFinder.getDependencies(files));//dasdasd
            classGraph.makePrintable();
            try {
                classGraph.exportToPNG("Classes.png");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        GraphHandler methodGraph = new GraphHandler();
        methodGraph.build(finder.getDependencies());
        methodGraph.makePrintable();
        try {
            methodGraph.exportToPNG("Methods.png");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}