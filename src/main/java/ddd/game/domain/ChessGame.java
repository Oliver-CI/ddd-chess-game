package ddd.game.domain;

import ddd.core.Entity;
import ddd.game.domain.pieces.ChessPiece;
import ddd.game.domain.pieces.Pawn;
import ddd.game.rules.ChessPieceIsOnPosition;
import ddd.game.rules.LastMoveMustBeDifferentColor;
import ddd.game.rules.PieceCanBeMovedInDirection;
import ddd.game.rules.PlayerMustMoveOwnChessPiece;
import lombok.Getter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

@Getter
public class ChessGame extends Entity<ChessGame.Id> {

    private final Map<Position, ChessPiece> board;
    private final LinkedList<Move> moves;
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

    public void validateMove(Move move, Player player) {
        new ChessPieceIsOnPosition(board, move).throwIfNotSatisfied();
        new PlayerMustMoveOwnChessPiece(board, move, white.equals(player))
                .and(new LastMoveMustBeDifferentColor(board, moves, white.equals(player)))
                .and(new PieceCanBeMovedInDirection(board, move))
                .throwIfNotSatisfied();
    }

    public void makeMove(Move move) {
        final ChessPiece chessPiece = board.get(move.source());
        board.remove(move.source());
        board.put(move.target(), chessPiece);
        moves.add(move);
    }

    public Player getCurrentPlayer() {
        if (moves.isEmpty()) return white;

        final Position target = moves.getLast().target();
        final ChessPieceColor color = board.get(target).getColor();

        return color == ChessPieceColor.WHITE ? black : white;
    }

    public record Id(UUID id) {}

}
