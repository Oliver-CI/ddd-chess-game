package ddd.game.rules;

import ddd.core.businessrules.BusinessRule;
import ddd.core.businessrules.BusinessRuleViolation;
import ddd.game.domain.Move;
import ddd.game.domain.Position;
import ddd.game.domain.pieces.ChessPiece;
import ddd.game.domain.pieces.movement.MovementStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class PieceCanBeMovedInDirection extends BusinessRule {
    private final Map<Position, ChessPiece> board;
    private final Move move;

    public PieceCanBeMovedInDirection(Map<Position, ChessPiece> board, Move move) {
        this.board = board;
        this.move = move;
    }

    @Override
    public List<BusinessRuleViolation> checkRule() {
        final ChessPiece movingChessPiece = board.get(move.source());
        final ChessPiece targetChessPiece = board.get(move.target());

        //TODO: maybe split in different rules?
        if (nonNull(targetChessPiece) && !targetChessPiece.getColor().equals(movingChessPiece.getColor())) {
            final Map<Boolean, List<MovementStrategy>> map = movingChessPiece.getAttackingStrategies().stream().collect(Collectors.groupingBy(strategy -> strategy.supportsMove(move)));
            final List<BusinessRuleViolation> businessRuleViolations = checkStrategies(map);
            if (!movingChessPiece.canAttack(move)) {
                businessRuleViolations.add(new BusinessRuleViolation("Piece cannot be attack piece due to invalid move"));
            }
            return businessRuleViolations;
        } else if (nonNull(targetChessPiece) && targetChessPiece.getColor().equals(movingChessPiece.getColor())) {
            return List.of(new BusinessRuleViolation("Piece cannot be moved as another piece blocks the position"));
        } else {
            final Map<Boolean, List<MovementStrategy>> map = movingChessPiece.getMovementStrategies().stream().collect(Collectors.groupingBy(strategy -> strategy.supportsMove(move)));
            return checkStrategies(map);
        }
    }

    private List<BusinessRuleViolation> checkStrategies(Map<Boolean, List<MovementStrategy>> strategyMap) {
        final List<MovementStrategy> failedStrategies = strategyMap.get(Boolean.FALSE);
        if (nonNull(failedStrategies) && !failedStrategies.isEmpty()) {
            final String failure = failedStrategies.stream().map(o -> o.getClass().getSimpleName()).collect(Collectors.joining(","));
            return new ArrayList<>(List.of(new BusinessRuleViolation("Move cannot be made due to: " + failure)));
        }

        return List.of();
    }
}
