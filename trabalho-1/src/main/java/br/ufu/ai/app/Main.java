package br.ufu.ai.app;

import br.ufu.ai.app.utils.BoardHelper;
import br.ufu.ai.app.utils.Helpers;
import br.ufu.ai.app.utils.Heuristic;
import br.ufu.ai.app.utils.NodeHelper;

import java.io.IOException;

/**
 * Main class
 * It will execute the program using the two heuristics in a serial mode
 * At the end of the execution, it will show the results comparing both.
 * <p>
 * TODOs:
 * - make the automatic generation of the goalState and initialState parameterizable
 * - plot a graph?????
 * - create the successors method
 */
public class Main {

    public static void main(String[] args) {

        int[] goalState = Helpers.generateState(9, true);
        Helpers.plot(goalState, "Goal State");
        int[] initialState = Helpers.generateState(9, true);
        Helpers.plot(initialState, "Initial State");

        // TODO if is not solvable as use if he wants to proceed anyways
        BoardHelper.isSolvable(initialState, goalState);


        System.out.println(Heuristic.manhattanDistance(initialState, goalState));
        System.out.println(Heuristic.piecesNotInPlace(initialState, goalState));


//        TreeSet<Node> as = new TreeSet<>(){};
//        as.add(NodeHelper.createNodeManhattanDistanceHeuristic(initialState, null, 0, goalState));
//        as.add(NodeHelper.createNodePiecesNotInPlaceHeuristic(initialState, null, 0, goalState));
//
//
//        System.out.println("asdasdasd ");
//        for(Node asd : as){
//            System.out.println(asd.getH());
//        }

//        int[] currentState = BoardHelper.move(initialState, Directions.UP);
//        Helpers.plot(currentState, "up");
//
//        currentState = BoardHelper.move(currentState, Directions.LEFT);
//        Helpers.plot(currentState, "left");
//
//        currentState = BoardHelper.move(currentState, Directions.DOWN);
//        Helpers.plot(currentState, "down");
//
//        currentState = BoardHelper.move(currentState, Directions.RIGHT);
//        Helpers.plot(currentState, "right");


        int[] array2 = new int[9];
        System.arraycopy(initialState, 0, array2, 0, initialState.length);


        Node test = new Node(new int[]{1,2,3,4,5,0,6,7,8}, null, 0, 0);

        NodeHelper.successors(test).forEach(ints -> Helpers.plot(ints, "successor"));

        System.exit(0);
    }
}
