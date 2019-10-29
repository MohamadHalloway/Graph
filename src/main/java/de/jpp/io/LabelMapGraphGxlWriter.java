package de.jpp.io;

import de.jpp.io.interfaces.ParseException;
import de.jpp.model.LabelMapGraph;
import de.jpp.model.interfaces.Edge;
import org.jdom2.Element;

import java.util.HashMap;
import java.util.Map;

public class LabelMapGraphGxlWriter extends GxlWriterTemplate<String, Map<String,String>, LabelMapGraph, String> {
    private int i = 1;
    private Map<String,String> nodesId = new HashMap<>();


    public LabelMapGraphGxlWriter() {
    }

    @Override
    public Element writeNode(String node) {
        Element result = new Element("node");
        result.setAttribute("id","id" + i);
        nodesId.put(node,"id" + i);
        i++;

        //set description
        Element description = new Element("attr").setAttribute("name","description");
        description.addContent(new Element("string").setText(node));
        result.addContent(description);
        return result;
    }

    @Override
    public Element writeEdge(Edge<String, Map<String, String>> edge) {
        Element result = new Element("edge");
        result.setAttribute("id","id" + i);i++;
        result.setAttribute("from",calculateId(edge.getStart()));
        result.setAttribute("to",calculateId(edge.getDestination()));

        if (!edge.getAnnotation().isPresent()){
            return result;
        }

        for (String child: edge.getAnnotation().get().keySet()){
            Element attr = new Element("attr");
            attr.setAttribute("name",child);
            Element string = new Element("string");
            string.setText(edge.getAnnotation().get().get(child)); //get value of the attribute
            attr.addContent(string);
            result.addContent(attr);
        }
        return result;
    }

    @Override
    public String calculateId(String node) {
        return nodesId.get(node);
    }

    @Override
    public String claculateId(Edge<String, Map<String, String>> edge) {
        return null;
    }

}
