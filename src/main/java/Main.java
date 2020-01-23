import Graph.GraphEdge;
import Graph.GraphHandler;
import Parser.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println(GitHandler.getVersion());
        JFileChooser chose = new JFileChooser();
        chose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chose.setMultiSelectionEnabled(true);
        chose.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int r = chose.showOpenDialog(null);
        File[] files;
        MethodCallFinder finder = new MethodCallFinder();
        if (r == JFileChooser.APPROVE_OPTION) {
            files = chose.getSelectedFiles();
            LinkedList<File> filesall = new LinkedList<>();
            for (File a : files) {
                if (a.isDirectory()) {
                    for (File b : a.listFiles())
                        filesall.add(b);
                } else {
                    filesall.add(a);
                }
            }
            finder.setBuilder(filesall);
            GraphHandler classGraph = new GraphHandler();
            classGraph.build(ClassFinder.getDependencies(filesall));
            classGraph.makePrintable();
            GraphHandler methodGraph = new GraphHandler();
            methodGraph.build(finder.getDependencies());
            methodGraph.makePrintable();
            GraphHandler PackGraph = new GraphHandler();
            PackGraph.build(PackageFinder.ClassDependency(filesall));
            PackGraph.makePrintable();
            GraphHandler FilestoMethodsGraph = new GraphHandler();
            FilestoMethodsGraph.build(FilesToMethods.FilesMethods(filesall));
            FilestoMethodsGraph.makePrintable();
            GraphHandler PackagesMethodsGraph = new GraphHandler();
            PackagesMethodsGraph.build(PackagesToMethods.PackagesMethods(filesall));
            PackagesMethodsGraph.makePrintable();
            GraphHandler MethodsToMethodsGraph = new GraphHandler();
            MethodsToMethodsGraph.build(MethodsToMethods.ListMethods(filesall));
            MethodsToMethodsGraph.makePrintable();
            GraphHandler PackGraph2 = new GraphHandler();
              PackGraph2.build(FileParser.FileDep(filesall));
            PackGraph2.makePrintable();
            GraphHandler AllTogether = new GraphHandler();
            LinkedList<GraphEdge> all = new LinkedList<>();
            System.out.println("Do you want Files To Methods on graph? (y/n): ");
            Scanner scanner = new Scanner(System.in);
            if (scanner.nextLine().equals("y")) {
                all.addAll(FilesToMethods.FilesMethods(filesall));
            }
            System.out.println("Do you want Packages To Methods on graph? (y/n): ");
            if (scanner.nextLine().equals("y")) {
                all.addAll(PackagesToMethods.PackagesMethods(filesall));
            }
            System.out.println("Do you want Methods To Methods on graph? (y/n): ");
            if (scanner.nextLine().equals("y")) {
                all.addAll(MethodsToMethods.ListMethods(filesall));
            }
            if(all.size()>0) {
                AllTogether.build(all);
                AllTogether.makePrintable();
            }else{
                System.out.println("You can not create a graph without edges");
            }
                try {
                    classGraph.exportToPNG("Classes.png");
                    classGraph.exportToXML("Classes");
                    methodGraph.exportToPNG("Methods.png");
                    methodGraph.exportToXML("Methods");
                    PackGraph.exportToPNG("Packages.png");
                    PackGraph.exportToXML("Packages");
                    FilestoMethodsGraph.exportToPNG("FilesMethods.png");
                    PackagesMethodsGraph.exportToPNG("PackagesToMethods.png");
                    MethodsToMethodsGraph.exportToPNG("MethodsToMethods.png");
            if(all.size()>0) {
                AllTogether.exportToPNG("All.png");
            }
                    PackGraph2.exportToPNG("Files.png");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
