package ddd.game.domain.pieces;

import ddd.game.domain.ChessPieceColor;
import ddd.game.domain.pieces.movement.EnPassantMove;
import ddd.game.domain.pieces.movement.MovementStrategy;
import ddd.game.domain.pieces.movement.Range;
import ddd.game.domain.pieces.movement.VerticalMove;

import java.util.List;

public class Pawn extends ChessPiece {
    private final EnPassantMove enPassantMove;

    public Pawn(ChessPieceColor chessPieceColor) {
        super(chessPieceColor);
        enPassantMove = new EnPassantMove(chessPieceColor);
    }

    @Override
    public List<MovementStrategy> getMovementStrategies() {
        return List.of(new VerticalMove(Range.SINGLE), enPassantMove);
    }

}
