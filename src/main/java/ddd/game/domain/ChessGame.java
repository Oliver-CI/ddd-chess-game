package ddd.game.domain;

import ddd.core.Entity;
import ddd.game.domain.pieces.ChessPiece;
import ddd.game.domain.pieces.Pawn;
import ddd.game.rules.ChessPieceIsOnPosition;
import ddd.game.rules.LastMoveMustBeDifferentColor;
import ddd.game.rules.MoveCanBeMade;
import ddd.game.rules.PlayerMustMoveOwnChessPiece;

import java.util.*;
import java.util.stream.Stream;

public class ChessGame extends Entity<ChessGame.Id> {

    private final Map<Position, ChessPiece> board;
    private final List<Move> moves;
    private Player white;
    private Player black;
    public ChessGame(Id id) {
        super(id);
        board = new HashMap<>();
        moves = new LinkedList<>();
    }

    public void loadBoard() {
        Stream.of("a", "b", "c", "d", "e", "f", "g", "h").forEach(s -> board.put(new Position(s + "2"), new Pawn(ChessPieceColor.WHITE)));
        Stream.of("a", "b", "c", "d", "e", "f", "g", "h").forEach(s -> board.put(new Position(s + "7"), new Pawn(ChessPieceColor.BLACK)));
        //TODO: Load more pieces
    }

    public void assignPlayers(Player playerWhite, Player playerBlack) {
        white = playerWhite;
        black = playerBlack;
    }

    public void makeMove(Move move, Player player) {
        final ChessPiece chessPiece = board.get(move.source());

        new ChessPieceIsOnPosition(board, move).checkRule();
        new PlayerMustMoveOwnChessPiece(board, move, white.equals(player)).checkRule();
        new LastMoveMustBeDifferentColor(board, moves, white.equals(player)).checkRule();
        new MoveCanBeMade(board, move).checkRule();

        board.remove(move.source());
        board.put(move.target(), chessPiece);
        moves.add(move);
    }

    public record Id(UUID id) {

    }

    public Map<Position, ChessPiece> getBoard() {
        return board;
    }

    public List<Move> getMoves() {
        return moves;
    }
}
