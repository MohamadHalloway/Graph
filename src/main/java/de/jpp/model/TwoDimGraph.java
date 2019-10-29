package de.jpp.model;

import de.jpp.model.interfaces.Edge;
import de.jpp.model.interfaces.Graph;
import de.jpp.model.interfaces.ObservableGraph;
import de.jpp.model.interfaces.WeightedGraph;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * A Two Dimensional graph. <br>
 * The abstract-tag is only set because the tests will not compile otherwise. You should remove it!
 */
public class TwoDimGraph extends ObservableGraphImpl<XYNode,Double> implements WeightedGraph<XYNode,Double>{


    public TwoDimGraph(Graph<XYNode, Double> graph) {
        super(graph);
    }

    /**
     * Adds an edge to the graph which is automatically initialised with the euclidian distance of the nodes <br>
     * Returns the newly created edge
     *
     * @param start the start node of the edge
     * @param dest  the destination node of the edge
     * @return the newly created edge
     */
    public Edge<XYNode, Double> addEuclidianEdge(XYNode start, XYNode dest) {
            return addEdge(start,dest, Optional.of(start.euclidianDistTo(dest)));
    }

    @Override
    public double getDistance(Edge<XYNode, Double> edge) {
        return edge.getAnnotation().get();
    }


//    @Override
//    public void addNodeAddedListener(Consumer<XYNode> listener) {
//        observableGraph.addNodeAddedListener(listener);
//    }
//
//    @Override
//    public void addNodeRemovedListener(Consumer<XYNode> listener) {
//        observableGraph.addNodeRemovedListener(listener);
//    }
//
//    @Override
//    public void addEdgeAddedListener(Consumer<Edge<XYNode, Double>> listener) {
//        observableGraph.addEdgeAddedListener(listener);
//    }
//
//    @Override
//    public void addEdgeRemovedListener(Consumer<Edge<XYNode, Double>> listener) {
//        observableGraph.addEdgeRemovedListener(listener);
//    }
//
//    @Override
//    public void removeNodeAddedListener(Consumer<XYNode> listener) {
//        observableGraph.removeNodeAddedListener(listener);
//    }
//
//    @Override
//    public void removeNodeRemovedListener(Consumer<XYNode> listener) {
//        observableGraph.removeNodeRemovedListener(listener);
//    }
//
//    @Override
//    public void removeEdgeAddedListener(Consumer<Edge<XYNode, Double>> listener) {
//        observableGraph.removeEdgeAddedListener(listener);
//    }
//
//    @Override
//    public void removeEdgeRemovedListener(Consumer<Edge<XYNode, Double>> listener) {
//        observableGraph.removeEdgeRemovedListener(listener);
//    }
//
//    @Override
//    public void addNeighboursListedListener(Consumer<Collection<Edge<XYNode, Double>>> listener) {
//        observableGraph.addNeighboursListedListener(listener);
//    }
//
//    @Override
//    public void addReachableListedListener(Consumer<Collection<Edge<XYNode, Double>>> listener) {
//        observableGraph.addReachableListedListener(listener);
//    }
//
//    @Override
//    public void addNodesListedListener(Consumer<Collection<XYNode>> listener) {
//        observableGraph.addNodesListedListener(listener);
//    }
//
//    @Override
//    public void addEdgesListedListener(Consumer<Collection<Edge<XYNode, Double>>> listener) {
//        observableGraph.addEdgesListedListener(listener);
//    }
//
//    @Override
//    public void removeNeighboursListedListener(Consumer<Collection<Edge<XYNode, Double>>> listener) {
//        observableGraph.removeNeighboursListedListener(listener);
//    }
//
//    @Override
//    public void removeReachableListedListener(Consumer<Collection<Edge<XYNode, Double>>> listener) {
//        observableGraph.removeReachableListedListener(listener);
//    }
//
//    @Override
//    public void removeNodesListedListener(Consumer<Collection<XYNode>> listener) {
//        observableGraph.removeNodesListedListener(listener);
//    }
//
//    @Override
//    public void removeEdgesListedListener(Consumer<Collection<Edge<XYNode, Double>>> listener) {
//        observableGraph.removeEdgesListedListener(listener);
//    }
//
//    @Override
//    public double getDistance(Edge<XYNode, Double> edge) {
//        return edge.getAnnotation().get();
//    }
//
//    @Override
//    public boolean addNode(XYNode node) {
//        return observableGraph.addNode(node);
//    }
//
//    @Override
//    public boolean addNodes(Collection<? extends XYNode> nodes) {
//        return observableGraph.addNodes(nodes);
//    }
//
//    @Override
//    public boolean addNodes(XYNode... nodes) {
//        return observableGraph.addNodes(nodes);
//    }
//
//    @Override
//    public Collection<XYNode> getNodes() {
//        return observableGraph.getNodes();
//    }
//
//    @Override
//    public Edge<XYNode, Double> addEdge(XYNode start, XYNode destination, Optional<Double> annotation) {
//        return addEuclidianEdge(start,destination);
//    }
//
//    @Override
//    public boolean removeEdge(Edge<XYNode, Double> edge) {
//        return observableGraph.removeEdge(edge);
//    }
//
//    @Override
//    public Collection<Edge<XYNode, Double>> getNeighbours(XYNode node) {
//        return observableGraph.getNeighbours(node);
//    }
//
//    @Override
//    public Collection<Edge<XYNode, Double>> getReachableFrom(XYNode node) {
//        return observableGraph.getReachableFrom(node);
//    }
//
//    @Override
//    public Collection<Edge<XYNode, Double>> getEdges() {
//        return observableGraph.getEdges();
//    }
//
//    @Override
//    public boolean removeNode(XYNode node) {
//        return observableGraph.removeNode(node);
//    }
//
//    @Override
//    public boolean removeNodes(Collection<? extends XYNode> nodes) {
//        return observableGraph.removeNodes(nodes);
//    }
//
//    @Override
//    public boolean removeNodes(XYNode... nodes) {
//        return observableGraph.removeNodes(nodes);
//    }
//
//    @Override
//    public void clear() {
//        observableGraph.clear();
//    }
}

