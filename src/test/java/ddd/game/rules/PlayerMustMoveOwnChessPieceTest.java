package ddd.game.rules;

import ddd.game.domain.ChessGame;
import ddd.game.domain.Move;
import ddd.game.domain.Player;
import ddd.game.domain.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class PlayerMustMoveOwnChessPieceTest {

    private static final UUID white = UUID.randomUUID();
    private static final UUID black = UUID.randomUUID();
    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(new ChessGame.Id(white));
        chessGame.loadBoard();
        chessGame.assignPlayers(new Player(new Player.Id(white)), new Player(new Player.Id(black)));
    }

    @Test
    void checkRule() {
        // Arrange
        var move = new Move(new Position("a2"), new Position("a3"));
        // Act
        var rule = new PlayerMustMoveOwnChessPiece(chessGame.getBoard(), move, true);

        // Assert
        rule.checkRule();
    }
}
