package ddd.game.domain;

import ddd.core.Entity;
import ddd.game.domain.pieces.ChessPiece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ChessGame extends Entity<ChessGame.Id> {

    public record Id(UUID id) {}

    private Player white;
    private Player black;
    private Map<Position, ChessPiece> board;
    private final List<Move> moves;

    public ChessGame(Id id) {
        super(id);
        board = new HashMap<>();
        moves = new ArrayList<>();
    }

}
