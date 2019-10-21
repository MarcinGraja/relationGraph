package Graph;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxIGraphLayout;
import javafx.scene.image.ImageView;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.mxgraph.util.mxCellRenderer;

public class GraphHandler {
    private List<GraphEdge> edges;   //Connections are graph's edges
    private List<GraphNode> nodes;
    //Result Graph is directed and weighted for good data representation
    private Graph<GraphNode, GraphEdge> resultGraph = new SimpleDirectedWeightedGraph<>(GraphEdge.class);
    //Formatted version of graph that can be put in buffered image
    private JGraphXAdapter<GraphNode, GraphEdge> printableGraph;

    //This method builds graph from lists of edges and nodes and adds weights
    public void build(List<GraphEdge> edgeList, List<GraphNode> nodeList) {
        this.edges = edgeList;
        this.nodes = nodeList;
        for (GraphNode node : nodes) {
            resultGraph.addVertex(node);
        }
        int nodeCounter = 0;
        for (GraphEdge edge : edges) {
            resultGraph.addEdge(nodes.get(nodeCounter), nodes.get(nodeCounter + 1), edge);
            resultGraph.setEdgeWeight(edge, edge.getWeight());
        }
    }

    //This method formats graph so it can be put into buffered image
    public void makePrintable() {
        this.printableGraph = new JGraphXAdapter<>(this.resultGraph);
        mxIGraphLayout layout = new mxHierarchicalLayout(this.printableGraph);
        layout.execute(this.printableGraph.getDefaultParent());

    }

    public void exportToPNG() throws IOException {
        BufferedImage image = mxCellRenderer.createBufferedImage(printableGraph, null, 2, Color.WHITE, true, null);
        File imageFile = new File("src/main/resources/graph.png");
        ImageIO.write(image, "PNG", imageFile);
    }

    public List<GraphEdge> getEdges() {
        return edges;
    }

    public List<GraphNode> getNodes() {
        return nodes;
    }

    public Graph<GraphNode, GraphEdge> getResultGraph() {
        return resultGraph;
    }
}
