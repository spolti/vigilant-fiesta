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
     *
     * @param state
     * @param direction
     * @return the new state after the movement.
     */
    public static int[] move(int[] state, Directions direction) {
        // get the position of the blank space
        Map<String, Integer> position = positionOnMatrix(state, 0);
        int[][] board = getBoardAsMatrix(state);
        switch (direction) {
            case UP:
                if (position.get(LINE_KEY) > 0) {
                    int tempPos = board[position.get(LINE_KEY) - 1][position.get(COLUMN_KEY)];
                    board[position.get(LINE_KEY) - 1][position.get(COLUMN_KEY)] = board[position.get(LINE_KEY)][position.get(COLUMN_KEY)];
                    board[position.get(LINE_KEY)][position.get(COLUMN_KEY)] = tempPos;
                }
                return Arrays.stream(board).flatMapToInt(Arrays::stream).toArray();

            case DOWN:
                if (position.get(LINE_KEY) < 2) {
                    int tempPos = board[position.get(LINE_KEY) + 1][position.get(COLUMN_KEY)];
                    board[position.get(LINE_KEY) + 1][position.get(COLUMN_KEY)] = board[position.get(LINE_KEY)][position.get(COLUMN_KEY)];
                    board[position.get(LINE_KEY)][position.get(COLUMN_KEY)] = tempPos;
                }
                return Arrays.stream(board).flatMapToInt(Arrays::stream).toArray();

            case LEFT:
                if (position.get(COLUMN_KEY) > 0) {
                    int tempPos = board[position.get(LINE_KEY)][position.get(COLUMN_KEY) - 1];
                    board[position.get(LINE_KEY)][position.get(COLUMN_KEY) - 1] = board[position.get(LINE_KEY)][position.get(COLUMN_KEY)];
                    board[position.get(LINE_KEY)][position.get(COLUMN_KEY)] = tempPos;
                }
                return Arrays.stream(board).flatMapToInt(Arrays::stream).toArray();

            case RIGHT:
                if (position.get(COLUMN_KEY) < 2) {
                    int tempPos = board[position.get(LINE_KEY)][position.get(COLUMN_KEY) + 1];
                    board[position.get(LINE_KEY)][position.get(COLUMN_KEY) + 1] = board[position.get(LINE_KEY)][position.get(COLUMN_KEY)];
                    board[position.get(LINE_KEY)][position.get(COLUMN_KEY)] = tempPos;
                }
                return Arrays.stream(board).flatMapToInt(Arrays::stream).toArray();

            default:
                return state;
        }
    }


    /**
     * Checks the board's parity, there are two worlds of 8-puzzle boards, even and odds.
     * If the initial board and the goal state parity are both even or odd, then the board is solvable, otherwise it is not.
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
            return true;
        } else {
            System.out.println("Not solvable, one has even parity and the other has odd parity: " + initialStateInversions + " - " + goalStateInversions);
            return false;
        }
    }

    /**
     * Check if the current state matches the desired state
     *
     * @param state, goalState
     * @return true if the given state is the desired one
     */
    public static boolean isTheSolution(int[] state, int[] goalState) {
        return Arrays.equals(state, goalState);
    }

    /**
     * Calculate the board's parity
     *
     * @param array
     * @return the board parity.
     */
    private static int parity(int[] array) {
        int parity = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {continue;}
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] == 0) {continue;}
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
     * @return a map with the position of the target element on the matrix
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
        return map;
    }

    /**
     * Receives the board in the array format and return its corresponding matrix
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
