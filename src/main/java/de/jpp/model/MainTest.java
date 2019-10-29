package de.jpp.model;

import de.jpp.factory.GraphFactory;
import de.jpp.model.interfaces.Graph;
import de.jpp.model.interfaces.ObservableGraph;

import java.util.Optional;

public class MainTest {
    public static void main(String[] args) {
        GraphFactory graphFactory = new GraphFactory();
        TwoDimGraph twoDimGraph = graphFactory.createNewTwoDimGraph();
        XYNode a = new XYNode("a",0,0);
        XYNode b = new XYNode("b",1,1);
        twoDimGraph.addNodes(a,b);
        twoDimGraph.addEdge(a,b, Optional.of(a.euclidianDistTo(b)));
        twoDimGraph.addEdge(b,a, Optional.of(a.euclidianDistTo(b)));
        System.out.println(twoDimGraph.getEdges());
        twoDimGraph.clear();
        System.out.println("test");
    }
}
