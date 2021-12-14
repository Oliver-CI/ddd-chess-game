package ddd.game.domainevents;

import ddd.core.DomainEvent;
import ddd.game.valueobject.Player;

public class TurnAssigned extends DomainEvent {
    private final Player currentPlayer;

    public TurnAssigned(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
