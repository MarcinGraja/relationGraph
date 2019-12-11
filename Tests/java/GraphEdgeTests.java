import Graph.GraphEdge;
import Graph.GraphNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

final class GraphEdgeTests {
    GraphNode gn1;
    GraphNode gn2;
    GraphEdge graphEdge;

    private ByteArrayOutputStream outContent;
    private ByteArrayOutputStream errContent;

    private PrintStream originalOut = System.out;
    private PrintStream originalErr = System.err;

    @BeforeEach
    public void setUp() {
        gn1 = new GraphNode("1");
        gn2 = new GraphNode("2");
        graphEdge = new GraphEdge(gn1, gn2, 2);

        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void print_graphEdge(){
        graphEdge.print();
        assert outContent.toString().equals("1 --2-> 2\r\n") : "System.out.printl doesnt work. lol...";
    }

    @Test
    public void toString_Test_Node() {
        String expected = "2";

        String actual = graphEdge.toString();

        assert expected.equals(actual) : "GraphNode -> toString() doesn't work";
    }

    @AfterEach
    public void teardown() {
        gn1 = null;
        gn2 = null;
        graphEdge = null;

        outContent = null;
        errContent = null;

        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}
