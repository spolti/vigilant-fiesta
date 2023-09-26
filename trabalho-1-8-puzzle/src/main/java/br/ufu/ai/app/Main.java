package br.ufu.ai.app;

import br.ufu.ai.app.search.AStar;
import br.ufu.ai.app.utils.BoardHelper;
import br.ufu.ai.app.utils.Helpers;
import br.ufu.ai.app.utils.Heuristic;
import br.ufu.ai.app.utils.NodeHelper;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Main class
 * It will execute the program using the two heuristics in a serial mode
 * At the end of the execution, it will show the results comparing both.
 * <p>
 * TODOs:
 *   Migrate Main to quarkus app
 */
@QuarkusMain
public class Main implements QuarkusApplication {

    @Override
    public int run(String... args) {
        Scanner scanner = new Scanner(System.in);

        int[] goalState = Helpers.generateState(9, true);
        Helpers.plot(goalState, "Goal State");
        int[] initialState = Helpers.generateState(9, true);
        Helpers.plot(initialState, "Initial State");

        System.out.println("\n########################################################################\n");

        boolean solvable = BoardHelper.isSolvable(initialState, goalState);
        boolean proceed = false;
        if ( ! solvable) {
            while (true) {
                System.out.println("Proceed: (y/n)");
                String line = scanner.nextLine();
                if (line.equals("y")) {
                    proceed = true;
                    break;
                } else {
                    System.out.println("Bye!");
                    return 0;
                }
            }
        } else {
           proceed = true;
        }

        if (proceed) {
            long start = System.currentTimeMillis();

            System.out.println("Solving using Manhattan distance heuristic...");
            AStar.solve(initialState, goalState, AStar.MANHATTAN_DISTANCE_HEURISTIC);
            long manh = System.currentTimeMillis() - start;
            System.out.println("Manhattan distance heuristic execution time: " + (manh) + "ms\n");

            System.out.println("########################################################################");

            System.out.println("\nSolving using pieces not in place heuristic...");
            AStar.solve(initialState, goalState, AStar.NOT_IN_PLACE_HEURISTIC);
            long pie = System.currentTimeMillis() - start;
            System.out.println("Pieces not in place heuristic execution time: " + (pie) + "ms");


            System.out.println("########################################################################");
            System.out.println("Overall execution time: " + TimeUnit.MILLISECONDS.toSeconds((manh + pie)) + "s");
            System.exit(0);
        } else {
            System.out.println("Bye!");
            return 0;
        }

        return 0;
    }
}
