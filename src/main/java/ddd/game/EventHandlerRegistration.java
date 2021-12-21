package ddd.game;

import ddd.core.DomainEvent;
import ddd.core.EventHandler;

public record EventHandlerRegistration<T extends DomainEvent>(Class<T> domainEventClass, EventHandler<T> eventHandler){}
