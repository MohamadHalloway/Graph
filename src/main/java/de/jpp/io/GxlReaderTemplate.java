package de.jpp.io;

import de.jpp.io.interfaces.GraphReader;
import de.jpp.io.interfaces.ParseException;
import de.jpp.model.interfaces.Graph;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public abstract class GxlReaderTemplate<N, A, G extends Graph<N, A>, S> implements GraphReader<N, A, G, S> {

    public GxlReaderTemplate() {
    }

    @Override
    public G read(S input) throws ParseException {
        SAXBuilder builder = new SAXBuilder();
        try {
            Document document = builder.build(new StringReader((java.lang.String) input));

            // if the GXL-tag is invalid, an exception will be thrown
            Element rootElement = document.getRootElement();

            //check if the Graph-Tag exists
            Element graph = rootElement.getChild("graph");
            if (graph == null){
                throw new ParseException("GXL-file is invalid(graph-Tag)");
            }
            G res = createGraph();

            //read nodes and edges
            Map<java.lang.String,N> nodes = new HashMap<>();
            for (Element child: graph.getChildren()){
                if (child.getName().equals("node")){
                    N temp = readNode(child);
                    nodes.put(readNodeId(null,child),temp);
                    res.addNode(temp);
                }
            }
            for (Element child: graph.getChildren("edge")){
                    addEdge(res,child,nodes);
            }
            return res;
        } catch (Exception e) {
            throw new ParseException("GXL-file is invalid");
        }


    }

    public void addEdge(G graph, Element element, Map<java.lang.String, N> map) {
        try {
            N start = map.get(element.getAttributeValue("from"));
            N dest = map.get(element.getAttributeValue("to"));
            Optional<A> annotation = Optional.empty();
            for (Element attr: element.getChildren("attr")){
                if (attr.getAttributeValue("name").equals("cost")){
                    annotation = readAnnotation(attr);
                }
            }
            graph.addEdge(start,dest,annotation);

        }catch (Exception e){
            throw new IllegalArgumentException("GXL-File is invalid");
        }
    }

    public abstract G createGraph();

    public abstract java.lang.String readNodeId(N node, Element element);

    public abstract N readNode(Element element);

    public abstract Optional<A> readAnnotation(Element element);

}
