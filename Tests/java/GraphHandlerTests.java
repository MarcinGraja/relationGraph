import Graph.GraphEdge;
import Graph.GraphHandler;
import Graph.GraphNode;
import org.jgrapht.ext.JGraphXAdapter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class GraphHandlerTests {
    GraphNode n1;
    GraphNode n2;
    GraphNode n3;

    GraphEdge e1;
    GraphEdge e2;
    GraphEdge e3;
    GraphEdge e4;

    ArrayList<GraphNode> nodes;
    ArrayList<GraphEdge> edges;

    JGraphXAdapter<GraphNode, GraphEdge> printableGraph;

    @BeforeEach
    public void startUp() {
        n1 = new GraphNode("1");
        n2 = new GraphNode("2");
        n3 = new GraphNode("3");

        e1 = new GraphEdge(n1, n2, 1);
        e2 = new GraphEdge(n2, n3, 2);
        e3 = new GraphEdge(n3, n1, 3);
        e4 = new GraphEdge(n3, n1, 4);

        nodes.addAll(Arrays.asList(n1, n2, n3));
        edges.addAll(Arrays.asList(e1, e2, e3, e4));
    }

    @AfterEach
    public void teardown() {
        n1 = null;
        n2 = null;
        n3 = null;

        e1 = null;
        e2 = null;
        e3 = null;
        e4 = null;

        nodes = null;
        edges = null;
    }
}
