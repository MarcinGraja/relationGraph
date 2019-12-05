package Graph;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;
import org.jgrapht.Graph;
import org.jgrapht.ext.DOTExporter;
import org.jgrapht.ext.GraphExporter;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.util.List;
import java.util.Set;

import org.jgrapht.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphHandler {
    final String RESOURCES_PATH = "src/main/resources/";
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
            if (edge.getWeight() > 0) {
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

    public void exportToGraphML(String filename) throws ExportException, IOException {
        ComponentNameProvider<GraphNode> vertexIdProvider = new ComponentNameProvider<>() {
            @Override
            public String getName(GraphNode graphNode) {
                return graphNode.getName();
            }
        };
        ComponentNameProvider<GraphNode> vertexLabelProvider = new ComponentNameProvider<>() {
            @Override
            public String getName(GraphNode graphNode) {
                return graphNode.toString();
            }
        };
        ComponentNameProvider<GraphEdge> edgeIdProvider = new ComponentNameProvider<>() {
            @Override
            public String getName(GraphEdge graphEdge) {
                return String.valueOf(graphEdge.getWeight());
            }
        };
        ComponentNameProvider<GraphEdge> edgeLabelProvider = new ComponentNameProvider<>() {
            @Override
            public String getName(GraphEdge graphEdge) {
                return graphEdge.toString();
            }
        };

        GraphMLExporter<GraphNode, GraphEdge> exporter = new GraphMLExporter<>(vertexIdProvider, vertexLabelProvider,
                                                                            edgeIdProvider, edgeLabelProvider);

        Writer writer = new StringWriter();
        exporter.exportGraph(resultGraph, writer);
        String text = writer.toString();

        File dir = new File(RESOURCES_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        FileOutputStream outputStream = new FileOutputStream(RESOURCES_PATH + filename);
        byte[] textB = text.getBytes();
        outputStream.write(textB);
        outputStream.close();
    }

    public void exportToPNG(String fileName) throws IOException {
        BufferedImage image = mxCellRenderer.createBufferedImage(printableGraph, null, 2, Color.WHITE, true, null);
        File dir = new File(RESOURCES_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File imageFile = new File(RESOURCES_PATH + fileName);
        imageFile.createNewFile();
        ImageIO.write(image, "PNG", imageFile);
        assertTrue(imageFile.exists());
    }

    public void exportToJSON(String filename) throws org.jgrapht.io.ExportException, IOException {
        JSONExporter<GraphNode, GraphEdge> exporter = new JSONExporter<>();
        Writer writer = new StringWriter();
        exporter.exportGraph(resultGraph, writer);
        String text = writer.toString();

        File dir = new File(RESOURCES_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        FileOutputStream outputStream = new FileOutputStream(RESOURCES_PATH + filename);
        byte[] textB = text.getBytes();
        outputStream.write(textB);
        outputStream.close();
    }

    public void exportToCSV(String filename) throws org.jgrapht.io.ExportException, IOException {
        CSVExporter<GraphNode, GraphEdge> exporter = new CSVExporter<>();
        Writer writer = new StringWriter();
        exporter.exportGraph(resultGraph, writer);
        String text = writer.toString();

        File dir = new File(RESOURCES_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        FileOutputStream outputStream = new FileOutputStream(RESOURCES_PATH + filename);
        byte[] textB = text.getBytes();
        outputStream.write(textB);
        outputStream.close();
    }

    public Set<GraphEdge> getEdges() {
        return resultGraph.edgeSet();
    }

    public Set<GraphNode> getNodes() {
        return resultGraph.vertexSet();
    }//ffggfg

    public Graph<GraphNode, GraphEdge> getResultGraph() {
        return resultGraph;
    }
}
