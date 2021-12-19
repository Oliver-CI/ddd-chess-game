package ddd.game.commands;

import ddd.game.ChessEngine;
import ddd.game.domain.ChessGame;
import ddd.game.repositories.ChessGameRepository;

import java.util.UUID;

public class CommandHandler {

    private final ChessGameRepository repository;

    public CommandHandler(ChessGameRepository repository) {
        this.repository = repository;
    }

    public ChessGame.Id executeCommand(StartGame command) {
        final ChessGame chessGame = new ChessGame(new ChessGame.Id(UUID.randomUUID()));
        final ChessEngine chessEngine = new ChessEngine(chessGame);

        chessEngine.startGame(command);

        repository.save(chessEngine.getChessGame());

        return chessGame.getId();
    }

    public boolean executeCommand(MakeMove command) {
        final ChessGame chessGame = repository.findById(command.chessGameId().id());
        final ChessEngine chessEngine = new ChessEngine(chessGame);

        return chessEngine.makeMove(command);
    }
}
