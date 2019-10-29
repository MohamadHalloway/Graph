package de.jpp.algorithm;

import de.jpp.algorithm.interfaces.NodeStatus;
import de.jpp.algorithm.interfaces.ObservableSearchResult;
import de.jpp.algorithm.interfaces.SearchAlgorithm;
import de.jpp.algorithm.interfaces.SearchResult;
import de.jpp.algorithm.strategy.SearchStopStrategy;
import de.jpp.factory.SearchStopFactory;
import de.jpp.model.interfaces.Edge;
import de.jpp.model.interfaces.Graph;


public class DepthFirstSearch<N, A, G extends Graph<N, A>> implements SearchAlgorithm<N, A, G> {
    private static boolean stopped = false;
    private G graph;
    private N start;
    private SearchResultImpl<N,A> result = new SearchResultImpl<>();
    private static double distance = 0;

    public DepthFirstSearch(G graph, N start) {
        if (graph == null || start == null) {
            throw new IllegalArgumentException("Graph or start IS NULL");
        }
        this.graph = graph;
        this.start = start;
        for (N node: graph.getNodes()){
            result.initNode(node);
        }

    }

    private boolean expand(N node , SearchStopStrategy<N> strategy){
        if (stopped || node == null){     //if should stop
            return false;
        }
        result.setOpen(node);
        result.setClosed(node);
        if (strategy.stopSearch(node)){
            stopped = true;  //stop()
            return false;
        }
        for (Edge<N,A> edge : graph.getNeighbours(node)) {
            if (stopped){
                return false;
            }
            N dest = edge.getDestination();
            if (result.getNodeStatus(dest) == NodeStatus.UNKOWN){
                NodeInformation<N,A> info = new NodeInformation<>(edge,distance++);
                result.setInformation(dest,info);
                expand(dest,strategy);
            }
        }
            return true;
    }


    @Override
    public SearchResult<N, A> findPaths(SearchStopStrategy<N> type) {
        result.clear();
        distance = 0;
        stopped = false;
        result.setInformation(start,new NodeInformation<>(null,distance++));
        expand(start,type);
        return result;
    }

    @Override
    public SearchResult<N, A> findAllPaths() {
        SearchStopFactory factory = new SearchStopFactory();
        return findPaths(factory.expandAllNodes());
    }

    @Override
    public ObservableSearchResult<N, A> getSearchResult() {
        return result;
    }

    @Override
    public N getStart() {
        return start;
    }

    @Override
    public G getGraph() {
        return graph;
    }

    @Override
    public void stop() {
        stopped = true;
    }



}
