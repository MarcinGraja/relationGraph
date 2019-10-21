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
    private List<Dependency> edges; //Dependencys are graph's edges
    private List<NodeFile> nodes;   //Source Files are graph's nodes
                                    //Result Graph is directed and weighted for good data representation
    private Graph<NodeFile, Dependency> resultGraph = new SimpleDirectedWeightedGraph<>(Dependency.class);
                                    //Formatted version of graph that can be put in buffered image
    private JGraphXAdapter<NodeFile, Dependency> printableGraph;
    private ImageView imageView;

    //This method builds graph from lists of edges and nodes and adds weights
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

    //This method formats graph so it can be put into buffered image
    public void makePrintable(){
        this.printableGraph=new JGraphXAdapter<>(this.resultGraph);
        mxIGraphLayout layout = new mxHierarchicalLayout(this.printableGraph);
        layout.execute(this.printableGraph.getDefaultParent());

        //Żeby wyświetlić:
        //        BufferedImage image = mxCellRenderer.createBufferedImage(printableGraph, null, 1, Color.WHITE, true, null);
        //      imageView.setImage(SwingFXUtils.toFXImage(image, null));
    }

    public void givenAdaptedGraph_whenWriteBufferedImage_thenFileShouldExist() throws IOException{
        BufferedImage image=mxCellRenderer.createBufferedImage(printableGraph,null,2, Color.WHITE,true,null);
        File imageFile=new File("src/test/resources/graph.png");
        ImageIO.write(image,"PNG",imageFile);

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
