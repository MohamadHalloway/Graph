package de.jpp.algorithm;

import de.jpp.algorithm.interfaces.NodeStatus;
import de.jpp.model.interfaces.Edge;
import de.jpp.model.interfaces.Graph;
import java.util.LinkedList;

public class BreadthFirstSearch<N,A,G extends Graph<N,A>> extends BreadthFirstSearchTemplate<N,A,G> {
    public BreadthFirstSearch(G graph, N start) {
        super(graph, start);
    }

    @Override
    protected void realx(SearchResultImpl<N, A> result, Edge<N,A> edge) {
        N u = edge.getStart();
        N v = edge.getDestination();
        if (result.getNodeStatus(v) == NodeStatus.UNKOWN){
            result.setOpen(v);
            result.setInformation(v,new NodeInformation<>(edge,0));
        }
    }

    @Override
    protected N extractNode(SearchResultImpl<N, A> result, LinkedList<N> queue) {
        if (queue.isEmpty()){
            throw new IllegalArgumentException("queue is empty");
        }
        return queue.getFirst();
    }
}
