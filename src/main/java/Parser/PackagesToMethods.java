package Parser;

import Graph.GraphEdge;
import Graph.GraphNode;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class PackagesToMethods {
    static public ArrayList<GraphEdge> PackageMethod(File file) {
        ArrayList<GraphEdge> tmp = new ArrayList<>();
        JavaProjectBuilder builder = new JavaProjectBuilder();
        builder.addSourceTree(file);
        Collection<JavaClass>klasy=builder.getClasses();
        String nazwa="paczka";
        for(JavaClass kl:klasy)
        {
            nazwa=kl.getPackageName();
        }
        ArrayList<JavaMethod> array = new ArrayList<>();

            for (JavaClass javaClass : builder.getClasses()) {
            array.addAll(javaClass.getMethods());
        }
        for(JavaMethod a : array)
        {

            tmp.add(new GraphEdge(new GraphNode(nazwa),new GraphNode(a.getName()),1));
        }
        return tmp;
    }
    static public ArrayList<GraphEdge> PackagesMethods(LinkedList<File> files)
    {
        ArrayList<GraphEdge> tmp = new ArrayList<>();
        for(File f: files)
        {
            tmp.addAll(PackageMethod(f));
        }
        return tmp;
    }
}
