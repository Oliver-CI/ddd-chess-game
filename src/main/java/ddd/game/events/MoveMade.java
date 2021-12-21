package ddd.game.events;

import ddd.game.domain.Move;
import ddd.game.domain.Player;

public record MoveMade(Move move, Player currentPlayer) implements ddd.core.DomainEvent {
}
