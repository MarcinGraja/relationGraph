package Parser;

import Graph.GraphEdge;
import Graph.GraphNode;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

public class FilesToMethods {
    static public ArrayList<GraphEdge> FileMethods(File file) {
        ArrayList<GraphEdge> tmp = new ArrayList<>();
        JavaProjectBuilder builder = new JavaProjectBuilder();
        builder.addSourceTree(file);
        ArrayList<JavaMethod> array = new ArrayList<>();
        for (JavaClass javaClass : builder.getClasses()) {
            array.addAll(javaClass.getMethods());
        }
        for(JavaMethod a : array)
        {
            tmp.add(new GraphEdge(new GraphNode(file.getName()),new GraphNode(a.getName()),1));
        }
        return tmp;
    }
    static public ArrayList<GraphEdge> FilesMethods(LinkedList<File> files)
    {
        ArrayList<GraphEdge> tmp = new ArrayList<>();
        for(File f: files)
        {
            tmp.addAll(FileMethods(f));
        }
        return tmp;
    }

}
