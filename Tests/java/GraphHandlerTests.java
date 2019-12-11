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


    @AfterEach
    public void teardown() {
        graphNode = null;

        outContent = null;
        errContent = null;

        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}
