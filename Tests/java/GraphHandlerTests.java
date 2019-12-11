import Graph.GraphEdge;
import Graph.GraphHandler;
import Graph.GraphNode;
import org.jgrapht.ext.JGraphXAdapter;

import java.util.ArrayList;

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
}
