package ddd.game.domain.pieces.movement;

import ddd.game.domain.ChessPieceColor;
import ddd.game.domain.Move;
import ddd.game.domain.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EnPassantMoveTest {

    private EnPassantMove sut;


    @Test
    void supportsMoveSingleWhite() {
        // Arrange
        sut = new EnPassantMove(ChessPieceColor.WHITE);
        var move = new Move(new Position("a2"), new Position("a3"));

        // Act
        assertTrue(sut.supportsMove(move));
    }

}
