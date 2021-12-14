package ddd;

import ddd.game.commands.CommandHandler;
import ddd.game.commands.MakeMove;
import ddd.game.commands.StartGame;
import ddd.game.domain.Move;
import ddd.game.domain.Player;
import ddd.game.domain.Position;
import ddd.game.repositories.ChessGameRepository;

import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println("**************************");
        System.out.println("*     The Bruur Game     *");
        System.out.println("**************************\n");
        final CommandHandler commandHandler = new CommandHandler(new ChessGameRepository());
        final UUID gameInvite = UUID.randomUUID();
        var player1 = new Player(new Player.Id(UUID.randomUUID()));
        var player2 = new Player(new Player.Id((UUID.randomUUID())));
        final StartGame command = new StartGame(player1, player2, gameInvite);
        final UUID chessGameId = commandHandler.executeCommand(command);

        try (Scanner scanner = new Scanner(System.in)) {
            var player = player1;
            var validMove = true;
            while (validMove) {
                System.out.println("Make a move");
                final String[] input = scanner.nextLine().split(" ");
                var p1 = new Position(input[1]);
                var p2 = new Position(input[2]);
                final MakeMove makeMove = new MakeMove(new Move(p1, p2), chessGameId, player);
                validMove = commandHandler.executeCommand(makeMove);
                player = player == player1 ? player2 : player1;
            }
        }
    }
}
