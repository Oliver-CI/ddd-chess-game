package ddd.game.domain.pieces.movement;

public enum Range {
    SINGLE(1), DOUBLE(2), UNLIMITED(8);

    private final int value;

    Range(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
