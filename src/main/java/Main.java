import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;

import Graph.Dependency;

public class Main {

    public static void main(String[] args) {
        JFileChooser chose = new JFileChooser();
        chose.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chose.setMultiSelectionEnabled(true);
        chose.setCurrentDirectory(new File(System.getProperty("user.dir")));

        JFrame jframe = new JFrame();

        int r = chose.showOpenDialog(jframe);
        if (r == JFileChooser.APPROVE_OPTION) {

        }
    }
}