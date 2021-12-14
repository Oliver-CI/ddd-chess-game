package ddd.game.commands;

import ddd.game.domain.ChessGame;
import ddd.game.domain.Move;
import ddd.game.domain.Player;

import java.util.UUID;

public record MakeMove(Move move,
                       ChessGame.Id chessGameId,
                       Player currentPlayer) implements Command {
}
