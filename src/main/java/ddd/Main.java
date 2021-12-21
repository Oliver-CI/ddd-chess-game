package ddd;

import ddd.core.businessrules.BusinessRuleViolation;
import ddd.core.businessrules.BusinessRuleViolationException;
import ddd.game.BoardPrinter;
import ddd.game.SimpleEventBus;
import ddd.game.commands.CommandHandler;
import ddd.game.commands.MakeMove;
import ddd.game.commands.StartGame;
import ddd.game.domain.Move;
import ddd.game.domain.Player;
import ddd.game.domain.Position;
import ddd.game.repositories.ChessGameRepository;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println("**************************");
        System.out.println("*     The Bruur Game     *");
        System.out.println("**************************\n");
        var eventBus = new SimpleEventBus();
        var boardPrinter = new BoardPrinter();
        eventBus.subscribe(boardPrinter);
        final CommandHandler commandHandler = new CommandHandler(new ChessGameRepository(), eventBus);
        final UUID gameInvite = UUID.randomUUID();
        var player1 = new Player(new Player.Id(UUID.randomUUID()));
        var player2 = new Player(new Player.Id((UUID.randomUUID())));
        final StartGame command = new StartGame(player1, player2, gameInvite);
        var chessGameId = commandHandler.executeCommand(command);

        try (Scanner scanner = new Scanner(System.in)) {
            var player = player1;
            while (true) {
                try {
                    System.out.println("Make a move: position position");
                    final String[] input = scanner.nextLine().split(" ");
                    var p1 = new Position(input[0]);
                    var p2 = new Position(input[1]);
                    final MakeMove makeMove = new MakeMove(new Move(p1, p2), chessGameId, player);
                    commandHandler.executeCommand(makeMove);
                    player = player == player1 ? player2 : player1;
                } catch (BusinessRuleViolationException businessRuleViolationException) {
                    final List<BusinessRuleViolation> violations = businessRuleViolationException.getViolations();
                    System.out.println("\n* violations *\n-----------------------------------------------------------");
                    violations.forEach(System.out::println);
                    System.out.println();
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
}
