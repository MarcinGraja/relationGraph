import Graph.GraphEdge;
import Graph.GraphNode;
import Parser.FileParser;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.ArrayList;

final class FileParserTests {
    private String ResourcesTests="Tests/resources/";
    private File [] listFile=new File[2];

    @BeforeEach
    public void setUp() {

        File dir = new File(ResourcesTests);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File fileA = new File(ResourcesTests + "A.java");
        File fileB = new File(ResourcesTests + "B.java");
        try {
            fileA.createNewFile();
            fileB.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(fileA));
            writer.write("import B;\n" +
                    "class A{\n" +
                    "    B b;\n" +
                    "}");
            writer.close();
            writer = new BufferedWriter(new FileWriter(fileB));
            writer.write("class B{int b;}");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        listFile[0]=fileA;
        listFile[1]=fileB;

    }

//    @Test
//    public void printTest_Node() {
//        ArrayList<GraphEdge> expectedOutput = new ArrayList<>();
//        expectedOutput.add(new GraphEdge(new GraphNode("A.java"),new GraphNode("Class A"),1));
//        expectedOutput.add(new GraphEdge(new GraphNode("B.java"),new GraphNode("Class B"),1));
//       // ArrayList<GraphEdge> actualOutput = FileParser.FileDep(listFile);
//        assert expectedOutput.equals(actualOutput);
//    }


    @AfterEach
    public void teardown() {
        for (File file:listFile){
            file.delete();
        }
    }


}
