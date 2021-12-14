package ddd.game.rules;

import ddd.game.domain.ChessPieceColor;
import ddd.game.domain.Move;
import ddd.game.domain.Position;
import ddd.game.domain.pieces.ChessPiece;

import java.util.List;
import java.util.Map;

public class LastMoveMustBeDifferentColor {

    private final Map<Position, ChessPiece> board;
    private final List<Move> moves;
    private final boolean isWhite;

    public LastMoveMustBeDifferentColor(Map<Position, ChessPiece> board, List<Move> moves, boolean isWhite) {
        this.board = board;
        this.moves = moves;
        this.isWhite = isWhite;
    }

    public void checkRule() {
        final Move lastMove = moves.get(moves.size() - 1);
        if (isWhite && board.get(lastMove.target()).getColor() == ChessPieceColor.WHITE) {
            throw new BusinessRuleViolationException("Not ur turn mate !!! BACKOFF");
        }
    }
}
