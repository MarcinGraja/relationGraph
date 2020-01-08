package Parser;

import Graph.GraphEdge;
import Graph.GraphNode;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodCallFinder {
    private JavaProjectBuilder builder;
    public void setBuilder(LinkedList<File> directories){
        builder = new JavaProjectBuilder();
        for (File file: directories){
            builder.addSourceTree(file);
        }
    }
    private ArrayList<JavaMethod> getMethods(){
        ArrayList<JavaMethod> array = new ArrayList<>();
        for (JavaClass javaClass: builder.getClasses()){
            array.addAll(javaClass.getMethods());
        }
        return array;
    }
    public ArrayList<GraphEdge> getDependencies(){
        HashMap<String, HashMap<String, Integer>> map = new HashMap<>();
        ArrayList<JavaMethod> methods = getMethods();
        ArrayList<String> methodNames= new ArrayList<>();
        for(JavaMethod method: methods){
            methodNames.add(method.getCallSignature());
        }
        //System.err.println(methods.get(0).getDeclarationSignature(true));
        //System.err.println(methods.get(0).getDeclarationSignature(false));
        //System.err.println(methods.get(0).getCallSignature());
        //System.err.println(methods.get(0).getTags());
       // System.err.println(methods.get(0).getDeclaringClass());
        for (JavaMethod method: methods){
            map.put(method.getCallSignature().replaceFirst("\\(.*\\)","()"), new HashMap<>());
            for (String s: methodNames){
                Integer value = 0;
                String matchedString = s.replaceFirst("\\(.*\\)","\\\\\\(.*\\\\\\)");
                Pattern pattern = Pattern.compile(matchedString);
                Matcher matcher = pattern.matcher(method.getSourceCode());
                while (matcher.find()){
                    value++;
                }
                String toPut = s.replaceFirst("\\(.*\\)","()");
                map.get(method.getCallSignature().replaceFirst("\\(.*\\)","()")).put(toPut, value);
            }
        }
        ArrayList<GraphEdge> arr = new ArrayList<>();
        for (String source: map.keySet()){
            GraphNode gSource= new GraphNode(source);
            for (String target: map.get(source).keySet()){
                GraphNode gTarget = new GraphNode(target);
                arr.add(new GraphEdge(gSource, gTarget, map.get(source).get(target)));
            }
        }
        return arr;
    }
}
