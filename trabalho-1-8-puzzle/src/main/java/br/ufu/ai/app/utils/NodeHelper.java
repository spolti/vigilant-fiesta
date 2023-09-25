package br.ufu.ai.app.utils;

import br.ufu.ai.app.Directions;
import br.ufu.ai.app.Node;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class NodeHelper {

    /**
     * Create a new node based in the Manhattan Distance Heuristic
     *
     * @param state
     * @param parent
     * @param g
     * @param goalState
     * @return a new node with the current state
     */
    public static Node createNodeManhattanDistanceHeuristic(int[] state, Node parent, int g, int[] goalState) {
        int heuristic = g + Heuristic.manhattanDistance(state, goalState);
        return new Node(state, parent, heuristic, g);
    }

    /**
     * Create a new node based in the Pieces Not In Place Heuristic
     *
     * @param state
     * @param parent
     * @param g
     * @param goalState
     * @return a new node with the current state
     */
    public static Node createNodePiecesNotInPlaceHeuristic(int[] state, Node parent, int g, int[] goalState) {
        int heuristic = g + Heuristic.piecesNotInPlace(state, goalState);
        return new Node(state, parent, heuristic, g);
    }

    /**
     * Generate and return all the possible board states from the given {@link Node}
     *
     * @param node
     * @return list of all possible states
     */
    public static Set<int[]> successors(Node node) {
        // use HashSet to not add duplicates, it will save a few lines of code to check if the state already exists
        // in the possible successors list, e.g. do not add the node itself.
        Set<int[]> allSuccessors = new HashSet<>();
        // copy state to avoid changing the original by accident
        int[] copyOfState = new int[9];
        System.arraycopy(node.getState(), 0, copyOfState, 0, 9);

        // try to move everything at least once, it will not do not valid moves, if that's the case
        // the generated board is the same thus the state is ignored.
        for (Directions direction : Directions.values()) {
            int[] successor = BoardHelper.move(copyOfState, direction);
            if (!Arrays.equals(copyOfState, successor)) {
                allSuccessors.add(successor);
            }
        }
        return allSuccessors;
    }


}
