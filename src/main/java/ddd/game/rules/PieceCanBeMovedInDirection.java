package ddd.game.rules;

import ddd.game.domain.Move;
import ddd.game.domain.Position;
import ddd.game.domain.pieces.ChessPiece;
import ddd.game.domain.pieces.movement.MovementStrategy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class PieceCanBeMovedInDirection {
    private final Map<Position, ChessPiece> board;
    private final Move move;

    public PieceCanBeMovedInDirection(Map<Position, ChessPiece> board, Move move) {
        this.board = board;
        this.move = move;
    }

    public void checkRule() {
        var movingPiece = board.get(move.source());
        var target = board.get(move.target());

        //TODO: maybe split in different rules?
        if (nonNull(target) && !target.getColor().equals(movingPiece.getColor())) {
            final Map<Boolean, List<MovementStrategy>> map = movingPiece.getAttackingStrategies().stream().collect(Collectors.groupingBy(strategy -> strategy.supportsMove(move)));
            checkStrategies(map);
            if (!movingPiece.canAttack(move)) {
                throw new IllegalArgumentException("Piece cannot be attack piece due to invalid move");
            }
        } else if (nonNull(target) && target.getColor().equals(movingPiece.getColor())) {
            throw new IllegalArgumentException("Piece cannot be moved as another piece blocks the position");
        } else {
            final Map<Boolean, List<MovementStrategy>> map = movingPiece.getMovementStrategies().stream().collect(Collectors.groupingBy(strategy -> strategy.supportsMove(move)));
            checkStrategies(map);
        }
    }

    private void checkStrategies(Map<Boolean, List<MovementStrategy>> strategyMap) {
        final List<MovementStrategy> failedStrategies = strategyMap.get(Boolean.FALSE);
        if (nonNull(failedStrategies) && !failedStrategies.isEmpty()) {
            final String failure = failedStrategies.stream().map(o -> o.getClass().getSimpleName()).collect(Collectors.joining(","));
            throw new IllegalArgumentException("Move cannot be made due to: " + failure);
        }
    }
}
