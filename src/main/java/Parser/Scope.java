package Parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

@SuppressWarnings("ALL")
public class Scope {
    private LinkedList<ObjectInformation> objects = new LinkedList<>();
    private static class LookAround{
        static String generate(String string){
            return "(?<=" + string + ")|(?=" + string + ")";
        }
    }
    String[] split(String input){
        String[] tokens;
        input = input.replace("(\\/{2}.\n)" +    //match // + anyString + newline
                        "|\\/\\*.s?\\*\\/"    //match /* + anyString + */
                , "");
        String includedTokenDelimiters =
                "^(\".*\")" +  // dont match between quotation marks
                        "[;=\\-+/*\\[\\]:\\{\\}]" +   // match any of ;=\-+/*\[\]{}:
                        "|(<\\S+>)" +   // OR match <anyString>
                        "|(\\(\\S+\\))" +   // OR match (anyString)
                        "|(\\[\\S+\\])";    // OR match [anyString]
        String excludedTokenDelimiters = "\\s+"; //one or more whitespace characters
        tokens =Arrays.stream(
                input.split(excludedTokenDelimiters + "|" + LookAround.generate(includedTokenDelimiters)))
                .filter(s -> !s.equals(""))
                .toArray(String[]::new);
        return tokens;
    }
    void createScope(String input, HashSet<String> consideredClasses){
        String[] scopeString;
        input = input.replace("(\\/{2}.\n)" +    //match // + anyString + newline
                "|\\/\\*.s?\\*\\/"    //match /* + anyString + */
                , "");
        String includedTokenDelimiters =
                "^(\".*\")" +  // dont match between quotation marks
                "[;=\\-+/*\\[\\]:\\{\\}]" +   // match any of ;=\-+/*\[\]{}:
                "|(<\\S+>)" +   // OR match <anyString>
                "|(\\(\\S+\\))" +   // OR match (anyString)
                "|(\\[\\S+\\])";    // OR match [anyString]
        String excludedTokenDelimiters = "\\s+"; //one or more whitespace characters
        scopeString =Arrays.stream(
                input.split(excludedTokenDelimiters + "|" + LookAround.generate(includedTokenDelimiters)))
                        .filter(s -> !s.equals(""))
                        .toArray(String[]::new);
        for (String s : scopeString){
            System.err.println(s);
        }
        for (int i = 0; i < scopeString.length; i++){
            for (String s : consideredClasses)
                if (scopeString[i].equals(s)){
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
