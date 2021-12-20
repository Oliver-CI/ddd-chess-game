package ddd.game.domain.pieces;

import ddd.game.domain.ChessPieceColor;
import ddd.game.domain.Move;
import ddd.game.domain.pieces.movement.MovementStrategy;

import java.util.List;

public abstract class ChessPiece {

    private final ChessPieceColor chessPieceColor;

    protected ChessPiece(ChessPieceColor chessPieceColor) {
        this.chessPieceColor = chessPieceColor;
    }

    public ChessPieceColor getColor() {
        return chessPieceColor;
    }

    public abstract List<MovementStrategy> getMovementStrategies();

    public abstract List<MovementStrategy> getAttackingStrategies();

    public abstract boolean canAttack(Move move);
}
