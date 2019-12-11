import Graph.GraphNode;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

final class GraphNodeTests {
    GraphNode graphNode;

    private ByteArrayOutputStream outContent;
    private ByteArrayOutputStream errContent;
    private PrintStream originalOut = System.out;
    private PrintStream originalErr = System.err;

    @BeforeEach
    public void setUp() {
        graphNode = new GraphNode("Test Node");

        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void printTest_Node() {
        graphNode.print();
        assert outContent.toString().equals("Test Node\r\n") : "System.out.printl doesnt work.";
    }

    @Test
    public void toString_Test_Node() {
        String expected = "Test Node";

        String actual = graphNode.toString();

        assert expected.equals(actual) : "GraphNode -> toString() doesn't work";
    }

    @Test
    public void getName_Test_Node() {
        String expected = "Test Node";

        String actual = graphNode.toString();

        assert expected.equals(actual) : "GraphNode -> getName() doesn't work";
    }

    @Test
    public void equals_OequalsO_returnTrue() {
        GraphNode a = new GraphNode("1");
        GraphNode b = new GraphNode("1");

        assert a.equals(b);
    }

    @Test
    public void equals_OequalsNULL_returnFalse(){
        GraphNode a = new GraphNode("1");
        GraphNode b = null;

        assert !a.equals(b);
    }

    @Test
    public void equals_OequalsDiffClass_resturnFalse(){
        GraphNode a = new GraphNode("1");
        Object b = new Object();

        assert !a.equals(b);
    }

    @Test
    public void hashCode_TestNode_NameToHash(){
        int expected = 764541167;

        int actual = graphNode.hashCode();

        assert expected==actual : "GraphNode -> hashCode() doesn't work";
    }

    @AfterEach
    public void teardown() {
        graphNode = null;

        outContent = null;
        errContent = null;

        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}
