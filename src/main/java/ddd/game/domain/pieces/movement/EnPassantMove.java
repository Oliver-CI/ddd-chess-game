package ddd.game.domain.pieces.movement;

import ddd.game.domain.ChessPieceColor;
import ddd.game.domain.Move;

public record EnPassantMove(ChessPieceColor color) implements MovementStrategy {

    @Override
    public boolean supportsMove(Move move) {
        final int targetX = move.target().getX();
        return move.source().getX() == 2 && (targetX == Range.SINGLE.getValue() || targetX == Range.EN_PASSANT.getValue());
    }
}
