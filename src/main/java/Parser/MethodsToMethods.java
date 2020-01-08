package Parser;

import Graph.GraphEdge;
import Graph.GraphNode;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

public class MethodsToMethods {
    static public ArrayList<GraphEdge> ListMethods(LinkedList<File> files) {
        ArrayList<GraphEdge> tmp=new ArrayList<>();
        ArrayList<String> Methods = new ArrayList<>();
        ArrayList<String> SourceCodes = new ArrayList<>();
        JavaProjectBuilder builder = new JavaProjectBuilder();
        JavaProjectBuilder builder2 = new JavaProjectBuilder();
        for(File f: files) {
            builder.addSourceTree(f);
            builder2.addSourceTree(f);
        }
        ArrayList<JavaMethod> array = new ArrayList<>();
        for (JavaClass javaClass : builder.getClasses()) {
            array.addAll(javaClass.getMethods());
        }
        for(JavaMethod a:array)
        {
            Methods.add(a.getName());
        }
        ArrayList<JavaMethod> array2 = new ArrayList<>();
        for (JavaClass javaClass : builder2.getClasses()) {
            array2.addAll(javaClass.getMethods());
        }
        for(JavaMethod a:array2)
        {
            SourceCodes.add(a.getSourceCode());
        }
        for(JavaMethod sor:array2)
        {
            for(String met: Methods)
            {
                if(sor.getSourceCode().contains(met) && !sor.getName().equals(met))
                {
                    tmp.add(new GraphEdge(new GraphNode(sor.getName()),new GraphNode(met),1));
                }
            }
        }
        return tmp;
    }

}
