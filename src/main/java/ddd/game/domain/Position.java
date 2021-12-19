package ddd.game.domain;

import ddd.core.ValueObject;

import java.util.Objects;

public class Position extends ValueObject {

    private static final String REGEX = "[a-h][1-8]";
    private final String value;
    private final int xValue;
    private final int yValue;

    public Position(String value) {
        if (value.matches(REGEX)) {
            this.value = value;
            this.xValue = getValueFromCharacter(value.substring(0, 1));
            this.yValue = Integer.parseInt(value.substring(1));
        } else throw new IllegalArgumentException("Position is not valid");

    }

    private int getValueFromCharacter(String character) {
        return switch (character) {
            case "a" -> 1;
            case "b" -> 2;
            case "c" -> 3;
            case "d" -> 4;
            case "e" -> 5;
            case "f" -> 6;
            case "g" -> 7;
            case "h" -> 8;
            default -> 0;
        };
    }

    @Override
    protected Object[] getAtomicValues() {
        return new Object[]{value};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Position position = (Position) o;
        return Objects.equals(value, position.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }

    public int getX() {
        return xValue;
    }

    public int getY() {
        return yValue;
    }
}
