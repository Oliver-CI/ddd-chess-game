package ddd.game.command;

public class StartGame implements Command {

    private final String playerId1;
    private final String playerId2;
    private final String gameInviteId;

    public StartGame(String playerId1, String playerId2, String gameInviteId) {
        this.playerId1 = playerId1;
        this.playerId2 = playerId2;
        this.gameInviteId = gameInviteId;
    }
}
