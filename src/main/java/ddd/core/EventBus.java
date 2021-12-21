package ddd.core;

import ddd.game.EventHandlerRegistration;

public interface EventBus {
    /**
     * Publish event to it's subscribers.
     */
    <T extends DomainEvent> void publish(T domainEvent);

    /**
     * Register a subscriber that gets called when a new event is published
     */
    <T extends DomainEvent> void subscribe(EventHandlerRegistration<T> eventHandlerRegistration);
}
