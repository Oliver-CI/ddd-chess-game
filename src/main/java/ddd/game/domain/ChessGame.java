package ddd.game.domain;

import ddd.core.Entity;
import ddd.game.domain.pieces.ChessPiece;
import ddd.game.rules.ChessPieceIsOnPosition;
import ddd.game.rules.LastMoveMustBeDifferentColor;
import ddd.game.rules.PlayerMustMoveOwnChessPiece;

import java.util.HashMap;
import java.util.LinkedList;
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
        moves = new LinkedList<>();
    }

    public void makeMove(Move move, Player player) {
        final ChessPiece chessPiece = board.get(move.source());

        new ChessPieceIsOnPosition(board, move).checkRule();
        new PlayerMustMoveOwnChessPiece(board, move, white.equals(player)).checkRule();
        new LastMoveMustBeDifferentColor(board, moves, white.equals(player)).checkRule();

        board.remove(move.source());
        board.put(move.target(), chessPiece);
        moves.add(move);
    }

}
