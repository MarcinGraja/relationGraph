package Parser;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class Scope {
    private LinkedList<ObjectInformation> objects = new LinkedList<>();
    public void createScope(String input, HashSet<String> consideredClasses){
        //int difference = 1;
        //int startIndex = input.indexOf("{", position+1);
        //int endIndex = input.indexOf("}", position);

        /*while (difference != 0){
            if (startIndex != -1 && startIndex < endIndex){
                startIndex = input.indexOf("{", startIndex + 1);
                difference++;
            }
            if (endIndex != -1 && endIndex < startIndex && input.indexOf("}", endIndex+1) < startIndex){
                endIndex = input.indexOf("}", endIndex + 1);
                difference--;
            }
        }*/
        //position = endIndex;
        String[] scopeString;
        scopeString = input.split(" ");
        for (int i = 0; i < scopeString.length; i++){
            if (consideredClasses.contains(scopeString[i])){
                objects.addLast(new ObjectInformation(scopeString[i], scopeString[i+1]));
            }
        }
        for (String s : scopeString) {
            for (ObjectInformation object : objects) {
                System.out.println(object);
                if (s.matches(object.getObjectName() + "(\\..)?")) {
                    object.incrementUses();
                }
            }
        }
       // return position;
    }
}
