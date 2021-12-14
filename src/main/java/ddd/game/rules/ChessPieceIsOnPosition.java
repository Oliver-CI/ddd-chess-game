package ddd.game.rules;

import ddd.game.domain.Move;
import ddd.game.domain.Position;
import ddd.game.domain.pieces.ChessPiece;

import java.util.Map;

public class ChessPieceIsOnPosition {

    private final Map<Position, ChessPiece> board;
    private final Move move;

    public ChessPieceIsOnPosition(Map<Position, ChessPiece> board, Move move) {
        this.board = board;
        this.move = move;
    }

    public void checkRule() {
        final ChessPiece chessPiece = board.get(move.source());
        if (chessPiece != null) return;

        throw new BusinessRuleViolationException("No chess piece found on selected position");
    }
}
