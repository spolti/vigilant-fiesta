package br.ufu.ai;

import br.ufu.ai.model.Game;
import br.ufu.ai.model.Player;

public class Main {
    public static void main(String[] args) {
        // Max first
        Player firstPlayer = new Player('X', "Max", true);
        Player secondPlayer = new Player('O', "Min", false);

        // Mini first
//        Player firstPlayer = new Player('O', "Min", true);
//        Player secondPlayer = new Player('X', "Max", true);

        long start = System.currentTimeMillis();
        Game game = new Game(firstPlayer, secondPlayer, Game.MINIMAX);
        game.start();
        System.out.println("Game Took: " + (System.currentTimeMillis() - start + "ms\n"));
    }
}