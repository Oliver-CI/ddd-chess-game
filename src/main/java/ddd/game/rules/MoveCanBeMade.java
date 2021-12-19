package ddd.game.rules;

import ddd.game.domain.Move;
import ddd.game.domain.Position;
import ddd.game.domain.pieces.ChessPiece;
import ddd.game.domain.pieces.movement.MovementStrategy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class MoveCanBeMade {
    private final ChessPiece movingPiece;
    private final Move move;

    public MoveCanBeMade(Map<Position, ChessPiece> board, Move move) {
        this.movingPiece = board.get(move.source());
        this.move = move;
    }

    public void checkRule() {
        final Map<Boolean, List<MovementStrategy>> map = movingPiece.getMovementStrategies().stream().collect(Collectors.groupingBy(strategy -> strategy.supportsMove(move)));
        final List<MovementStrategy> failedStrategies = map.get(Boolean.FALSE);
        if (nonNull(failedStrategies) && !failedStrategies.isEmpty()) {
            final String failure = failedStrategies.stream().map(o -> o.getClass().getSimpleName()).collect(Collectors.joining(","));
            throw new IllegalArgumentException("Move cannot be made due to: +" + failure);
        }
    }
}
