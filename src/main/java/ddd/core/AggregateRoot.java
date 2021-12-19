package ddd.core;

import java.util.ArrayList;
import java.util.List;

/**
 * <summary>
 * Represents an aggregate-root of a domain aggregate (DDD). An aggregate-root is always an entity.
 * </summary>
 * <typeparam name="TId">The type of the Id of the entity.</typeparam>
 */
public abstract class AggregateRoot<I> extends Entity<I> {
    /**
     * <summary>
     * The list of events that occurred while handling commands.
     * </summary>
     */
    private final ArrayList<DomainEvent> events;
    /**
     * <summary>
     * Indication whether the aggregate is replaying events (true) or not (false).
     * </summary>
     */
    private boolean isReplaying = false;
    /**
     * <summary>
     * The current version after handling any commands.
     * </summary>
     */
    private int version;
    /**
     * <summary>
     * The original version of the aggregate after replaying all events in the event-store.
     * </summary>
     */
    private int originalVersion;

    /**
     * <summary>
     * Constructor for creating an empty aggregate.
     * </summary>
     * <param name="id">The unique id of the aggregate-root.</param>
     */
    public AggregateRoot(I id) {
        super(id);
        originalVersion = 0;
        version = 0;
        events = new ArrayList<>();
    }

    /**
     * <summary>
     * Constructor for creating an aggregate of which the state is intialized by
     * replaying the list of events specified.
     * </summary>
     * <param name="id">The unique Id of the aggregate.</param>
     * <param name="events">The events to replay.</param>
     */
    public AggregateRoot(I id, List<DomainEvent> events) {
        this(id);
        isReplaying = true;
        for (DomainEvent evt : events) {
            when(evt);
            originalVersion++;
            version++;
        }
        isReplaying = false;
    }

    public List<DomainEvent> getEvents() {
        return events;
    }

    protected boolean isRelaying() {
        return isReplaying;
    }

    public int getVersion() {
        return version;
    }

    public int getOriginalVersion() {
        return originalVersion;
    }

    /**
     * <summary>
     * Let the aggregate handle an event and save it in the list of events
     * so it can be used outside the aggregate (persisted, published on a bus, ...).
     * </summary>
     * <param name="domainEevent">The event to handle.</param>
     * <remarks>Use GetEvents to retrieve the list of events.</remarks>
     */
    protected void raiseEvent(DomainEvent domainEvent) {
        // let the derived aggregate handle the event
        when(domainEvent);

        // save the event so it can be published outside the aggregate
        events.add(domainEvent);
        version += 1;
    }

    /**
     * <summary>
     * Clear the list of events that occurred while handling a command.
     * </summary>
     */
    public void clearEvents() {
        events.clear();
    }

    /**
     * <summary>
     * Handle a specific event. Derived classes should  implement this method
     * for every event type.
     * </summary>
     * <param name="domainEevent">The event to handle.</param>
     * <remarks>Because the parameter type of the specified event is dynamic,
     * the appropriate overload of the When method is called.</remarks>
     */
    protected abstract void when(DomainEvent domainEvent);
}
