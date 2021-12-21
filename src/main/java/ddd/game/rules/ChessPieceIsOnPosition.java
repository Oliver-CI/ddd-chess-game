package ddd.game.rules;

import ddd.core.businessrules.BusinessRule;
import ddd.core.businessrules.BusinessRuleViolation;
import ddd.game.domain.Move;
import ddd.game.domain.Position;
import ddd.game.domain.pieces.ChessPiece;

import java.util.List;
import java.util.Map;

public class ChessPieceIsOnPosition extends BusinessRule {

    private final Map<Position, ChessPiece> board;
    private final Move move;

    public ChessPieceIsOnPosition(Map<Position, ChessPiece> board, Move move) {
        this.board = board;
        this.move = move;
    }

    @Override
    public List<BusinessRuleViolation> checkRule() {
        final ChessPiece chessPiece = board.get(move.source());
        if (chessPiece != null) return List.of();

        return List.of(new BusinessRuleViolation("No chess piece found on selected position"));
    }
}
