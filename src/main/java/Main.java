import javax.sound.midi.SysexMessage;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import Parser.FileTree;

public class Main {

    public static void main(String[] args) {
        JFileChooser chose = new JFileChooser();
        chose.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chose.setMultiSelectionEnabled(true);
        chose.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int r = chose.showOpenDialog(new JFrame());
        if (r == chose.APPROVE_OPTION) {
            String filepath = chose.getSelectedFile().getAbsolutePath();
            File[] files = chose.getSelectedFiles();
            HashSet<String> classList=new HashSet<String>();
            FileTree[] trees=new FileTree[files.length];
            for(int i=0;i<files.length;i++)
            {

                System.out.println(files[i].getName().split("\\.")[0]);
                classList.add(files[i].getName().split("\\.")[0]);

            }
            for(int i = 0; i < files.length; i++) {
                System.out.println(i);
                try {
                    trees[i]=new FileTree(files[i], classList);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
