package ddd.core;

/**
 * <summary>
 * Represents a ValueObject in the domain (DDD).
 * </summary>
 */
public abstract class ValueObject {
    private static boolean areEqual(ValueObject left, ValueObject right) {
        if (left == null)
            return right == null;
        else if (right == null)
            return false;
        else {
            Object[] leftValues = left.getAtomicValues();
            Object[] rightValues = right.getAtomicValues();

            boolean result = true;
            for (int i = 0; i < leftValues.length; i++) {
                result = result && leftValues[i].equals(rightValues[i]);
            }
            return result;
        }
    }

    protected abstract Object[] getAtomicValues();

    public boolean equals(Object obj) {
        return obj != null &&
                this.getClass().equals(obj.getClass()) &&
                areEqual(this, (ValueObject) obj);
    }

    public int hashCode() {
        int result = 0;
        for (Object value : getAtomicValues()) {
            if (value != null) {
                result = result ^ value.hashCode();
            }
        }
        return result;
    }

}
