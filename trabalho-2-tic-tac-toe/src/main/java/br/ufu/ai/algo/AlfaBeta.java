package br.ufu.ai.algo;

import br.ufu.ai.model.Player;
import br.ufu.ai.utils.BoardUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AlfaBeta {
    private static int maxDepth = 0;
    private static int expandedNodes = 0;
    private static int expandedNodesTotal = 0;
    private static Map<Character, Integer> scores = Map.of('X', 10, 'O', -10, 'P', 0);

    /**
     * Expand all possible moves for the current board.
     * the player X will always be the max
     *
     * @param board
     * @param isMaximizing
     * @param depth current depth in the search tree
     * @param currentPlayer
     * @return ??
     */
    public static int minimaxWithAlphaBetaPruning(char board[][], boolean isMaximizing, int depth, char currentPlayer, int alpha, int beta) {
        // keep track of the max depth
        // TODO add depth limit
        maxDepth = depth;
        // keep track of the expanded nodes
        expandedNodes++;
        char opponent = (currentPlayer == 'X') ? 'O' : 'X';

        // get available moves to expand everything
        char winner = BoardUtils.hasWon(board);
        // P == Tie
        if (winner != 'P') {
            return scores.get(winner);
        }
        if (BoardUtils.isDraw(board)) return 0;

        List<int[]> moves = BoardUtils.getAvailableMoves(board);

        // MAX
        if (isMaximizing) {
            //int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < moves.size(); i++) {
                char resultantMoveBoard[][] = BoardUtils.move(board, moves.get(i)[0], moves.get(i)[1], currentPlayer);
                int score = minimaxWithAlphaBetaPruning(resultantMoveBoard, false, depth + 1, opponent, alpha, beta);
                if (score > alpha) {
                    alpha = score;
                }
                if (alpha >= beta) {
                    return alpha;
                }
            }
            return alpha;

        } else { // MIN
            //int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < moves.size(); i++) {
                char resultantMoveBoard[][] = BoardUtils.move(board, moves.get(i)[0], moves.get(i)[1], currentPlayer);
                int score = minimaxWithAlphaBetaPruning(resultantMoveBoard, true, depth + 1, opponent, alpha, beta);
                if (score < beta) {
                    beta = score;
                }
                if (alpha >= beta) {
                    return beta;
                }
            }
            return beta;
        }
    }

    /**
     * Search for the best move for the current player.
     *
     * @param board
     * @param player
     * @param player2 - only used to know if both players are AI controlled
     * @return the best move for the current player
     */
    public static int[] bestMove(char board[][], Player player, Player player2) {

        long start = System.currentTimeMillis();

        List<int[]> availableMoves = BoardUtils.getAvailableMoves(board);
        int[] bestMove = new int[]{-1, -1};
        int bestScore = (player.getSymbol() == 'X') ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        // Player is human
        if (!player.isAiControlled()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Coordinate: eg: x,y:");
            String[] line = scanner.nextLine().split(",");
            bestMove[0] = Integer.parseInt(line[0]);
            bestMove[1] = Integer.parseInt(line[1]);
            return bestMove;
        }

        // if both players are AI, then the first move is random
        // otherwise the ending will always be the same
//        if (player.isAiControlled() && player2.isAiControlled() && availableMoves.size() == 9) {
//            int random = (int) (Math.random() * availableMoves.size());
//            bestMove[0] = availableMoves.get(random)[0];
//            bestMove[1] = availableMoves.get(random)[1];
//            return bestMove;
//        }

        for (int i = 0; i < availableMoves.size(); i++) {
            char resultantMoveBoard[][] = BoardUtils.move(board, availableMoves.get(i)[0], availableMoves.get(i)[1], player.getSymbol());

            boolean isMaximizing = player.getSymbol() == 'X' ? false : true;
            int score = minimaxWithAlphaBetaPruning(resultantMoveBoard, isMaximizing, 0, player.getSymbol() == 'X' ? 'O' : 'X', Integer.MIN_VALUE, Integer.MAX_VALUE);
            if ((player.getSymbol() == 'X' && score > bestScore) || (player.getSymbol() == 'O' && score < bestScore)) {
                bestScore = score;
                // X Y coordinates
                // Key is X and Value is Y
                bestMove[0] = availableMoves.get(i)[0];
                bestMove[1] = availableMoves.get(i)[1];
            }
        }
        expandedNodesTotal = expandedNodesTotal + expandedNodes;
        System.out.print("Move Took: (" + Arrays.toString(bestMove) + " -- Max Depth: " + maxDepth + " - " +
                " Expanded nodes (Total): " + expandedNodesTotal + " - " +
                " Expanded nodes current move: " +expandedNodes + "): " +  (System.currentTimeMillis() - start + "ms\n"));

        expandedNodes = 0;

        return bestMove;
    }

}
