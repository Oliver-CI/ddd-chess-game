package ddd.game.domainevents;

import ddd.game.valueobject.Move;

import java.util.UUID;

public class MoveMade extends ddd.core.DomainEvent {
    private final Move move;
    private final UUID currentPlayer;

    public MoveMade(Move move, UUID currentPlayer) {
        this.move = move;
        this.currentPlayer = currentPlayer;
    }

    public Move getMove() {
        return move;
    }

    public UUID getCurrentPlayer() {
        return currentPlayer;
    }
}
