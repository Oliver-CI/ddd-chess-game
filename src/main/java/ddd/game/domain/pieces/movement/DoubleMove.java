package ddd.game.domain.pieces.movement;

import ddd.game.domain.ChessPieceColor;
import ddd.game.domain.Move;

public record DoubleMove(ChessPieceColor color) implements MovementStrategy {

    @Override
    public boolean supportsMove(Move move) {
        final int diff = Math.abs(move.source().getY() - move.target().getY());
        if (diff > 1) {
            if (ChessPieceColor.WHITE.equals(color)) {
                return move.source().getY() == 2;
            } else {
                return move.source().getY() == 7;
            }
        }
        return true;
    }
}
