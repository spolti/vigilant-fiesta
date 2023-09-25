package br.ufu.ai.app;

import br.ufu.ai.app.search.AStar;
import br.ufu.ai.app.utils.BoardHelper;
import br.ufu.ai.app.utils.Helpers;
import br.ufu.ai.app.utils.Heuristic;
import br.ufu.ai.app.utils.NodeHelper;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);

        int[] goalState = Helpers.generateState(9, true);
        Helpers.plot(goalState, "Goal State");
        int[] initialState = Helpers.generateState(9, true);
        Helpers.plot(initialState, "Initial State");

        System.out.println("\n########################################################################\n");

        // TODO if is not solvable ask user if he wants to proceed anyways
        boolean solvable = BoardHelper.isSolvable(initialState, goalState);
        boolean proceed = false;
        if ( ! solvable) {
            while (true) {
                System.out.println("Proceed: (y/n)");
                String line = scanner.nextLine();
                if (line.equals("y")) {
                    proceed = true;
                    break;
                }
            }
        } else {
           proceed = true;
        }

        if (proceed) {
            long start = System.currentTimeMillis();

            System.out.println("Solving using Manhattan distance heuristic...");
            AStar.solve(initialState, goalState, AStar.MANHATTAN_DISTANCE_HEURISTIC);
            System.out.println("Manhattan distance heuristic execution time: " + (System.currentTimeMillis() - start) + "ms\n");

            System.out.println("########################################################################");

            System.out.println("\nSolving using pieces not in place heuristic...");
            AStar.solve(initialState, goalState, AStar.NOT_IN_PLACE_HEURISTIC);
            System.out.println("Pieces not in place heuristic execution time: " + (System.currentTimeMillis() - start) + "ms");

//
//        as.add(NodeHelper.createNodeManhattanDistanceHeuristic(initialState, null, 0, goalState));
//        as.add(NodeHelper.createNodePiecesNotInPlaceHeuristic(initialState, null, 0, goalState));
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


            Node test = new Node(initialState, null, 0, 0);

            NodeHelper.successors(test).forEach(ints -> Helpers.plot(ints, "successor"));


            System.out.println("Overall execution time: " + (System.currentTimeMillis() - start) + "ms");
            System.exit(0);
        } else {
            System.out.println("Bye!");
            System.exit(0);
        }
    }
}
