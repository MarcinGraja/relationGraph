import Graph.GraphEdge;
import Graph.GraphHandler;
import Graph.GraphNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TmpMain {
    public static void main(String[] args) {
        GraphNode n1;
        GraphNode n2;
        GraphNode n3;

        GraphEdge e1;
        GraphEdge e2;
        GraphEdge e3;
        GraphEdge e4;

        ArrayList<GraphNode> nodes = new ArrayList<>();
        ArrayList<GraphEdge> edges = new ArrayList<>();

        n1 = new GraphNode("1");
        n2 = new GraphNode("2");
        n3 = new GraphNode("3");

        e1 = new GraphEdge(n1, n2, 1);
        e2 = new GraphEdge(n2, n3, 2);
        e3 = new GraphEdge(n3, n1, 3);
        e4 = new GraphEdge(n3, n1, 4);

        nodes.addAll(Arrays.asList(n1, n2, n3));
        edges.addAll(Arrays.asList(e1, e2, e3, e4));

        GraphHandler graphHandler=new GraphHandler();
        graphHandler.build(edges);
        //graphHandler.serializeResultGraph();
    }
}