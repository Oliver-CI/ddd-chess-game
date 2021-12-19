package ddd.game.domain.pieces.movement;

import ddd.game.domain.Move;
import ddd.game.domain.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HorizontalMoveTest {

    private HorizontalMove sut;

    @Test
    void supportsMoveSingle() {
        // Arrange
        sut = new HorizontalMove(Range.SINGLE);
        var move = new Move(new Position("a1"), new Position("b2"));

        // Act
        assertTrue(sut.supportsMove(move));
    }

    @Test
    void supportsMoveUnlimited() {
        // Arrange
        sut = new HorizontalMove(Range.UNLIMITED);

        var move = new Move(new Position("a1"), new Position("h8"));

        // Act
        assertTrue(sut.supportsMove(move));
    }
}
