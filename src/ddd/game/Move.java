package ddd.game;

import ddd.core.ValueObject;

public class Move extends ValueObject {

    final Color color;
    final Piece piece;
    final Position start;
    final Position end;

    public Move(Color color, Piece piece, Position start, Position end) {
        this.color = color;
        this.piece = piece;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Object[] getAtomicValues() {
        return new Object[]{color, piece, start, end};
    }
}
