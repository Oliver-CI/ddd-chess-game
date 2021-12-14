package ddd.game;

import ddd.core.AggregateRoot;
import ddd.core.DomainEvent;
import ddd.game.command.MakeMove;
import ddd.game.command.StartGame;
import ddd.game.domainevents.GameStarted;
import ddd.game.domainevents.MoveMade;
import ddd.game.domainevents.TurnAssigned;
import ddd.game.valueobject.Move;
import ddd.game.valueobject.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class ChessGame extends AggregateRoot<UUID> {

    private Player white;
    private Player black;
    private final List<Move> moves;


    public ChessGame(UUID id) {
        super(id);
        moves = new ArrayList<>();
    }

    // region domain events
    public void when(DomainEvent domainEvent) {
        switch (domainEvent) {
            case GameStarted gameStarted -> setPlayer(gameStarted);
            case TurnAssigned turnAssigned -> setActivePlayer(turnAssigned);
            case MoveMade moveMade -> switchPlayer(moveMade);
            default -> throw new UnsupportedOperationException();
        }

    }

    private void switchPlayer(MoveMade moveMade) {
        final Player player = moveMade.getCurrentPlayer() == white ? black : white;
        raiseEvent(new TurnAssigned(player));
    }

    private void setActivePlayer(TurnAssigned turnAssigned) {
        //notify Player
        System.out.printf("It is the turn of %s", turnAssigned.getCurrentPlayer());
    }

    private void setPlayer(GameStarted gameStarted) {
        white = gameStarted.getPlayerWhite();
        black = gameStarted.getPlayerBlack();
    }

    // endregion

    // region command
    public void startGame(StartGame startGame) {
        raiseEvent(new GameStarted(startGame.playerId1(), startGame.playerId2()));
        raiseEvent(new TurnAssigned(startGame.playerId1()));
    }

    public boolean makeMove(MakeMove makeMove) {
        //todo validation player
        final Player currentPlayer = makeMove.currentPlayer();
        final boolean b = Arrays.asList(white, black).contains(currentPlayer);

        //todo validation move
        final Move move = makeMove.move();

        moves.add(move);
        raiseEvent(new MoveMade(move, currentPlayer));
        return true;
    }
    // endregion
}
