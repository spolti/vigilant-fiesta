package br.ufu.ai.app.utils;

import java.util.Map;

import static br.ufu.ai.app.utils.BoardHelper.COLUMN_KEY;
import static br.ufu.ai.app.utils.BoardHelper.LINE_KEY;

public abstract class Heuristic {

    /**
     * First Heuristic
     * Calculate the quarter distance between the two nodes - Manhattan distance
     *
     * @param currentState
     * @param goalState
     * @return the position of the target element in the matrix
     */
    public static int manhattanDistance(int[] currentState, int[] goalState) {
        int dist = 0;
        int[][] board1 = BoardHelper.getBoardAsMatrix(currentState);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board1[i][j] == 0) {
                    continue;
                }
                Map<String, Integer> position = BoardHelper.positionOnMatrix(goalState, board1[i][j]);
                dist += (Math.abs(position.get(LINE_KEY) - i) + Math.abs(position.get(COLUMN_KEY) - j));
            }
        }
        return dist;
    }

    /**
     * Second Heuristic
     * Calculate the number of pieces not in place
     *
     * @param currentState
     * @param goalState
     * @return the number of pieces not in place
     */
    public static int piecesNotInPlace(int[] currentState, int[] goalState) {
        int out = 0;
        int[][] board1 = BoardHelper.getBoardAsMatrix(currentState);
        int[][] board2 = BoardHelper.getBoardAsMatrix(goalState);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board1[i][j] != board2[i][j]) {
                    out++;
                }
            }
        }
        return out;
    }
}
