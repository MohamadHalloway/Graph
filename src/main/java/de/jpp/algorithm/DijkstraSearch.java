package de.jpp.algorithm;


import de.jpp.algorithm.strategy.StartToDestStrategy;
import de.jpp.model.GraphImpl;
import de.jpp.model.TwoDimGraph;
import de.jpp.model.XYNode;
import de.jpp.model.interfaces.Edge;
import de.jpp.model.interfaces.WeightedGraph;

import java.util.*;

public class DijkstraSearch<N,G extends WeightedGraph<N,Double>> extends BreadthFirstSearchTemplate<N,Double,G> {
    public DijkstraSearch(G graph, N start) {
        super(graph, start);
    }

    @Override
    protected void realx(SearchResultImpl<N, Double> result, Edge<N,Double> edge) {
        N v = edge.getDestination();
        N u = edge.getStart();
        double gewicht = result.getInformation(u).getDistance() + edge.getAnnotation().get();
        if (result.getInformation(v).getDistance() > gewicht){
            result.setOpen(v);
            result.setInformation(v,new NodeInformation<>(edge,gewicht));
        }
    }

    @Override
    protected N extractNode(SearchResultImpl<N, Double> result, LinkedList<N> queue) {
        if (queue.isEmpty()){
            throw new IllegalArgumentException("queue is empty");
        }
        N res = null;
        double min = Double.MAX_VALUE;
        for (N node: queue) {
            double temp = result.getInformation(node).getDistance();
            if (temp < min){
                min = temp;
                res = node;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TwoDimGraph g = new TwoDimGraph(new GraphImpl<>(new ArrayList<>(),new HashMap<>()));
        XYNode n0 = new XYNode("0", 0, 0);
// XYNode n2 = new XYNode("2", 1, 0);
        XYNode n1 = new XYNode("1", 2, 0);
        XYNode n3 = new XYNode("3", 5, 0);
        XYNode n5 = new XYNode("5", 3, 1);
        XYNode n2 = new XYNode("2", 4, 0);
        XYNode n4 = new XYNode("4", 1, 1);
        g.addNode(n1);
        g.addNode(n5);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);
//g.addNode(n6);

        g.addEuclidianEdge(n0, n1);
        g.addEuclidianEdge(n1, n0);
        g.addEuclidianEdge(n0, n4);
        g.addEuclidianEdge(n4, n0);
        g.addEuclidianEdge(n4, n1);
        g.addEuclidianEdge(n1, n4);
        g.addEuclidianEdge(n1, n2);
        g.addEuclidianEdge(n2, n1);
        g.addEuclidianEdge(n1, n5);
        g.addEuclidianEdge(n5, n1);
        g.addEuclidianEdge(n5, n2);
        g.addEuclidianEdge(n2, n5);
        g.addEuclidianEdge(n2, n3);
        g.addEuclidianEdge(n3, n2);

        DijkstraSearch<XYNode,TwoDimGraph> dijkstraSearch = new DijkstraSearch<>(g,n0);
        List<Edge<XYNode,Double>> list = dijkstraSearch.findAllPaths().getPathTo(n4).get();
        System.out.println(list);
        list.forEach(c-> System.out.println(c.getAnnotation().get()));

    }

}
