package ddd.game.command;

import ddd.game.valueobject.Move;
import ddd.game.valueobject.Player;

import java.util.UUID;

public record MakeMove(Move move,
                       UUID chessGameId,
                       Player currentPlayer) implements Command {
}
