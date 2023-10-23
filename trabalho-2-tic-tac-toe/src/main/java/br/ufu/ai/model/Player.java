package br.ufu.ai.model;

public class Player {

    /**
     * X or O
     */
    private char symbol;
    /**
     * Player name
     */
    private String name;
    private boolean aiControlled;

    public Player(char symbol, String name, boolean aiControlled) {
        if (symbol != 'X' && symbol != 'O') {
            throw new IllegalArgumentException("Invalid symbol");
        }
        this.symbol = symbol;
        this.name = name;
        this.aiControlled = aiControlled;
    }

    public char getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public boolean isAiControlled() {
        return aiControlled;
    }
}
