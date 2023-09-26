package br.ufu.ai.app.utils;

import br.ufu.ai.app.Directions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class BoardHelper {

    public static String LINE_KEY = "line";
    public static String COLUMN_KEY = "column";

    /**
     * Move the piece to the desired direction
     * @param state
     * @param direction
     * @return the new state after the movement.
     */
    public static int[] move (int[] state, Directions direction) {
        // get the position of the blank space
        Map<String, Integer> position = positionOnMatrix(state, 0);
        int[][] board = getBoardAsMatrix(state);
        switch (direction) {
            case UP:
                if(position.get(LINE_KEY) > 0) {
                    int tempPos = board[position.get(LINE_KEY) - 1][position.get(COLUMN_KEY)];
                    board[position.get(LINE_KEY) - 1][position.get(COLUMN_KEY)] =  board[position.get(LINE_KEY)][position.get(COLUMN_KEY)];
                    board[position.get(LINE_KEY)][position.get(COLUMN_KEY)] = tempPos;
                }
                return  Arrays.stream(board).flatMapToInt(Arrays::stream).toArray();

            case DOWN:
                if(position.get(LINE_KEY) < 2) {
                    int tempPos = board[position.get(LINE_KEY) + 1][position.get(COLUMN_KEY)];
                    board[position.get(LINE_KEY) + 1][position.get(COLUMN_KEY)] =  board[position.get(LINE_KEY)][position.get(COLUMN_KEY)];
                    board[position.get(LINE_KEY)][position.get(COLUMN_KEY)] = tempPos;
                }
                return  Arrays.stream(board).flatMapToInt(Arrays::stream).toArray();

            case LEFT:
                if(position.get(COLUMN_KEY) > 0) {
                    int tempPos = board[position.get(LINE_KEY)][position.get(COLUMN_KEY) - 1];
                    board[position.get(LINE_KEY)][position.get(COLUMN_KEY) - 1] =  board[position.get(LINE_KEY)][position.get(COLUMN_KEY)];
                    board[position.get(LINE_KEY)][position.get(COLUMN_KEY)] = tempPos;
                }
                return  Arrays.stream(board).flatMapToInt(Arrays::stream).toArray();

            case RIGHT:
                if(position.get(COLUMN_KEY) < 2) {
                    int tempPos = board[position.get(LINE_KEY)][position.get(COLUMN_KEY) + 1];
                    board[position.get(LINE_KEY)][position.get(COLUMN_KEY) + 1] =  board[position.get(LINE_KEY)][position.get(COLUMN_KEY)];
                    board[position.get(LINE_KEY)][position.get(COLUMN_KEY)] = tempPos;
                }
                return Arrays.stream(board).flatMapToInt(Arrays::stream).toArray();

            default:
                return state;
        }
    }


    /**
     * Checks the parity of the board, there are two worlds of 8-puzzle boards.
     * If the initial board parity is even the board is solvable, otherwise it is not.
     * So, to check it initially we must check its parity first.
     *
     * @param initialState
     * @param goalState
     * @return true if the board is solvable, false otherwise
     */
    public static boolean isSolvable(int[] initialState, int[] goalState) {
        int initialStateInversions = parity(initialState);
        int goalStateInversions = parity(goalState);

        if (initialStateInversions % 2 == 0 && goalStateInversions % 2 == 0) {
            System.out.println("Solvable, both have even parity: " + initialStateInversions + " - " + goalStateInversions);
            return true;
        } else if (initialStateInversions % 2 != 0 && goalStateInversions % 2 != 0) {
            System.out.println("Solvable, both have odd parity: " + initialStateInversions + " - " + goalStateInversions);
            // System.exit(10);
            return true;
        } else {
            System.out.println("Not solvable, one has even parity and the other has odd parity: " + initialStateInversions + " - " + goalStateInversions);
            // System.exit(10);
            return false;
        }
    }

    /**
     * Converts check if the
     * @param  state, goalState
     * @return true if the given state is the desired one
     */
    public static boolean isTheSolution(int[] state, int[] goalState){
        return Arrays.equals(state, goalState);
    }

    private static int parity (int[] array) {
        int parity = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                continue;
            }
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] == 0) {
                    continue;
                }
                if (array[i] > array[j]) {
                    parity++;
                }
            }
        }
        return parity;
    }


    /**
     * Locates the target element in the board and returns its position on the matrix
     * e.g. Target 6 is on line 2 and column 0
     * Target 6 is on line 0 and column 1
     *
     * @param board
     * @param target
     * @return
     */
    public static Map<String, Integer> positionOnMatrix(int[] board, int target) {
        Map<String, Integer> map = new HashMap<>();
        int[][] matrix = getBoardAsMatrix(board);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[i][j] == target) {
                    map.put(LINE_KEY, i);
                    map.put(COLUMN_KEY, j);
                }
            }
        }
        //System.out.println("Target " + target + " is on line " + map.get("line") + " and column " + map.get("column"));
        return map;
    }

    /**
     * receives a board array and transform return its corresponding matrix
     *
     * @param array
     */
    public static int[][] getBoardAsMatrix(int[] array) {
        int[][] matrix = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = array[i * 3 + j];
            }
        }
        return matrix;
    }
}
