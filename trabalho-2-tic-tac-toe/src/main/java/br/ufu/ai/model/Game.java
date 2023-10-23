package br.ufu.ai.model;

import br.ufu.ai.algo.AlfaBeta;
import br.ufu.ai.algo.MiniMax;
import br.ufu.ai.utils.BoardUtils;

public class Game {

    public static final String MINIMAX = "minimax";
    public static final String ALPHABETA = "alphabeta";

    private Player currentPlayer;
    private Player nextPlayer;
    private String algorithm;
    private char board[][] = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    public Game(Player firstPlayer, Player secondPlayer,  String algorithm) {
        System.out.println("Starting a new game ");
        this.currentPlayer = firstPlayer;
        this.nextPlayer = secondPlayer;
        this.algorithm = algorithm;
//        if (board.length > 0) {
//            board = ini
//        }
    }

    public void start() {
        if (algorithm.equals(MINIMAX)) {
            StartGame(MINIMAX);
        } else if (algorithm.equals(ALPHABETA)) {
            StartGame(ALPHABETA);
        } else {
            throw new IllegalArgumentException("Invalid algorithm");
        }
    }

    public void StartGame(String algorithm) {
        System.out.println("Starts [" + algorithm + "] " + currentPlayer.getName());
        while (true) {
            BoardUtils.plotBoard(board, currentPlayer);
            int[] coordinates;
            if (algorithm.equals(MINIMAX)) {
                coordinates = MiniMax.bestMove(board, currentPlayer);
            } else {
                coordinates = AlfaBeta.bestMove(board, currentPlayer, this.nextPlayer);
            }

            board = BoardUtils.move(board, coordinates[0], coordinates[1], currentPlayer.getSymbol());

            if ('P' != BoardUtils.hasWon(board)) {
                BoardUtils.plotBoard(board, currentPlayer);
                System.out.println("Game over!");
                System.out.println("Winner is " + currentPlayer.getName());
                break;
            } else if (BoardUtils.isDraw(board)) {
                System.out.println("Draw!");
                break;
            }
            // switch player's turn
            Player temp = currentPlayer;
            currentPlayer = nextPlayer;
            nextPlayer = temp;
        }
    }

}
