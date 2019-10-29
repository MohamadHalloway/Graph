package de.jpp.algorithm;

import de.jpp.model.interfaces.Edge;
import java.util.Objects;

public class NodeInformation<N,A>{
    private Edge<N,A> predecessor;
    private double distance;

    public NodeInformation(Edge<N, A> predecessor, double distance) {
        this.predecessor = predecessor;
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NodeInformation)) return false;
        NodeInformation<?, ?> that = (NodeInformation<?, ?>) o;
        return predecessor.equals(that.predecessor) &&
                distance == that.distance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(predecessor, distance);
    }

    @Override
    public String toString() {
        return "predecessor=" + predecessor +
                ", distance=" + distance;
    }

    public Edge<N, A> getPredecessor() {
        return predecessor;
    }

    public double getDistance() {
        return distance;
    }
}
