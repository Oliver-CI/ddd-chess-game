package ddd.game.commands;

import ddd.core.DomainEvent;
import ddd.game.ChessEngine;
import ddd.game.domain.ChessGame;
import ddd.game.repositories.ChessGameRepository;

import java.util.List;
import java.util.UUID;

public class CommandHandler {

    private final ChessGameRepository repository;

    public CommandHandler(ChessGameRepository repository) {
        this.repository = repository;
    }

    public ChessGame.Id executeCommand(StartGame command) {
        final ChessGame.Id id = new ChessGame.Id(UUID.randomUUID());
        final ChessEngine chessEngine = new ChessEngine(id);
        chessEngine.startGame(command);

        chessEngine.getEvents().forEach(event -> repository.save(chessEngine.getChessGame().getId(), event));

        return id;
    }

    public void executeCommand(MakeMove command) {
        final List<DomainEvent> domainEvents = repository.findById(command.chessGameId());

        final ChessEngine chessEngine = new ChessEngine(command.chessGameId(), domainEvents);

        chessEngine.makeMove(command);
        chessEngine.getEvents().forEach(event -> repository.save(chessEngine.getChessGame().getId(), event));
    }
}
