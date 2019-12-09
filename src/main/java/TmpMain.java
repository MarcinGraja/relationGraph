import Graph.GraphEdge;
import Graph.GraphHandler;
import Graph.GraphNode;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class TmpMain {
    public static void main(String[] args) {
        GraphHandler methodGraph = new GraphHandler();
        GraphNode n1 = new GraphNode("f1");
        GraphNode n2 = new GraphNode("f2");
        GraphEdge edge = new GraphEdge(n1, n2, 5);
        //List<GraphNode> nodes = Arrays.asList(n1, n2);
        List<GraphEdge> edges = Collections.singletonList(edge);

        methodGraph.build(edges);
        methodGraph.makePrintable();
        try {
            methodGraph.exportToPNG("test.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}