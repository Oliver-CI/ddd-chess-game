package ddd.game;

import ddd.core.AggregateRoot;
import ddd.core.DomainEvent;
import ddd.game.commands.MakeMove;
import ddd.game.commands.StartGame;
import ddd.game.domain.ChessGame;
import ddd.game.domain.Move;
import ddd.game.domain.Player;
import ddd.game.events.GameStarted;
import ddd.game.events.MoveMade;
import ddd.game.events.TurnAssigned;

import java.util.Arrays;

public class ChessEngine extends AggregateRoot<ChessGame.Id> {

    private ChessGame chessGame;

    public ChessEngine(ChessGame.Id id) {
        super(id);
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
//        final Player player = moveMade.getCurrentPlayer() == white ? black : white;
//        raiseEvent(new TurnAssigned(player));
        throw new UnsupportedOperationException();
    }

    private void setActivePlayer(TurnAssigned turnAssigned) {
        //notify Player
        System.out.printf("It is the turn of %s", turnAssigned.getCurrentPlayer());
    }

    private void setPlayer(GameStarted gameStarted) {
//        white = gameStarted.getPlayerWhite();
//        black = gameStarted.getPlayerBlack();
        throw new UnsupportedOperationException();
    }

    // endregion

    // region command
    public void startGame(StartGame startGame) {
        raiseEvent(new GameStarted(startGame.playerId1(), startGame.playerId2()));
        raiseEvent(new TurnAssigned(startGame.playerId1()));
    }

    public boolean makeMove(MakeMove makeMove) {
//        //todo validation player
//        final Player currentPlayer = makeMove.currentPlayer();
//        final boolean b = Arrays.asList(white, black).contains(currentPlayer);
//
//        //todo validation move
//        final Move move = makeMove.move();
//
//        moves.add(move);
//        raiseEvent(new MoveMade(move, currentPlayer));
//        return true;
        throw new UnsupportedOperationException();
    }
    // endregion
}
