package ddd.game.domain.pieces.movement;

import ddd.game.domain.Move;

public record VerticalMove(Range range, boolean strict) implements MovementStrategy {

    @Override
    public boolean supportsMove(Move move) {
        final int diff = Math.abs(move.source().getY() - move.target().getY());
        boolean allowed = true;
        if (strict) {
            allowed = (Math.abs(move.source().getX() - move.target().getX())) == 0;
        }
        return allowed && diff <= range.value();
    }
}
