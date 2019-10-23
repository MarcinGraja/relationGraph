package Parser;

import Graph.Dependency;
import Graph.NodeFile;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class ClassFinder {

    public static LinkedList<String> classList(File file) throws IOException {
        JavaProjectBuilder builder=new JavaProjectBuilder();
        JavaSource src=builder.addSource(file);
        LinkedList<String> tmp=new LinkedList<>();
        for(int i=0;i<src.getClasses().size();i++)
        {
            tmp.add(src.getClasses().get(i).getName());
        }
        return tmp;

    }
    public static Dependency ClassDependency(File source, File target)
    {
        int counter=0;
        JavaProjectBuilder builder=new JavaProjectBuilder();
        JavaSource src= null;
        try {
            src = builder.addSource(source);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LinkedList<String> tmp=new LinkedList<>();
        for(int i=0;i<src.getClasses().size();i++)
        {
            tmp.add(src.getClasses().get(i).getName());
        }
        try {
            Scanner scanner=new Scanner(target);
            while(scanner.hasNext())
            {
                for(String a:tmp)
                {
                    if(scanner.nextLine().contains(a))
                        counter++;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    return new Dependency(new NodeFile(source.getName()),new NodeFile(target.getName()),counter);
    }
}
