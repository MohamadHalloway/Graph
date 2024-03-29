package de.jpp.algorithm.interfaces;

import de.jpp.algorithm.interfaces.SearchAlgorithm;
import de.jpp.algorithm.interfaces.SearchResult;
import de.jpp.algorithm.strategy.NToZeroStrategy;
import de.jpp.algorithm.strategy.StartToDestStrategy;
import de.jpp.factory.GraphFactory;
import de.jpp.factory.SearchFactory;
import de.jpp.model.GraphImpl;
import de.jpp.model.TwoDimGraph;
import de.jpp.model.XYNode;
import de.jpp.model.interfaces.Graph;

import java.util.Optional;

public class TestDFS<N,A,G extends Graph<N,A>> {
    static XYNode k1 = new XYNode("k1",0,0);
    static XYNode k2 = new XYNode("k2",1,0);
    static XYNode k3 = new XYNode("k3",2,-1);
    static XYNode k4 = new XYNode("k4",2,1);
    static XYNode k5 = new XYNode("k5",3,0);
    static XYNode k6 = new XYNode("k6",4,0);
    static GraphFactory graphFactory = new GraphFactory();
    static TwoDimGraph twoDimGraph = graphFactory.createNewTwoDimGraph();

    public static void main(String[] args) {


        twoDimGraph.addNodes(k1,k2,k3,k4,k5,k6);
        twoDimGraph.addEdge(k1,k2, Optional.of(1.0));
        twoDimGraph.addEdge(k2,k3, Optional.of(2.0));
        twoDimGraph.addEdge(k2,k4, Optional.of(1.0));
        twoDimGraph.addEdge(k3,k5, Optional.of(1.0));
        twoDimGraph.addEdge(k4,k5, Optional.of(1.0));
        twoDimGraph.addEdge(k5,k6, Optional.of(1.0));
        testBFS(twoDimGraph,k1);

    }
    private static void testDepthFirstSearch(TwoDimGraph graph, XYNode start){
        SearchAlgorithm<XYNode,Double,TwoDimGraph> tiefensuche = new SearchFactory<XYNode,Double>().getDepthFirstSearch(graph,start);
        SearchResult<XYNode,Double> ergebnis = tiefensuche.findPaths(new StartToDestStrategy<>(k6));
        System.out.println(ergebnis.getPathTo(k3));
        System.out.println(ergebnis.getAllOpenNodes());
        System.out.println(ergebnis.getAllKnownNodes());
//        SearchResult<XYNode,Double> ergebnis2 = tiefensuche.findAllPaths();
//        System.out.println(ergebnis2.getPathTo(k5));
//        SearchResult<XYNode,Double> ergebnis3 = tiefensuche.findAllPaths();
//        System.out.println(ergebnis3.getPathTo(k5));
//        System.out.println(ergebnis3.getPathTo(k3));
//        System.out.println(ergebnis2.getPathTo(k1));

    }

    private static void testBFS(TwoDimGraph graph, XYNode start){
        SearchAlgorithm<XYNode,Double,TwoDimGraph> bfs = new SearchFactory<XYNode,Double>().getBreadthFirstSearch(graph,start);
        SearchResult<XYNode,Double> ergebnis = bfs.findPaths(new StartToDestStrategy<>(k1));
        System.out.println(ergebnis.getPathTo(k1));
        System.out.println(ergebnis.getAllKnownNodes());
        System.out.println(ergebnis.getAllOpenNodes());
    }

    private static void testDijkstra(TwoDimGraph graph, XYNode start){
        SearchAlgorithm<XYNode,Double,TwoDimGraph> dij = new SearchFactory<XYNode,Double>().getDijkstra(graph,start);
        SearchResult<XYNode,Double> ergebnis = dij.findPaths(new StartToDestStrategy<>(k2));
        System.out.println(ergebnis.getPathTo(k2));
        System.out.println(ergebnis.getAllKnownNodes());
        System.out.println(ergebnis.getAllOpenNodes());
    }
    private static void testAStar(TwoDimGraph graph, XYNode start, XYNode dest, EstimationFunction<XYNode> function){
        SearchAlgorithm<XYNode,Double,TwoDimGraph> aStar = new SearchFactory<XYNode,Double>().getAStar(graph,start,dest,function);
        SearchResult<XYNode,Double> ergebnis = aStar.findPaths(new StartToDestStrategy<>(k1));
        System.out.println(ergebnis.getPathTo(k1));
        System.out.println(ergebnis.getAllKnownNodes());
        System.out.println(ergebnis.getAllOpenNodes());
    }
}

