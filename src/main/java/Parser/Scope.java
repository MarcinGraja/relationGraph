package Parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Scope {
    private LinkedList<ObjectInformation> objects = new LinkedList<>();
    void createScope(String input, HashSet<String> consideredClasses){
        String[] scopeString;
        scopeString =Arrays.stream(
                input.split("[ ;<>=\\-+/*\\[\\]:]"))
                .filter(s -> !s.equals(""))
                .toArray(String[]::new);
        for (int i = 0; i < scopeString.length; i++){
            for (String s : consideredClasses)
                if (scopeString[i].contains(s)){
                    objects.addLast(new ObjectInformation(scopeString[i], scopeString[i+1]));
                }
        }
        for (String s : scopeString) {
            for (ObjectInformation object : objects) {
                //System.out.println(object);
                if (s.matches(object.getObjectName() + "(\\..)?")) {
                    object.incrementUses();
                }
            }
        }
    }
    HashMap<String,Integer> funkcja()
    {
        HashMap<String,Integer> temp=new HashMap<>();
        for(ObjectInformation object:objects) {
            if(temp.containsKey(object.getClassName())) {
                temp.put(object.getClassName(),object.getUses()+temp.get(object.getClassName()));
            }
            else {
                temp.put(object.getClassName(),object.getUses());
            }
            System.err.println(object.getClassName() + " " + temp.get(object.getClassName()) + '\n');
        }
        return temp;
    }
}
