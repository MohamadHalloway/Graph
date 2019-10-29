package de.jpp.algorithm.strategy;


public class NToZeroStrategy<N> implements SearchStopStrategy<N> {

    private int maxNodeCount;

    public NToZeroStrategy(int maxNodeCount) {
        this.maxNodeCount = maxNodeCount;
    }

    @Override
    public boolean stopSearch(N lastClosedNode) {
        maxNodeCount--;
        return maxNodeCount <= 0;
    }
}
