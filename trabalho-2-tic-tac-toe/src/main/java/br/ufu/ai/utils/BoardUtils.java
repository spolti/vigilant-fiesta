package br.ufu.ai.utils;

import br.ufu.ai.model.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Tic Tac Toe board utilities.
 */
public abstract class BoardUtils {

    /**
     * Makes a move
     *
     * @param board
     * @param x,     y coordinates of the move's position
     * @param player
     * @return the board with the given move
     */
    public static char[][] move(char board[][], int x, int y, char player) {
        char[][] newBoard = new char[3][3];
        // make a deepCopy to avoid copying the board by reference
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, board.length);
        }
        if (newBoard[x][y] == ' ') {
            newBoard[x][y] = player;
        }
        return newBoard;
    }

    /**
     * Verifies if the current board is a draw
     *
     * @param board
     * @return true or false
     */
    public static boolean isDraw(char board[][]) {
        return 'P' == hasWon(board) && getAvailableMoves(board).isEmpty();
    }

    /**
     * Verifies if the current board has a winner
     *
     * @param board
     * @return true or false
     */
    public static char hasWon(char board[][]) {
        // First check the rows
        for (char[] row : board) {
            if (row[0] == row[1] && row[0] == row[2] && row[0] != ' ') {
                return row[0];
            }
        }
        // Then check the columns
        for (int col = 0; col < board.length; col++) {
            if (board[0][col] == board[1][col] && board[0][col] == board[2][col] && board[0][col] != ' ') {
                return board[0][col];
            }

        }
        // Then check the diagonals
        if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != ' ') {
            return board[0][0];
        } else if (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != ' ') {
            return board[0][2];
        }
        return 'P';
    }

    /**
     * Return a Map<Integer, Integer> with the matrix coordinates of the available moves.
     * Eg: [0,0], [1,2] which corresponds to:
     * <p>
     *' '| X | X
     * X | O |' '
     * O | X | O
     */
    public static List<int[]> getAvailableMoves(char board[][]) {
        List<int[]> availablePositions = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == ' ') {
                    availablePositions.add(new int[]{i, j});
                }
            }
        }
        return availablePositions;
    }

    /**
     * Print the current board, with the
     *
     * @param board
     * @param player
     */
    public static void plotBoard(char board[][], Player player) {
        StringBuilder b = new StringBuilder("Player's turn: ");
        b.append(player.getName()).append(" - Available Moves: ");
        getAvailableMoves(board).forEach(item -> b.append(Arrays.toString(item)).append(" "));
        b.append("\n");
        b.append(" -----------");
        for (char[] row : board) {
            b.append("\n");
            b.append("|");
            for (char c : row) {
                b.append(" " + c + " |");
            }
            b.append("\n");
            b.append(" -----------");
        }
        System.out.println(b);
    }

    public static int evaluate(char board[][], char currentPlayer) {
        int score = 0;

        //lines
        for (int i = 0; i < 3; i++) {
            score += evaluateLine(board[i][0], board[i][1], board[i][2], currentPlayer);
        }

        // columns
        for (int j = 0; j < 3; j++) {
            score += evaluateLine(board[0][j], board[1][j], board[2][j], currentPlayer);
        }

        // diagonals
        score += evaluateLine(board[0][0], board[1][1], board[2][2], currentPlayer);
        score += evaluateLine(board[0][2], board[1][1], board[2][0], currentPlayer);

        return score;
    }


    public static int evaluateLine(char cell1, char cell2, char cell3, char currentPlayer) {
        int countPlayer = 0;
        int countOpponent = 0;

        if (cell1 == currentPlayer) {
            countPlayer++;
        } else if (cell1 != ' ') {
            countOpponent++;
        }

        if (cell2 == currentPlayer) {
            countPlayer++;
        } else if (cell2 != ' ') {
            countOpponent++;
        }

        if (cell3 == currentPlayer) {
            countPlayer++;
        } else if (cell3 != ' ') {
            countOpponent++;
        }

        if (countPlayer == 3) {
            return 100;
        } else if (countOpponent == 3) {
            return -100;
        } else if (countPlayer == 2 && countOpponent == 0) {
            return 10;
        } else if (countPlayer == 1 && countOpponent == 0) {
            return 1;
        } else {
            return 0;
        }
    }

}
