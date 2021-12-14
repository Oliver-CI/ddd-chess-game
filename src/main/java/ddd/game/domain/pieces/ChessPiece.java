package ddd.game.domain.pieces;

import ddd.game.domain.ChessPieceColor;

public abstract class ChessPiece {

    private final ChessPieceColor chessPieceColor;

    protected ChessPiece(ChessPieceColor chessPieceColor) {
        this.chessPieceColor = chessPieceColor;
    }

    public ChessPieceColor getColor() {
        return chessPieceColor;
    }

}
