package de.jpp.io;

import de.jpp.model.TwoDimGraph;
import de.jpp.model.XYNode;
import de.jpp.model.interfaces.Edge;
import org.jdom2.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TwoDimGraphGxlWriter extends GxlWriterTemplate<XYNode, Double, TwoDimGraph, String> {
    private int id = 1;
    private Map<XYNode,Integer> nodesId = new HashMap<>();

    public TwoDimGraphGxlWriter() {
    }


    @Override
    public Element writeNode(XYNode node) {
        Element result = new Element("node");
        result.setAttribute("id",Integer.toString(id));
        nodesId.put(node,id);
        id++;
        //set x
        Element x = new Element("attr");
        x.setAttribute("name", "x");
        result.addContent(x);
//        result.getChildren().add(x);
        Element xValue = new Element("float");
        xValue.setText(Double.toString(node.getX()));
        x.addContent(xValue);
//        x.getChildren().add(xValue);

        //set Y
        Element y = new Element("attr");
        y.setAttribute("name", "y");
        result.addContent(y);
//        result.getChildren().add(y);
        Element yValue = new Element("float");
        yValue.setText(Double.toString(node.getY()));
        y.addContent(yValue);
//        y.getChildren().add(yValue);

        //set description
        Element description = new Element("attr");
        description.setAttribute("name", "description");
        result.addContent(description);
//        result.getChildren().add(description);
        Element descriptionValue = new Element("string");
        descriptionValue.setText(node.getLabel());
        description.addContent(descriptionValue);
//        description.getChildren().add(descriptionValue);

        return result;
    }

    @Override
    public Element writeEdge(Edge<XYNode, Double> edge) {
        Element result = new Element("edge");
        result.setAttribute("id",Integer.toString(id++));
        result.setAttribute("from",calculateId(edge.getStart()));
        result.setAttribute("to",calculateId(edge.getDestination()));

        Element cost = new Element("attr");
        cost.setAttribute("name","cost");
        result.addContent(cost);
//        result.getChildren().add(cost);
        Element costValue = new Element("float");
        costValue.setText(Double.toString(edge.getAnnotation().get()));
        cost.addContent(costValue);
//        cost.getChildren().add(costValue);

        return result;
    }

    @Override
    public String calculateId(XYNode node) {
        return Integer.toString(nodesId.get(node));
    }

    @Override
    public String claculateId(Edge<XYNode, Double> edge) {
        return null;
    }


}
