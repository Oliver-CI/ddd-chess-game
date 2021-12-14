package ddd.game;

import ddd.core.ValueObject;

import java.util.Objects;

public class Position extends ValueObject {

    private final String value;

    public Position(String value) {
        this.value = value;
    }

    @Override
    protected Object[] getAtomicValues() {
        return new Object[] { value };
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
}
