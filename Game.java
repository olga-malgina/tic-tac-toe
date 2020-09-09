package tictactoe;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public static int n; // the size of the board
    public static char[][] game; // array which is the board of the game

    public Player player1;
    public Player player2;

    // constructor creates an empty board
    public Game() {
    }

    public void createBoard() {
        n = 3;
        game = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(game[i], ' ');
        }
    }

    public static Player createPlayer(String input) {
        switch (input) {
            case "user":
                return new User();
            case "easy":
                return new ComputerEasy();
            case "medium":
                return new ComputerMedium();
            case "hard":
                return new ComputerHard();
            default:
                return null;
        }
    }


    public void play(Player player1, Player player2) {

        createBoard();

        while (true) {
            player1.makeMove(this);
            draw(game);
            String state1 = calcState(game);
            if (!state1.equals("Game not finished")) {
                System.out.println(state1);
                return;
            }
            player2.makeMove(this);
            draw(game);
            String state2 = calcState(game);
            if (!state2.equals("Game not finished")) {
                System.out.println(state2);
                return;
            }
        }
    }

    public void playGame() {
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
                } else {
                    this.player1 = createPlayer(player1);
                    this.player2 = createPlayer(player2);
                    if (this.player1 == null || this.player2 == null) {
                        System.out.println("Bad Parameters");
                    } else {
                        play(this.player1, this.player2);
                    }
                }
            } else {
                System.out.println("Bad parameters!");
            }
        }
    }

    public static void draw(char[][] game) {
        int n = 9;
        while (n > 0) {
            System.out.print("-");
            n--;
        }
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    System.out.print("| " + game[i][j] + " ");
                } else if (j == 2) {
                    System.out.print(game[i][j] + " |");
                } else {
                    System.out.print(game[i][j] + " ");
                }
            }
            System.out.println();
        }
        int m = 9;
        while (m > 0) {
            System.out.print("-");
            m--;
        }
        System.out.println();
    }

    // calculate current state of the game
    public static String calcState(char[][] game) {
        char result;

        if (game[0][0] == game [1][1] && game [0][0] == game[2][2] && game[0][0] != ' ') {
            result = game[0][0];
            return result + " wins";
        }

        if (game[0][2] == game [1][1] && game [0][2] == game[2][0] && game[0][2] != ' ') {
            result = game[0][2];
            return result + " wins";
        }

        for (int i = 0; i < 3; i++) {
            if (game[i][0] == game[i][1] && game[i][0] == game[i][2] && game[i][0] != ' ') {
                result = game[i][0];
                return result + " wins";
            }
        }

        for (int j = 0; j < 3; j++) {
            if (game[0][j] == game[1][j] && game[0][j] == game[2][j] && game[0][j] != ' ') {
                result = game[0][j];
                return result + " wins";
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game[i][j] == ' ') {
                    return "Game not finished";
                }
            }
        }

        return "Draw";
    }
}
