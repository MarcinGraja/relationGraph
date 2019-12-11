package Parser;

import Graph.GraphEdge;
import Graph.GraphNode;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class ClassFinder {
    public static LinkedList<String> classList(File file) throws IOException {
        JavaProjectBuilder builder=new JavaProjectBuilder();
        JavaSource src=builder.addSource(file);
        LinkedList<String> tmp=new LinkedList<>();
        for(int i=0;i<src.getClasses().size();i++)
        {
            tmp.add(src.getClasses().get(i).getName());
        }
        return tmp;
    }
    public static GraphEdge classDependency(File source, File target)
    {
        int counter=0;
        JavaProjectBuilder builder=new JavaProjectBuilder();
        JavaSource src= null;
        try {
            src = builder.addSource(source);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LinkedList<String> classes=new LinkedList<>();
        for(int i=0;i<src.getClasses().size();i++) {
            classes.add(src.getClasses().get(i).getName());
        }
        try {
            Scanner scanner=new Scanner(target);
            while(scanner.hasNext()) {
                for(String a:classes) {
                    if(scanner.nextLine().contains(a))
                        counter++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new GraphEdge(new GraphNode(source.getName()),new GraphNode(target.getName()),counter);
    }
    public static ArrayList<GraphEdge> getDependencies(File[] files){
        ArrayList<GraphEdge> tmp = new ArrayList<>();
        for(File a:files)
        {
            for(File b:files)
            {
                if(a!=b)
                {
                    tmp.add(classDependency(a,b));
                }
            }
        }
        return tmp;
    }
}
