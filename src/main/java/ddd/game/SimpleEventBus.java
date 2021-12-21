package ddd.game;

import ddd.core.DomainEvent;
import ddd.core.EventBus;
import ddd.core.EventHandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SimpleEventBus implements EventBus {

    private final Map<Class<? extends DomainEvent>, Set<? extends EventHandler<? extends DomainEvent>>> registry;

    public SimpleEventBus() {
        registry = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends DomainEvent> void publish(T domainEvent) {
        var eventHandlers = (Set<EventHandler<T>>) registry.getOrDefault(domainEvent.getClass(), Set.of());
        eventHandlers.forEach(eventHandler -> eventHandler.handle(domainEvent));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends DomainEvent> void subscribe(EventHandlerRegistration<T> registration) {
        var eventHandlers = (Set<EventHandler<T>>) registry.computeIfAbsent(registration.domainEventClass(), x -> new HashSet<>());
        eventHandlers.add(registration.eventHandler());
    }
}
