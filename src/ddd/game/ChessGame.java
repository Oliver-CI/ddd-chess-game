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
import java.util.List;
import java.util.UUID;

public class ChessGame extends AggregateRoot<UUID> {

    private Player white;
    private Player black;
    private final List<Move> moves;


    public ChessGame(UUID id) {
        super(id);
        moves = new ArrayList<>();
    }

    public void when(DomainEvent domainEvent) {
        switch (domainEvent) {
            case GameStarted gameStarted:
                setPlayer(gameStarted);
                break;
            case TurnAssigned turnAssigned:
                setActivePlayer(turnAssigned);
                break;

            case MoveMade moveMade:
                switchPlayer(moveMade);
                break;
            default:
                throw new UnsupportedOperationException();
        }

    }

    private void switchPlayer(MoveMade moveMade) {

    }

    private void setActivePlayer(TurnAssigned turnAssigned) {
        //notify Player
        System.out.printf("It is the turn of %s", turnAssigned.getCurrentPlayer());
    }

    private void setPlayer(GameStarted gameStarted) {
        white = gameStarted.getPlayerWhite();
        black = gameStarted.getPlayerBlack();
    }

    public void startGame(StartGame startGame) {
        raiseEvent(new GameStarted(startGame.getPlayerId1(), startGame.getPlayerId2()));
        raiseEvent(new TurnAssigned(startGame.getPlayerId1()));
    }

    public boolean makeMove(MakeMove makeMove) {
        //todo validation player
        final UUID currentPlayer = makeMove.getCurrentPlayer();

        //todo validation move
        final Move move = makeMove.getMove();

        moves.add(move);
        raiseEvent(new MoveMade(move, currentPlayer));
        return true;
    }
}