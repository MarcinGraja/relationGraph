package Graph;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphHandler {
    //Result Graph is directed and weighted for good data representation
    private Graph<GraphNode, GraphEdge> resultGraph = new SimpleDirectedWeightedGraph<>(GraphEdge.class);
    //Formatted version of graph that can be put in buffered image
    private JGraphXAdapter<GraphNode, GraphEdge> printableGraph;

    //This method builds graph from lists of edges and nodes and adds weights
    public void build(List<GraphEdge> edgeList) {
        for (GraphEdge edge : edgeList) {
            if (!resultGraph.containsVertex(edge.getSource())) {
                resultGraph.addVertex(edge.getSource());
            }
            if (!resultGraph.containsVertex(edge.getTarget())) {
                resultGraph.addVertex(edge.getTarget());
            }
            if (edge.getWeight() > 0){
                if (!edge.getSource().equals(edge.getTarget()))
                    resultGraph.addEdge(edge.getSource(), edge.getTarget(), edge);
            }
        }
    }

    //This method formats graph so it can be put into buffered image
    public void makePrintable() {
        this.printableGraph = new JGraphXAdapter<>(this.resultGraph);
        mxIGraphLayout layout = new mxHierarchicalLayout(this.printableGraph);
        layout.execute(this.printableGraph.getDefaultParent());
    }

    public void exportToPNG(String fileName) throws IOException {
        BufferedImage image = mxCellRenderer.createBufferedImage(printableGraph, null, 2, Color.WHITE, true, null);
        File dir = new File("src/main/resources/");
        if (!dir.exists()){
            dir.mkdirs();
        }
        File imageFile = new File("src/main/resources/"+fileName+".png");
        imageFile.createNewFile();
        ImageIO.write(image, "PNG", imageFile);
        assertTrue(imageFile.exists());
    }

    public Set<GraphEdge> getEdges() {
        return resultGraph.edgeSet();
    }

    public Set<GraphNode> getNodes() {
        return resultGraph.vertexSet();
    }

    public Graph<GraphNode, GraphEdge> getResultGraph() {
        return resultGraph;
    }
}
