package Parser;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Scope {
    LinkedList<ObjectInformation> objects;
    public int createScope(String input, int position, HashSet<String> consideredClasses){
        int difference = 1;
        int startIndex = input.indexOf("{", position+1);
        int endIndex = input.indexOf("}", position);

        while (difference != 0){
            while (startIndex < endIndex){
                startIndex = input.indexOf("{", startIndex + 1);
                difference++;
            }
            while (endIndex < startIndex && input.indexOf("}", endIndex+1) < startIndex){
                endIndex = input.indexOf("}", endIndex+1);
                difference--;
            }
        }
        String[] scopeString;
        scopeString = input.substring(position, endIndex).split(" ");
        for (int i = 0; i < scopeString.length; i++){
            if (consideredClasses.contains(scopeString[i])){
                objects.addLast(new ObjectInformation(scopeString[i], scopeString[i+1]));
            }
        }
        for (int i = 0; i < scopeString.length; i++){
            for (int j = 0; j < objects.size(); j++){
                //if (scopeString[i].matches(Pattern.compile(objects.get(j).getClassName())));
            }
        }
        return position;
    }
    public Scope(){
    }
}
