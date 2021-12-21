package ddd.game.commands;

import ddd.core.DomainEvent;
import ddd.game.ChessGameAggregate;
import ddd.game.domain.ChessGame;
import ddd.game.repositories.ChessGameRepository;

import java.util.List;

public class CommandHandler {

    private final ChessGameRepository repository;

    public CommandHandler(ChessGameRepository repository) {
        this.repository = repository;
    }

    public ChessGame.Id executeCommand(StartGame command) {
        final ChessGameAggregate chessGameAggregate = new ChessGameAggregate();
        chessGameAggregate.startGame(command);

        chessGameAggregate.getEvents().forEach(event -> repository.save(chessGameAggregate.getChessGame().getId(), event));

        return chessGameAggregate.getId();
    }

    public void executeCommand(MakeMove command) {
        final List<DomainEvent> domainEvents = repository.findById(command.chessGameId());

        final ChessGameAggregate chessGameAggregate = new ChessGameAggregate(command.chessGameId(), domainEvents);

        chessGameAggregate.makeMove(command);
        chessGameAggregate.getEvents().forEach(event -> repository.save(chessGameAggregate.getChessGame().getId(), event));
    }
}
