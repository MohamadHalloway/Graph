package de.jpp.factory;

import de.jpp.algorithm.*;
import de.jpp.algorithm.interfaces.EstimationFunction;
import de.jpp.algorithm.interfaces.SearchAlgorithm;
import de.jpp.algorithm.strategy.NToZeroStrategy;
import de.jpp.io.TwoDimImgReader;
import de.jpp.maze.MazeImpl;
import de.jpp.maze.MazeReader;
import de.jpp.model.TwoDimGraph;
import de.jpp.model.XYNode;
import de.jpp.model.interfaces.Graph;
import de.jpp.model.interfaces.WeightedGraph;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * A class which is collecting several methods to create new SearchAlgorithms on Graphs with specified types
 *
 * @param <N> the type of nodes in graphs on which this search should operate
 * @param <A> the type of annotation of edges in graphs on which this search should operate
 */
public class SearchFactory<N, A> {


    /**
     * Returns a new SearchAlgorithm instance which searches the specified Graph with a DepthFirstSearch starting at the specified start node
     *
     * @param graph the graph
     * @param start the start node
     * @param <G>   the exact type of the Graph
     * @return a new SearchAlgorithm instance which searches the specified Graph with a DepthFirstSearch starting at the specified start node
     */
    public <G extends Graph<N, A>> SearchAlgorithm<N, A, G> getDepthFirstSearch(G graph, N start) {
        return new DepthFirstSearch<>(graph,start);
    }

    /**
     * Returns a new SearchAlgorithm instance which searches the specified Graph with a BreadthFirstSearch starting at the specified start node
     *
     * @param graph the graph
     * @param start the start node
     * @param <G>   the exact type of the Graph
     * @return a new SearchAlgorithm instance which searches the specified Graph with a BreadthFirstSearch starting at the specified start node
     */
    public <G extends Graph<N, A>> SearchAlgorithm<N, A, G> getBreadthFirstSearch(G graph, N start) {
        return new BreadthFirstSearch<>(graph,start);
    }

    /**
     * Returns a new SearchAlgorithm instance which searches the specified WeightedGraph with a BreadthFirstSearch starting at the specified start node
     *
     * @param graph the graph
     * @param start the start node
     * @param <G>   the exact type of the WeightedGraph
     * @return a new SearchAlgorithm instance which searches the specified Graph with a BreadthFirstSearch starting at the specified start node
     */
    public <G extends WeightedGraph<N, A>> SearchAlgorithm<N, A, G> getDijkstra(G graph, N start) {
        return new DijkstraSearch(graph,start);
    }

    /**
     * Returns a new SearchAlgorithm instance which searches the specified WeightedGraph with a A*Search starting at the specified start node aiming at the specified destination node
     *
     * @param graph              the graph
     * @param start              the start node
     * @param dest               the destination node
     * @param estimationFunction the heuristic function h estimated the distance from the current node to the destination
     * @param <G>                the exact type of the WeightedGraph
     * @return a new SearchAlgorithm instance which searches the specified WeightedGraph with a A*Search starting at the specified start node aiming at the specified destination node
     */

    public <G extends WeightedGraph<N, A>> SearchAlgorithm<N,A,G> getAStar(G graph, N start, N dest, EstimationFunction<N> estimationFunction) {
        return new AStar(graph,start,dest,estimationFunction);
    }


    public static void main(String[] args) {
        MazeImpl maze = new MazeImpl(new Random(),5,3);
        System.out.println(maze);
        BufferedImage bufferedImage = MazeReader.mazeAsImage(maze);
        TwoDimImgReader imgReader = new TwoDimImgReader();
        TwoDimGraph graph = imgReader.read(bufferedImage);
        XYNode start = graph.getNodes().iterator().next();
        DepthFirstSearch DFS = new DepthFirstSearch(graph,start);
        DFS.findPaths(new NToZeroStrategy(100000000));
        SearchResultImpl<XYNode,Double> res = (SearchResultImpl) DFS.getSearchResult();
        for (XYNode node : graph.getNodes()) {
            System.out.println(res.getPathTo(node));
        }
    }


}
