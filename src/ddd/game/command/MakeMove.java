package ddd.game.command;

import ddd.game.valueobject.Move;

import java.util.UUID;

public record MakeMove(Move move,
                       UUID chessGameId,
                       UUID currentPlayer) implements Command {
}
