package ddd.game.domain.pieces.movement;

import ddd.game.domain.ChessPieceColor;
import ddd.game.domain.Move;

public record EnPassantMove(ChessPieceColor color) implements MovementStrategy {

    @Override
    public boolean supportsMove(Move move) {
        final int targetY = move.target().getY();
        return move.source().getY() == 2 && (targetY == 3 || targetY == 4);
    }
}
