package ddd.game.domain.pieces.movement;

import ddd.game.domain.Move;

public record HorizontalMove(Range range) implements MovementStrategy {

    @Override
    public boolean supportsMove(Move move) {
        final int diff = Math.abs(move.source().getX() - move.target().getX());
        return diff <= range.value();
    }
}
