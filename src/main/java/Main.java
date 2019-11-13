import Graph.GraphHandler;
import Parser.ClassFinder;
import Parser.MethodCallFinder;
import Parser.PackageFinder;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        JFileChooser chose = new JFileChooser();
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
            classGraph.build(ClassFinder.getDependencies(files));
            classGraph.makePrintable();
            try {
                classGraph.exportToPNG("Classes");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        GraphHandler methodGraph = new GraphHandler();
        methodGraph.build(finder.getDependencies());
        methodGraph.makePrintable();
        try {
            methodGraph.exportToPNG("Methods");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JFileChooser chose1 = new JFileChooser();
        chose1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chose1.setMultiSelectionEnabled(true);
        chose1.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int r1 = chose1.showOpenDialog(null);
        File[] files1;
        if (r1 == JFileChooser.APPROVE_OPTION) {
            files1 = chose1.getSelectedFiles();
            GraphHandler PackGraph = new GraphHandler();
            PackGraph.build(PackageFinder.ClassDependency(files1));
            PackGraph.makePrintable();
            try {
                 PackGraph.exportToPNG("Packages");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}