package ddd.core;

/**
 * <summary>
 * Represents an Entity in the domain (DDD).
 * </summary>
 * <typeparam name="TId">The type of the Id of the entity.</typeparam>
 */
public abstract class Entity<Y> {

    private final Y id;

    public Entity(Y id) {
        this.id = id;
    }

    public Y getId() {
        return id;
    }
}
