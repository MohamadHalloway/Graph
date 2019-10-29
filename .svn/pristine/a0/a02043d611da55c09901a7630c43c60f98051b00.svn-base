package de.jpp.algorithm.strategy;

public class StartToDestStrategy<N> implements SearchStopStrategy<N> {

    private N dest;

    public StartToDestStrategy(N dest) {
        this.dest = dest;
    }

    @Override
    public boolean stopSearch(N lastClosedNode) {
        return lastClosedNode.equals(dest);
    }

    /**
     * Returns the destination node of this search
     *
     * @Returns the destination node of this search
     */
    public N getDest() {
        return dest;
    }

}
