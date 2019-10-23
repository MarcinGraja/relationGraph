package Parser;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodCallFinder {
    private JavaProjectBuilder builder;
    public void setBuilder(File[] directories){
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
    public HashMap<String, HashMap<String, Integer>> getDependencies(){
        HashMap<String, HashMap<String, Integer>> map = new HashMap<>();
        ArrayList<JavaMethod> methods = getMethods();
        System.err.println(methods.get(0).getCallSignature());
        System.err.println(methods.get(1).getSourceCode());
        ArrayList<String> methodNames= new ArrayList<>();
        for(JavaMethod method: methods){
            methodNames.add(method.getCallSignature());
        }
        for (JavaMethod method: methods){
            map.put(method.getCallSignature(), new HashMap<>());
            for (String s: methodNames){
                Integer value = 0;
                String matchedString = s.replaceFirst("\\(.*\\)","\\\\\\(.*\\\\\\)");
                Pattern pattern = Pattern.compile(matchedString);
                Matcher matcher = pattern.matcher(method.getSourceCode());
                while (matcher.find()){
                    value++;
                }
                map.get(method.getCallSignature()).put(s, value);
            }
        }
        return map;
    }
}
