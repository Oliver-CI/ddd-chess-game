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

import java.util.List;

public class ChessEngine extends AggregateRoot<ChessGame.Id> {

    private ChessGame chessGame;
    private final BoardPrinter printer = new BoardPrinter();

    public ChessEngine(ChessGame.Id chessGameId) {
        super(chessGameId);
    }

    public ChessEngine(ChessGame.Id chessGameId, List<DomainEvent> domainEvents) {
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
            case MoveMade moveMade -> {
                chessGame.makeMove(moveMade.getMove());
                printBoard();
            }
            default -> throw new UnsupportedOperationException();
        }

    }

    private void printBoard() {
        if (!isReplaying()) {
            printer.print(chessGame.getBoard());
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
    }

    public void makeMove(MakeMove makeMove) {
        final Player currentPlayer = makeMove.currentPlayer();
        final Move move = makeMove.move();
        chessGame.validateMove(move, currentPlayer);
        raiseEvent(new MoveMade(move, currentPlayer));
        raiseEvent(new TurnAssigned());
    }
    // endregion
}
