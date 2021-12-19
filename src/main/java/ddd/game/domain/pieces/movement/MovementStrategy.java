package ddd.game.domain.pieces.movement;

import ddd.game.domain.Move;

public interface MovementStrategy {

    boolean supportsMove(Move move);
}
