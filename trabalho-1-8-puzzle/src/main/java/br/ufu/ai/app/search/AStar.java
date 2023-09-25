package br.ufu.ai.app.search;

import br.ufu.ai.app.Node;
import br.ufu.ai.app.utils.NodeHelper;

import java.util.TreeSet;

public abstract class AStar {

    public static final String MANHATTAN_DISTANCE_HEURISTIC = "manhattan";
    public static final String NOT_IN_PLACE_HEURISTIC = "notInPlace";

    public static void solve(int[] initialState, int[] goalState, String heuristic) {
        int iterations = 0;
        TreeSet<Node> edge = new TreeSet<>() {
        };

        // add the initialState in the edge based in the heuristic
        if (heuristic.equals(MANHATTAN_DISTANCE_HEURISTIC)) {
            edge.add(NodeHelper.createNodeManhattanDistanceHeuristic(
                    initialState,
                    null,
                    0,
                    goalState));
        } else {
            edge.add(NodeHelper.createNodePiecesNotInPlaceHeuristic(
                    initialState,
                    null,
                    0,
                    goalState));
        }

        // let's keep the loop while there are nodes to be expanded
        while (!edge.isEmpty()) {
            // get the first node in the edge
            Node node = edge.pollFirst();

            // check if the node is the goalState
            if (NodeHelper.isGoalState(node, goalState)) {
                // if it is, print the solution and break the loop
                NodeHelper.printSolution(node, iterations);
                break;
            }

            // if it is not the goalState, expand the node
            edge.addAll(NodeHelper.expand(node, goalState, heuristic));

            // increment the iterations
            iterations++;
        }
    }
}
