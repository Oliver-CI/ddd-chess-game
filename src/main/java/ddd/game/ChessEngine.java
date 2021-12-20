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

public class ChessEngine extends AggregateRoot<ChessGame.Id> {

    private final ChessGame chessGame;
    private final BoardPrinter printer;

    public ChessEngine(ChessGame chessGame) {
        super(chessGame.getId());
        this.chessGame = chessGame;
        this.printer = new BoardPrinter();
    }

    public ChessGame getChessGame() {
        return chessGame;
    }

    // region domain events
    public void when(DomainEvent domainEvent) {
        switch (domainEvent) {
            case GameStarted gameStarted -> setPlayer(gameStarted);
            case TurnAssigned turnAssigned -> setActivePlayer(turnAssigned);
            case MoveMade moveMade -> {
                switchPlayer(moveMade);
                printBoard();
            }
            default -> throw new UnsupportedOperationException();
        }

    }

    private void printBoard() {
        printer.print(chessGame.getBoard());
    }

    private void switchPlayer(MoveMade moveMade) {
        final Player player = chessGame.switchCurrentPlayer(moveMade.getCurrentPlayer());
        raiseEvent(new TurnAssigned(player));
    }

    private void setActivePlayer(TurnAssigned turnAssigned) {
        //notify Player
        System.out.println("It is the turn of " + turnAssigned.getCurrentPlayer());
    }

    private void setPlayer(GameStarted gameStarted) {
        chessGame.assignPlayers(gameStarted.getPlayerWhite(), gameStarted.getPlayerBlack());
    }

    // endregion

    // region command
    public void startGame(StartGame startGame) {
        chessGame.loadBoard();
        raiseEvent(new GameStarted(startGame.playerId1(), startGame.playerId2()));
        raiseEvent(new TurnAssigned(startGame.playerId1()));
    }

    public boolean makeMove(MakeMove makeMove) {
        final Player currentPlayer = makeMove.currentPlayer();
        final Move move = makeMove.move();
        chessGame.makeMove(move, currentPlayer);
        raiseEvent(new MoveMade(move, currentPlayer));
        return true;
    }
    // endregion
}
