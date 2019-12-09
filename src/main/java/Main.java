import Graph.GraphHandler;
import Parser.ClassFinder;
import Parser.FileParser;
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
        MethodCallFinder finder = new MethodCallFinder();
        if (r == JFileChooser.APPROVE_OPTION) {
            files = chose.getSelectedFiles();
            finder.setBuilder(files);
            GraphHandler classGraph = new GraphHandler();
            classGraph.build(ClassFinder.getDependencies(files));//dasdasd
            classGraph.makePrintable();
            try {
                classGraph.exportToPNG("Classes.png");
//                classGraph.exportToJSON("ClassesJSON.json");
//                classGraph.exportToCSV("ClassesCSV.csv");
                classGraph.exportToXML("Classes");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        GraphHandler methodGraph = new GraphHandler();
        methodGraph.build(finder.getDependencies());
        methodGraph.makePrintable();
        try {
            methodGraph.exportToPNG("Methods.png");
//            methodGraph.exportToJSON("MethodsJSON.json");
//            methodGraph.exportToCSV("MethodsCSV.csv");
            methodGraph.exportToXML("Methods");
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
                PackGraph.exportToPNG("Packages.png");
//                PackGraph.exportToJSON("PackagesJSON.json");
//                PackGraph.exportToCSV("PackagesCSV.csv");
                PackGraph.exportToXML("Packages");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        JFileChooser chose2 = new JFileChooser();
        chose2.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chose2.setMultiSelectionEnabled(true);
        chose2.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int r2 = chose2.showOpenDialog(null);
        File[] files2;
        if (r2 == JFileChooser.APPROVE_OPTION) {
            files2 = chose2.getSelectedFiles();
            GraphHandler PackGraph = new GraphHandler();
            PackGraph.build(FileParser.FileDep(files2));
            PackGraph.makePrintable();
            try {
                PackGraph.exportToPNG("Files.png");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}