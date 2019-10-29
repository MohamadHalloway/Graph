package de.jpp.io;

import de.jpp.io.interfaces.GraphWriter;
import de.jpp.model.interfaces.Edge;
import de.jpp.model.interfaces.Graph;
import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import java.util.Collection;


public abstract class GxlWriterTemplate<N, A, G extends Graph<N, A>, String> implements GraphWriter<N, A, G, java.lang.String> {
    @Override
    public java.lang.String write(Graph graph) {
        Document document = new Document();
        //adding gxl-Tag
        Element root = new Element("gxl");
        document.setRootElement(root);
        //adding graph-Tag
        Element g = new Element("graph");
        root.getChildren().add(g);
        //adding the nodes
        for (N node: (Collection<N>)graph.getNodes()) {
            Element knoten = writeNode(node);
            g.addContent(knoten);
//            g.getChildren().add(knoten);
        }
        for (Edge edge: (Collection<Edge>)graph.getEdges()){
            Element kante = writeEdge(edge);
            g.addContent(kante);
        }

        return new XMLOutputter().outputString(document);
    }

    public abstract Element writeNode(N node);

    public abstract Element writeEdge(Edge<N,A> edge);

    public abstract java.lang.String calculateId(N node);

    public abstract java.lang.String claculateId(Edge<N,A> edge);
}
