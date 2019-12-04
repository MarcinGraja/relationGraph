package Parser;

import Graph.GraphEdge;
import Graph.GraphNode;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class PackageFinder
{
    public static int PackageDep(File source, File target)
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
        return counter;
    }
    public static ArrayList<GraphEdge> ClassDependency(File[] dirs)
    {
        ArrayList<GraphEdge> tmp=new ArrayList<>();

        for(File a:dirs)
        {
            for(File b:dirs)
            {
                if(a!=b)
                {   int counter=0;
                    LinkedList<File> pliki1=new LinkedList<>();
                    for(File c:a.listFiles())
                    {
                        pliki1.add(c);
                    }
                    LinkedList<File> pliki2=new LinkedList<>();
                    for(File c:b.listFiles())
                    {
                        pliki2.add(c);
                    }
                    for(File c:pliki1)
                    {
                        for(File d:pliki2)
                        {
                        counter+=PackageDep(c,d);
                        }
                    }
                    tmp.add(new GraphEdge(new GraphNode(a.getName()),new GraphNode(b.getName()),counter));
                }
            }
        }
    return tmp;
    }
}
