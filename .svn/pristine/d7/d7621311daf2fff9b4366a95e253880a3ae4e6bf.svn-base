package de.jpp.io;

import de.jpp.factory.GraphFactory;
import de.jpp.model.LabelMapGraph;
import org.jdom2.Element;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LabelMapGraphGxlReader extends GxlReaderTemplate<String, Map<String, String>, LabelMapGraph, String> {
    @Override
    public LabelMapGraph createGraph() {
        GraphFactory graphFactory = new GraphFactory();
        return graphFactory.createNewLabelMapGraph();
    }

    @Override
    public String readNodeId(String node, Element element) {
        return element.getAttributeValue("id");
    }

    @Override
    public String readNode(Element element) {
        String description = "";
        boolean found = false;
        for (Element child : element.getChildren()){
            String temp = child.getAttributeValue("name");
            if (temp.equals("description")){
                found = true;
                description = child.getChildText("string");
            }
            if (!found){
                throw new IllegalArgumentException("description not found");
            }
        }
        return description;
    }

    @Override
    public Optional<Map<String, String>> readAnnotation(Element element) {
        boolean found = false;
        Map<String,String > map = new HashMap<>();
        Optional<Map<String ,String >> result = Optional.empty();
        for (Element attr: element.getChildren("attr")){
            found =true;
            map.put(attr.getAttributeValue("name"),attr.getChildren().get(0).getText());
        }
        if (found){
            result = Optional.of(map);
        }
        return result;
    }

    @Override
    public void addEdge(LabelMapGraph graph, Element element, Map<String, String> map){
        try {
            String start = map.get(element.getAttributeValue("from"));
            String  dest = map.get(element.getAttributeValue("to"));
            Optional<Map<String ,String>> annotation = readAnnotation(element);
            graph.addEdge(start,dest,annotation);

        }catch (Exception e){
            throw new IllegalArgumentException("GXL-File is invalid");
        }
    }

    public static void main(String[] args) {
        try {
            //read
            File file = new File("TestFiles/labelmap gxl/valid/sample 1.gxl");
            InputStream inputStream = new FileInputStream(file);
            String input = new String(inputStream.readAllBytes());
            LabelMapGraphGxlReader labelMapGraphGxlReader = new LabelMapGraphGxlReader();
            LabelMapGraph labelMapGraph = labelMapGraphGxlReader.read(input);
            System.out.println("read1: " + true);

            //write
            FileOutputStream f = new FileOutputStream("TestFiles/testLabelMapGraphGxlWriter.txt");
            LabelMapGraphGxlWriter labelMapGraphGxlWriter = new LabelMapGraphGxlWriter();
            f.write(labelMapGraphGxlWriter.write(labelMapGraph).getBytes());
            System.out.println("write1: " + true);

            //read2
            File file2 = new File("TestFiles/testLabelMapGraphGxlWriter.txt");
            InputStream inputStream2 = new FileInputStream(file2);
            String input2 = new String(inputStream2.readAllBytes());
            LabelMapGraph labelMapGraph2 = labelMapGraphGxlReader.read(input2);
            System.out.println("read2: " + true);

            //write2
            FileOutputStream f2 = new FileOutputStream("TestFiles/testLabelMapGraphGxlWriter2.txt");
            f2.write(labelMapGraphGxlWriter.write(labelMapGraph2).getBytes());
            System.out.println("write1: " + true);

            System.out.println("Equal: " + labelMapGraph.equals(labelMapGraph2));


        }catch (Exception e){
            throw new IllegalArgumentException("راحت علينا!!");
        }
    }

}
