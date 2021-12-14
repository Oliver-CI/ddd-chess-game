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
        //todo create aggreate
        var chessGame = new ChessGame(new ChessGame.Id(UUID.randomUUID()));
        final ChessEngine chessEngine = new ChessEngine(chessGame.getId());

        //todo execute command on aggreate
        chessEngine.startGame(command);

        repository.save(chessGame);

        return chessGame.getId();
    }

    public boolean executeCommand(MakeMove command) {
        //todo create aggreate
        final ChessGame chessGame = repository.findById(command.chessGameId().id());
        final ChessEngine chessEngine = new ChessEngine(chessGame.getId());

        //todo execute command on aggreate
        return chessEngine.makeMove(command);
    }
}
