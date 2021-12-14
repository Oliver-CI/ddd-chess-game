package ddd.game.command;

import ddd.game.valueobject.Move;

import java.util.UUID;

public class MakeMove implements Command {
    private final Move move;
    private final UUID chessGameId;
    private final UUID currentPlayer;

    public MakeMove(Move move, UUID chessGameId, UUID currentPlayer) {
        this.move = move;
        this.chessGameId = chessGameId;
        this.currentPlayer = currentPlayer;
    }

    public Move getMove() {
        return move;
    }

    public UUID getChessGameId() {
        return chessGameId;
    }

    public UUID getCurrentPlayer() {
        return currentPlayer;
    }
}
