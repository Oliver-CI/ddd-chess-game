package ddd.game;

import ddd.game.domain.Position;
import ddd.game.domain.pieces.ChessPiece;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

/**
 * ╔═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╗┈╮
 * ║ ♜ │ ♞ │ ♝ │ ♛ │ ♚ │ ♝ │ ♞ │ ♜ ║ 8
 * ╟───┼───┼───┼───┼───┼───┼───┼───╢ ┊
 * ║ ♟ │ ♟ │ ♟ │ ♟ │ ♟ │ ♟ │ ♟ │ ♟ ║ 7
 * ╟───┼───┼───┼───┼───┼───┼───┼───╢ ┊
 * ║ ░ │   │ ░ │   │ ░ │   │ ░ │   ║ 6
 * ╟───┼───┼───┼───┼───┼───┼───┼───╢ ┊
 * ║   │ ░ │   │ ░ │   │ ░ │   │ ░ ║ 5
 * ╟───┼───┼───┼───┼───┼───┼───┼───╢ ┊
 * ║ ░ │   │ ░ │   │ ░ │   │ ░ │   ║ 4
 * ╟───┼───┼───┼───┼───┼───┼───┼───╢ ┊
 * ║   │ ░ │   │ ░ │   │ ░ │   │ ░ ║ 3
 * ╟───┼───┼───┼───┼───┼───┼───┼───╢ ┊
 * ║ ♙ │ ♙ │ ♙ │ ♙ │ ♙ │ ♙ │ ♙ │ ♙ ║ 2
 * ╟───┼───┼───┼───┼───┼───┼───┼───╢ ┊
 * ║ ♖ │ ♘ │ ♗ │ ♕ │ ♔ │ ♗ │ ♘ │ ♖ ║ 1
 * ╚═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╝ ┊
 * ╰┈a┈┈┈b┈┈┈c┈┈┈d┈┈┈e┈┈┈f┈┈┈g┈┈┈h┈┈┈╯
 */
public class BoardPrinter {

    private static final String TOPP_LINE = "╔═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╗\n";
    private static final String SEPARATOR = "╟───┼───┼───┼───┼───┼───┼───┼───╢\n";
    private static final String BOTT_LINE = "╚═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╝\n";

    private static final String RANKS_FORMAT = "║ %s │ %s │ %s │ %s │ %s │ %s │ %s │ %s ║\n";

    private final PrintStream printStream;

    public BoardPrinter() {
        this.printStream = new PrintStream(System.out);
    }

    public void print(Map<Position, ChessPiece> board) {
        final String boardString = getRawString(board);

        PrintWriter printWriter = new PrintWriter(printStream, true, StandardCharsets.UTF_8);
        printWriter.println(boardString);
    }

    private String getRawString(Map<Position, ChessPiece> board) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(TOPP_LINE);
        for (int rank = 8; rank >= 1; rank--) {
            stringBuilder.append(print(rank, board));

            if (rank != 1) {
                stringBuilder.append(SEPARATOR);
            }
        }
        stringBuilder.append(BOTT_LINE);

        return stringBuilder.toString();
    }

    private String print(final int rank, Map<Position, ChessPiece> board) {

        final List<String> stringValues = new ArrayList<>();
        final List<String> chars = List.of("a", "b", "c", "d", "e", "f", "g", "h");
        for (int i = 0; i < chars.size(); i++) {
            var position = new Position(chars.get(i) + rank);
            ChessPiece piece = board.get(position);
            if (nonNull(piece)) {
                stringValues.add("X");
            } else {
                var color = "";
                if (rank % 2 == 0) {
                    color = i % 2 != 0 ? "░" : " ";
                } else {
                    color = i % 2 == 0 ? "░" : " ";
                }
                stringValues.add(color);
            }
        }

        return String.format(RANKS_FORMAT, stringValues.toArray());
    }
}
