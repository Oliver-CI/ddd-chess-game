package ddd.game.domainevents;

import ddd.game.valueobject.Move;
import ddd.game.valueobject.Player;

public class MoveMade extends ddd.core.DomainEvent {
    private final Move move;
    private final Player currentPlayer;

    public MoveMade(Move move, Player currentPlayer) {
        this.move = move;
        this.currentPlayer = currentPlayer;
    }

    public Move getMove() {
        return move;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
