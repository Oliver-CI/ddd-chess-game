package ddd.game.rules;

import ddd.core.businessrules.BusinessRule;
import ddd.core.businessrules.BusinessRuleViolation;
import ddd.game.domain.ChessPieceColor;
import ddd.game.domain.Move;
import ddd.game.domain.Position;
import ddd.game.domain.pieces.ChessPiece;

import java.util.List;
import java.util.Map;

public class LastMoveMustBeDifferentColor extends BusinessRule {

    private final Map<Position, ChessPiece> board;
    private final List<Move> moves;
    private final boolean isWhite;

    public LastMoveMustBeDifferentColor(Map<Position, ChessPiece> board, List<Move> moves, boolean isWhite) {
        this.board = board;
        this.moves = moves;
        this.isWhite = isWhite;
    }

    @Override
    public List<BusinessRuleViolation> checkRule() {
        if (moves.isEmpty()) return List.of();
        if (isTheLastMoveOfDifferentColor()) return List.of();

        return List.of(new BusinessRuleViolation("Must move chess piece of own color"));
    }

    private boolean isTheLastMoveOfDifferentColor() {
        final Move lastMove = moves.get(moves.size() - 1);
        final ChessPiece chessPiece = board.get(lastMove.target());

        return isWhite && chessPiece.getColor() == ChessPieceColor.BLACK;
    }
}
