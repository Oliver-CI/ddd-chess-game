package ddd.game.commands;

import ddd.game.domain.ChessGame;
import ddd.game.repositories.ChessGameRepository;

import java.util.UUID;

public class CommandHandler {

    private final ChessGameRepository repository;

    public CommandHandler(ChessGameRepository repository) {
        this.repository = repository;
    }

    public UUID executeCommand(StartGame command) {
        //todo create aggreate
        var chessGame = new ChessGame(UUID.randomUUID());

        //todo execute command on aggreate
        chessGame.startGame(command);

        repository.save(chessGame);

        return chessGame.getId();
    }

    public boolean executeCommand(MakeMove command) {
        //todo create aggreate
        final ChessGame chessGame = repository.findById(command.chessGameId());

        //todo execute command on aggreate
        return chessGame.makeMove(command);
    }
}
