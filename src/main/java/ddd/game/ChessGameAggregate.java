package ddd.game;

import ddd.core.AggregateRoot;
import ddd.core.DomainEvent;
import ddd.game.commands.MakeMove;
import ddd.game.commands.StartGame;
import ddd.game.domain.ChessGame;
import ddd.game.domain.Move;
import ddd.game.domain.Player;
import ddd.game.events.BoardUpdated;
import ddd.game.events.GameStarted;
import ddd.game.events.MoveMade;
import ddd.game.events.TurnAssigned;

import java.util.List;
import java.util.UUID;

public class ChessGameAggregate extends AggregateRoot<ChessGame.Id> {

    private ChessGame chessGame;

    public ChessGameAggregate() {
        super(new ChessGame.Id(UUID.randomUUID()));
    }

    public ChessGameAggregate(ChessGame.Id chessGameId, List<DomainEvent> domainEvents) {
        super(chessGameId, domainEvents);
    }

    public ChessGame getChessGame() {
        return chessGame;
    }

    // region domain events
    @Override
    public void when(DomainEvent domainEvent) {
        switch (domainEvent) {
            case GameStarted gameStarted -> handleGameStarted(gameStarted);
            case TurnAssigned turnAssigned -> setActivePlayer(turnAssigned);
            case MoveMade moveMade -> chessGame.makeMove(moveMade.move());
            case BoardUpdated boardUpdated -> {}
            default -> throw new UnsupportedOperationException();
        }

    }

    private void setActivePlayer(TurnAssigned ignored) {
        //notify Player
        if (!isReplaying()) {
            var color = chessGame.getCurrentPlayer() == chessGame.getWhite() ? "white" : "black";
            System.out.println("It is the turn of " + color);
        }
    }

    private void handleGameStarted(GameStarted gameStarted) {
        chessGame = new ChessGame(this.getId());
        chessGame.loadBoard();
        chessGame.assignPlayers(gameStarted.getPlayerWhite(), gameStarted.getPlayerBlack());
    }

    // endregion

    // region command
    public void startGame(StartGame startGame) {
        raiseEvent(new GameStarted(startGame.playerId1(), startGame.playerId2()));
        raiseEvent(new TurnAssigned());
        raiseEvent(new BoardUpdated(chessGame.getBoard()));
    }

    public void makeMove(MakeMove makeMove) {
        final Player currentPlayer = makeMove.currentPlayer();
        final Move move = makeMove.move();
        chessGame.validateMove(move, currentPlayer);
        raiseEvent(new MoveMade(move, currentPlayer));
        raiseEvent(new TurnAssigned());
        raiseEvent(new BoardUpdated(chessGame.getBoard()));
    }
    // endregion
}
