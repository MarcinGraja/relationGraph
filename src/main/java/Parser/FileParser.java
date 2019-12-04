package Parser;

import Graph.GraphEdge;
import Graph.GraphNode;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class FileParser {

    public static ArrayList<GraphEdge> FileDependencies(File source)
    {
        JavaProjectBuilder builder=new JavaProjectBuilder();
        JavaSource src= null;
        try {
            src = builder.addSource(source);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LinkedList<String> classes=new LinkedList<>();
        for(int i=0;i<src.getClasses().size();i++)
        {
            classes.add(src.getClasses().get(i).getName());
        }
        ArrayList<JavaMethod> methodsJava= new ArrayList<>();
        for (JavaClass javaClass: builder.getClasses()){
            methodsJava.addAll(javaClass.getMethods());
        }
        LinkedList<String> methodsString=new LinkedList<>();
        for(JavaMethod a : methodsJava)
        {
            methodsString.add(a.getName());
        }
        ArrayList<GraphEdge> tmp=new ArrayList<>();
        for(String s : classes)
        {
            tmp.add(new GraphEdge(new GraphNode(source.getName()),new GraphNode(("Class "+s)),1));
        }
        for(String s : methodsString)
        {
            tmp.add(new GraphEdge(new GraphNode(source.getName()),new GraphNode(("Method "+s)),1));
        }
        return tmp;
    }
    public static ArrayList<GraphEdge> FileDep(File[] files)
    {
        ArrayList<GraphEdge> tmp=new ArrayList<>();
        for(File f : files) {
            tmp.addAll(FileDependencies(f));
        }
        return tmp;
    }
}
