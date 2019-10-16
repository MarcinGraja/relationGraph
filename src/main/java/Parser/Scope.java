package Parser;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

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
        for (String token : scopeString){
            if (consideredClasses.contains(token)){
                objects.addLast(new ObjectInformation());
            }
        }
        return position;
    }
    public Scope(){
    }
}
