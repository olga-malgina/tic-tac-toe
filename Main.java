package tictactoe;

import java.util.*;

public class Main {

    public static void playGame() {
        while (true) {
            System.out.print("Input command: ");
            Scanner sc = new Scanner(System.in);
            String command = sc.next();
            if (command.equals("exit")) {
                return;
            } else if (command.equals("start")) {
                String player1 = sc.next();
                String player2 = sc.next();
                if (player1.isEmpty() || player2.isEmpty()) {
                    System.out.println("Bad Parameters");
                }
                if (!(player1.equals("user") || player1.equals("easy") || player1.equals("medium")) &&
                        !(player2.equals("user") || player2.equals("easy") || player2.equals("medium"))) {
                    System.out.println("Bad parameters!");
                } else {
                    if (player2.equals(player1) && player2.equals("user")) {
                        UserUser game = new UserUser();
                        game.play();
                    } else if (player1.equals("user") || player2.equals("user")) {
                        ComputerUser game = new ComputerUser(player1, player2);
                        game.play();
                    } else {
                        ComputerComputer game = new ComputerComputer(player1, player2);
                        game.play();
                    }
                }
            } else {
                System.out.println("Bad parameters!");
            }
        }
    }

    public static void main(String[] args) {
        playGame();
    }
}
