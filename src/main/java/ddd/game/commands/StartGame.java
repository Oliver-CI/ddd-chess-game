package ddd.game.commands;

import ddd.game.domain.Player;

import java.util.UUID;

public record StartGame(Player playerId1,
                        Player playerId2,
                        UUID gameInviteId) implements Command {
}
