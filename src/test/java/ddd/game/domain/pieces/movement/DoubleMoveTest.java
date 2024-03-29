package ddd.game.domain.pieces.movement;

import ddd.game.domain.ChessPieceColor;
import ddd.game.domain.Move;
import ddd.game.domain.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DoubleMoveTest {

    private DoubleMove sut;


    @Test
    void supportsMoveSingleWhite() {
        // Arrange
        sut = new DoubleMove(ChessPieceColor.WHITE);
        var move = new Move(new Position("a2"), new Position("a3"));

        // Act
        assertTrue(sut.supportsMove(move));
    }

    @Test
    void supportsMoveDoubleWhite() {
        // Arrange
        sut = new DoubleMove(ChessPieceColor.WHITE);
        var move = new Move(new Position("a2"), new Position("a4"));

        // Act
        assertTrue(sut.supportsMove(move));
    }

    @Test
    void supportsMoveDoubleBlack() {
        // Arrange
        sut = new DoubleMove(ChessPieceColor.BLACK);
        var move = new Move(new Position("b7"), new Position("b5"));

        // Act
        assertTrue(sut.supportsMove(move));
    }

}
