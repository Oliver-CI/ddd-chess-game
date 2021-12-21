package ddd.game.events;

import ddd.core.DomainEvent;
import ddd.game.domain.Player;

public record GameStarted(Player playerWhite,
                          Player playerBlack) implements DomainEvent {

    public Player getPlayerWhite() {
        return playerWhite;
    }

    public Player getPlayerBlack() {
        return playerBlack;
    }
}
