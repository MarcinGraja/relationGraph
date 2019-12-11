package Graph;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphHandler {
    final String RESOURCES_PATH = "src/main/resources/";
    ArrayList<GraphNode> nodes = new ArrayList<>();
    ArrayList<GraphEdge> edges = new ArrayList<>();
    //Result Graph is directed and weighted for good data representation
    private Graph<GraphNode, GraphEdge> resultGraph = new SimpleDirectedWeightedGraph<>(GraphEdge.class);
    //Formatted version of graph that can be put in buffered image
    private JGraphXAdapter<GraphNode, GraphEdge> printableGraph;

    //This method builds graph from lists of edges and nodes and adds weights
    public void build(List<GraphEdge> edgeList) {
        for (GraphEdge edge : edgeList) {
            if (!resultGraph.containsVertex(edge.getSource())) {
                resultGraph.addVertex(edge.getSource());
                nodes.add(edge.getSource());
            }
            if (!resultGraph.containsVertex(edge.getTarget())) {
                resultGraph.addVertex(edge.getTarget());
                nodes.add(edge.getTarget());
            }
            if (edge.getWeight() > 0) {
                if (!edge.getSource().equals(edge.getTarget())) {
                    resultGraph.addEdge(edge.getSource(), edge.getTarget(), edge);
                    edges.add(edge);
                }
            }
        }
    }

    //This method formats graph so it can be put into buffered image
    public void makePrintable() {
        this.printableGraph = new JGraphXAdapter<>(this.resultGraph);
        mxIGraphLayout layout = new mxHierarchicalLayout(this.printableGraph);
        layout.execute(this.printableGraph.getDefaultParent());
    }

    public void exportToXML(String fileName) {

        try {
            //Document
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //<Project>
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Project");
            doc.appendChild(rootElement);

            //Project attributes
            Attr dov_type_attr = doc.createAttribute("DocumentationType");
            dov_type_attr.setValue("html");
            rootElement.setAttributeNode(dov_type_attr);

            Attr exp_attr = doc.createAttribute("ExporterVersion");
            exp_attr.setValue("12.2");
            rootElement.setAttributeNode(exp_attr);

            Attr name_attr = doc.createAttribute("Name");
            name_attr.setValue("us5");
            rootElement.setAttributeNode(name_attr);

            Attr uml_v_attr = doc.createAttribute("UmlVersion");
            uml_v_attr.setValue("2.x");
            rootElement.setAttributeNode(uml_v_attr);

            Attr structure_attr = doc.createAttribute("Xml_structure");
            structure_attr.setValue("simple");
            rootElement.setAttributeNode(structure_attr);

            //Models
            Element models = doc.createElement("Models");
            rootElement.appendChild(models);

            //Nodes
            int i = 0;
            for (GraphNode node : nodes) {
                Element element = doc.createElement("Class");

                Attr attr_name = doc.createAttribute("Name");
                attr_name.setValue(node.getName());
                element.setAttributeNode(attr_name);

                Attr attr_Id = doc.createAttribute("Id");
                attr_Id.setValue(Integer.toString(i++));
                element.setAttributeNode(attr_Id);

                models.appendChild(element);
            }

            //Edges
            int j = 5000;
            for (GraphEdge edge : edges) {
                Element element = doc.createElement("Usage");

                Attr attr_Id = doc.createAttribute("Id");
                attr_Id.setValue(Integer.toString(j++));
                element.setAttributeNode(attr_Id);

                Attr attr_To = doc.createAttribute("To");
                attr_To.setValue(edge.getTarget().getName());
                element.setAttributeNode(attr_To);

                Attr attr_From = doc.createAttribute("From");
                attr_From.setValue(edge.getSource().getName());
                element.setAttributeNode(attr_From);

                models.appendChild(element);
            }

            //Write to xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(RESOURCES_PATH + fileName + ".xml"));
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
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
//
//    public void serializeResultGraph() {
//
//        FileOutputStream fout = null;
//        ObjectOutputStream oos = null;
//
//        try {
//
//            fout = new FileOutputStream("Tests/resources/buildedTestGraph.xml");
//            oos = new ObjectOutputStream(fout);
//            oos.writeObject(resultGraph);
//
//            System.out.println("Done");
//
//        } catch (Exception ex) {
//
//            ex.printStackTrace();
//
//        } finally {
//
//            if (fout != null) {
//                try {
//                    fout.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            if (oos != null) {
//                try {
//                    oos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//    }
}
