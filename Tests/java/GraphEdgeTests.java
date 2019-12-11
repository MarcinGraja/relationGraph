import Graph.GraphEdge;
import Graph.GraphNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

final class GraphEdgeTests {
    GraphNode source;
    GraphNode target;
    GraphEdge graphEdge;

    private ByteArrayOutputStream outContent;
    private ByteArrayOutputStream errContent;

    private PrintStream originalOut = System.out;
    private PrintStream originalErr = System.err;

    @BeforeEach
    public void setUp() {
        source = new GraphNode("1");
        target = new GraphNode("2");
        graphEdge = new GraphEdge(source, target, 2);

        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void print_graphEdge() {
        graphEdge.print();
        assert outContent.toString().equals("1 --2-> 2\r\n") : "System.out.printl doesnt work. lol...";
    }

    @Test
    public void toString_Test_Node() {
        String expected = "2";

        String actual = graphEdge.toString();

        assert expected.equals(actual) : "GraphNode -> toString() doesn't work";
    }

    @Test
    public void getSource_return1() {
        GraphNode expected = source;

        GraphNode actual = graphEdge.getSource();

        assert expected == actual : "GraphEdge -> getSource() doesn't work";
    }

    @Test
    public void getTarget_return2() {
        GraphNode expected = target;

        GraphNode actual = graphEdge.getTarget();

        assert expected == actual : "GraphEdge -> getTarget() doesn't work";
    }

    @Test
    public void getWeight_return2() {
        int expected = 2;

        int actual = graphEdge.getWeight();

        assert actual == expected;
    }

    @AfterEach
    public void teardown() {
        source = null;
        target = null;
        graphEdge = null;

        outContent = null;
        errContent = null;

        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}
