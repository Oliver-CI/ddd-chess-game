package ddd.game.events;

import ddd.core.DomainEvent;
import ddd.game.domain.Position;
import ddd.game.domain.pieces.ChessPiece;

import java.util.Map;

public record BoardUpdated(Map<Position, ChessPiece> board) implements DomainEvent {
}
