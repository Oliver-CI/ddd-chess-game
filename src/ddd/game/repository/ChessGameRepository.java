package ddd.game.repository;

import ddd.game.ChessGame;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChessGameRepository {

    private Map<UUID, ChessGame> chessGames;

    public ChessGameRepository() {
        this.chessGames = new HashMap<>();
    }

    public void save(ChessGame chessGame) {
        chessGames.putIfAbsent(chessGame.getId(), chessGame);
    }

    public ChessGame findById(UUID chessGameId) {
        return chessGames.get(chessGameId);
    }
}
