package de.jpp.algorithm;

import de.jpp.algorithm.interfaces.EstimationFunction;
import de.jpp.model.interfaces.Edge;
import de.jpp.model.interfaces.WeightedGraph;

import java.util.LinkedList;

public class AStar<N,G extends WeightedGraph<N,Double>> extends BreadthFirstSearchTemplate<N,Double,G> {
    private N dest;
    private EstimationFunction<N> estimationFunction;


    public AStar(G graph, N start, N dest, EstimationFunction<N> estimationFunction) {
        super(graph, start);
        this.dest = dest;
        this.estimationFunction = estimationFunction;
    }

    @Override
    protected void realx(SearchResultImpl<N, Double> result, Edge<N, Double> edge) {
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
        double min = Double.MAX_VALUE;
        N res = null;
        for (N node:queue){
            double temp = estimationFunction.getEstimatedDistance(node,dest);
            if (temp < min){
                min = temp;
                res = node;
            }
        }
        return res;
    }
}
