package Graph;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;
//import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class GraphHandler {
    private List<Dependency> edges;
    private List<NodeFile> nodes;
    private Graph<NodeFile, Dependency> resultGraph = new SimpleDirectedWeightedGraph<>(Dependency.class);
    private JGraphXAdapter<NodeFile, Dependency> printableGraph;
    private ImageView imageView;

    public void build(List<Dependency> edgeList, List<NodeFile> nodeList) {
        this.edges = edgeList;
        this.nodes = nodeList;
        for (NodeFile node : nodes) {
            resultGraph.addVertex(node);
        }
        int nodeCounter = 0;
        for (Dependency edge : edges) {
            resultGraph.addEdge(nodes.get(nodeCounter), nodes.get(nodeCounter + 1), edge);
            resultGraph.setEdgeWeight(edge, edge.getAmount());
        }
    }

    public void makePrintable(){
        this.printableGraph=new JGraphXAdapter<>(this.resultGraph);
        mxIGraphLayout layout = new mxHierarchicalLayout(this.printableGraph);
        layout.execute(this.printableGraph.getDefaultParent());

        //Żeby wyświetlić:
                BufferedImage image = mxCellRenderer.createBufferedImage(printableGraph, null, 1, Color.WHITE, true, null);
              //imageView.setImage(SwingFXUtils.toFXImage(image, null));
    }

    public List<Dependency> getEdges() {
        return edges;
    }

    public List<NodeFile> getNodes() {
        return nodes;
    }

    public Graph<NodeFile, Dependency> getResultGraph() {
        return resultGraph;
    }
}
