package ddd.game.command;

import ddd.game.valueobject.Player;

import java.util.UUID;

public class StartGame implements Command {

    private final Player playerId1;
    private final Player playerId2;
    private final UUID gameInviteId;

    public StartGame(Player playerId1, Player playerId2, UUID gameInviteId) {
        this.playerId1 = playerId1;
        this.playerId2 = playerId2;
        this.gameInviteId = gameInviteId;
    }

    public Player getPlayerId1() {
        return playerId1;
    }

    public Player getPlayerId2() {
        return playerId2;
    }

    public UUID getGameInviteId() {
        return gameInviteId;
    }
}
