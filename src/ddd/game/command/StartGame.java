package ddd.game.command;

import ddd.game.valueobject.Player;

import java.util.UUID;

public record StartGame(Player playerId1,
                        Player playerId2,
                        UUID gameInviteId) implements Command {
}
