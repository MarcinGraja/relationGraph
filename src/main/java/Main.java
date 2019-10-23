import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import Graph.GraphEdge;
import Graph.GraphHandler;
import Graph.GraphNode;
import Parser.MethodCallFinder;

public class Main {

    public static void main(String[] args) {
        JFileChooser chose = new JFileChooser();
        chose.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chose.setMultiSelectionEnabled(true);
        chose.setCurrentDirectory(new File(System.getProperty("user.dir")));

        JFrame jframe = new JFrame();

        int r = chose.showOpenDialog(jframe);
        File[] files;
        MethodCallFinder finder= new MethodCallFinder();
        if (r == JFileChooser.APPROVE_OPTION) {
            files = chose.getSelectedFiles();
            finder.setBuilder(files);
        }
        GraphHandler methodGraph = new GraphHandler();
        methodGraph.build(finder.getDependencies());
        methodGraph.makePrintable();
        try {
            methodGraph.exportToPNG();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}