package ddd.game.rules;

import ddd.game.domain.ChessPieceColor;
import ddd.game.domain.Move;
import ddd.game.domain.Position;
import ddd.game.domain.pieces.ChessPiece;

import java.util.Map;

public class PlayerMustMoveOwnChessPiece {

    private final Map<Position, ChessPiece> board;
    private final Move move;
    private final boolean isWhite;

    public PlayerMustMoveOwnChessPiece(Map<Position, ChessPiece> board, Move move, boolean isWhite) {
        this.board = board;
        this.move = move;
        this.isWhite = isWhite;
    }

    public void checkRule() {
        final ChessPiece chessPiece = board.get(move.source());
        final ChessPieceColor chessPieceColor = chessPiece.getColor();
        if (isWhite && chessPieceColor != ChessPieceColor.WHITE || !isWhite && chessPieceColor != ChessPieceColor.BLACK) {
            throw new IllegalArgumentException("Piece is not of the current player");
        }
    }
}
