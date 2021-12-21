package ddd.game.repositories;

import ddd.core.DomainEvent;
import ddd.game.domain.ChessGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGameRepository {

    private final Map<ChessGame.Id, List<DomainEvent>> chessGames;

    public ChessGameRepository() {
        this.chessGames = new HashMap<>();
    }

    public void save(ChessGame.Id chessGameId, DomainEvent domainEvent) {
        chessGames.computeIfAbsent(chessGameId, k -> new ArrayList<>());
        chessGames.get(chessGameId).add(domainEvent);
    }

    public List<DomainEvent> findById(ChessGame.Id chessGameId) {
        return chessGames.get(chessGameId);
    }
}
