package br.ufu.ai.app.search;

import br.ufu.ai.app.Node;
import br.ufu.ai.app.utils.BoardHelper;
import br.ufu.ai.app.utils.Helpers;
import br.ufu.ai.app.utils.NodeHelper;

import javax.sound.midi.Soundbank;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public abstract class AStar {

    public static final String MANHATTAN_DISTANCE_HEURISTIC = "manhattan";
    public static final String NOT_IN_PLACE_HEURISTIC = "notInPlace";

    // The heuristic is the priority order. The lower the value, the higher the priority
    private static final PriorityQueue<Node> edge = new PriorityQueue<>(100);

    /**
     * Solves the puzzle using the {@link br.ufu.ai.app.utils.Heuristic} methods
     * @param initialState
     * @param goalState
     * @param heuristic
     */
    public static void solve(int[] initialState, int[] goalState, String heuristic) {
        int iterations = 0;
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

        Set<Node> visited = new HashSet<>() {};
        boolean solutionFound = false;
        // let's keep the loop while there are nodes to be expanded
        while (!edge.isEmpty()) {
            // get the first node in the edge
            Node node = edge.poll();

            // check if the node is the goalState
            if (BoardHelper.isTheSolution(node.getState(), goalState)) {
                // if it is, print the solution and break the loop
                Helpers.plot(node.getState(), "Solution");
                System.out.println("Final Cost: " + node.getG());
                System.out.println("Iterations: " + iterations);
                System.out.println("Generated States: " + visited.size());
                solutionFound = true;
                break;
            }

            // if the solution is not found, continue the search by expanding
            // the next possible  successors
            Set<int[]> successors = NodeHelper.successors(node);
            for (int[] suc : successors) {
                Node newNode = null;
                if (heuristic.equals(MANHATTAN_DISTANCE_HEURISTIC)) {
                    newNode = NodeHelper.createNodeManhattanDistanceHeuristic(
                            suc,
                            node,
                            node.getG() + 1,
                            goalState);
                } else {
                    newNode = NodeHelper.createNodePiecesNotInPlaceHeuristic(
                            suc,
                            node,
                            node.getG() + 1,
                            goalState);
                }
                // add the new node in the edge if it was not visited yet
                if (!visited.contains(newNode)) {
                    edge.add(newNode);
                }
                // add the successor in the already generated ones, to avoid
                // looping
                visited.add(node);
            }

            // increment the iterations
            iterations++;
        }

        if (!solutionFound) {
            System.out.println("The solution was not found....");
            System.out.println("Iterations: " + iterations);
            System.out.println("Generated States: " + visited.size());
        }
    }
}
