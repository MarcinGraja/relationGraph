import Graph.GraphEdge;
import Graph.GraphHandler;
import Graph.GraphNode;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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
    Graph<GraphNode, GraphEdge> resultGraph;

    GraphHandler testHandler;

    @BeforeEach
    public void startUp() {
        n1 = new GraphNode("1");
        n2 = new GraphNode("2");
        n3 = new GraphNode("3");

        e1 = new GraphEdge(n1, n2, 1);
        e2 = new GraphEdge(n2, n3, 2);
        e3 = new GraphEdge(n3, n1, 3);
        e4 = new GraphEdge(n1, n3, 4);

        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        nodes.addAll(Arrays.asList(n1, n2, n3));
        edges.addAll(Arrays.asList(e1, e2, e3, e4));

        resultGraph = new SimpleDirectedWeightedGraph<>(GraphEdge.class);

        testHandler = new GraphHandler();
    }

    @Test
    public void buildAndMakePrintableAndExportToPNG_Test_Graph() {
        testHandler.build(edges);

        testHandler.makePrintable();

        try {
            testHandler.exportToPNG("testGraph.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("Sprawdz czy testGraph jest taki jak testGraphCheck");
        assert true;
    }

    @Test
    public void exportToXml() {
        testHandler.build(edges);

        testHandler.makePrintable();

        testHandler.exportToXML("testXMLGraph");
        File file = new File("src/main/resources/testXMLGraphCheck.xml");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // we just need to use \\Z as delimiter

        assert sc != null;
        sc.useDelimiter("\\Z");


        String expected = sc.next();

        file = new File("src/main/resources/testXMLGraph.xml");
        sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // we just need to use \\Z as delimiter

        assert sc != null;
        sc.useDelimiter("\\Z");

        String actual = sc.next();

        assert expected.equals(actual) : "Export to XML doesnt work.";

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

        resultGraph = null;

        testHandler = null;
    }
}
