package ddd;

import ddd.game.command.CommandHandler;
import ddd.game.command.MakeMove;
import ddd.game.command.StartGame;
import ddd.game.repository.ChessGameRepository;
import ddd.game.valueobject.Move;
import ddd.game.valueobject.Piece;
import ddd.game.valueobject.Player;
import ddd.game.valueobject.Position;

import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        // write your code here
        final CommandHandler commandHandler = new CommandHandler(new ChessGameRepository());
        final UUID gameInvite = UUID.randomUUID();
        final UUID player1 = UUID.randomUUID();
        final UUID player2 = UUID.randomUUID();
        final StartGame command = new StartGame(new Player(player1), new Player(player2), gameInvite);
        final UUID chessGameId = commandHandler.executeCommand(command);

        try (Scanner scanner = new Scanner(System.in)) {
            var player = player1;
            var validMove = true;
            while (validMove) {
                System.out.println("Make a move");
                final String[] input = scanner.nextLine().split(" ");
                final Piece piece = Piece.valueOf(input[0]);
                var p1 = new Position(input[1]);
                var p2 = new Position(input[2]);
                final MakeMove makeMove = new MakeMove(new Move(piece, p1, p2), chessGameId, player);
                validMove = commandHandler.executeCommand(makeMove);
                player = player == player1 ? player2 : player1;
            }
        }
    }
}
