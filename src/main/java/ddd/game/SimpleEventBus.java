package ddd.game;

import ddd.core.DomainEvent;
import ddd.core.EventBus;
import ddd.core.EventHandler;
import ddd.game.events.BoardUpdated;

import java.util.ArrayList;
import java.util.List;

public class SimpleEventBus implements EventBus {

    private final List<EventHandler> handlerList;

    public SimpleEventBus() {
        handlerList = new ArrayList<>();
    }

    @Override
    public <T extends DomainEvent> void publish(T domainEvent) {
        //todo: fix generics
        if(domainEvent instanceof BoardUpdated){
            handlerList.forEach(h -> h.handle(domainEvent));
        }
    }

    @Override
    public <T extends DomainEvent> void subscribe(EventHandler<T> eventListener) {
        handlerList.add(eventListener);
    }
}
