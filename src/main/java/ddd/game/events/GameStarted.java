package ddd.game.events;

import ddd.core.DomainEvent;
import ddd.game.domain.Player;

public class GameStarted extends DomainEvent {

    private final Player playerWhite;
    private final Player playerBlack;

    public GameStarted(Player playerWhite, Player playerBlack) {
        this.playerWhite = playerWhite;
        this.playerBlack = playerBlack;
    }

    public Player getPlayerWhite() {
        return playerWhite;
    }

    public Player getPlayerBlack() {
        return playerBlack;
    }
}
