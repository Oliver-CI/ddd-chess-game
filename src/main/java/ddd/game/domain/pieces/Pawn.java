package ddd.game.domain.pieces;

import ddd.game.domain.ChessPieceColor;
import ddd.game.domain.Move;
import ddd.game.domain.pieces.movement.*;

import java.util.List;

public class Pawn extends ChessPiece {
    private final EnPassantMove enPassantMove;

    public Pawn(ChessPieceColor chessPieceColor) {
        super(chessPieceColor);
        enPassantMove = new EnPassantMove(chessPieceColor);
    }

    @Override
    public List<MovementStrategy> getMovementStrategies() {
        return List.of(new VerticalMove(Range.EN_PASSANT, true), enPassantMove);
    }

    @Override
    public List<MovementStrategy> getAttackingStrategies() {
        return List.of(new VerticalMove(Range.SINGLE, false), new HorizontalMove(Range.SINGLE), enPassantMove);
    }

    @Override
    public boolean canAttack(Move move) {
        final int targetX = move.target().getX();
        final int sourceX = move.source().getX();

        final int yMovement = move.target().getY() - move.source().getY();

        return yMovement == Range.SINGLE.value() && ((sourceX - 1) == targetX || (sourceX + 1) == targetX);
    }

}
