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
     public static ArrayList<GraphEdge> ClassDependency(LinkedList<File> files)
    {
        ArrayList<GraphEdge> tmp=new ArrayList<>();

        for(File a:files)
        {
            for(File b:files)
            {
                JavaProjectBuilder builder=new JavaProjectBuilder();
                JavaSource src= null;
                try {
                    src = builder.addSource(a);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JavaProjectBuilder builder2=new JavaProjectBuilder();
                JavaSource src2= null;
                try {
                    src2 = builder.addSource(b);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(!src.getPackageName().equals(src2.getPackageName()))
                {
                    int counter=PackageDep(a,b);
                    //System.out.println(src.getPackageName()+" "+counter+" "+src2.getPackageName());
                    boolean s=true;
                    for(GraphEdge c:tmp)
                    {
                        if(c.getSource().getName().equals(src.getPackageName()) && c.getTarget().getName().equals(src2.getPackageName()))
                        {
                            int t=c.getWeight();
                            c.setWeight(counter+t);
                            s=false;
                        }

                    }
                    if(s) {
                        tmp.add(new GraphEdge(new GraphNode(src.getPackageName()), new GraphNode(src2.getPackageName()), counter));

                    }
                }
            }
        }
    return tmp;
    }
}
