package ddd.game.domain.pieces.movement;

import ddd.game.domain.Move;

public record VerticalMove(Range range) implements MovementStrategy {

    @Override
    public boolean supportsMove(Move move) {
        final int diff = Math.abs(move.source().getY() - move.target().getY());
        return diff <= range.getValue();
    }
}
