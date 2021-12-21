package ddd.core;

public interface EventBus {
    /**
     * Publish event to it's subscribers.
     *
     * @param domainEvent
     */
    <T extends DomainEvent> void publish(T domainEvent);

    /**
     * Register a subscriber that gets called when a new event is published
     *
     * @param eventListener
     * @param <T>
     */
    <T extends DomainEvent> void subscribe(EventHandler<T> eventListener);
}
