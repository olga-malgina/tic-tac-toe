package tictactoe;

import java.util.Scanner;

public class User implements Player {


    public static char getElement(Game game, int i, int j) {
        return Game.game[Math.abs(j - 3)][i - 1];
    }

    // check VERY CAREFULLY if it works (reference to Player)
    public static void markMove(Game game, int i, int j) {
        if (Player.calcMove(game) == 'X') {
            Game.game[Math.abs(j - 3)][i - 1] = 'X';
        } else {
            Game.game[Math.abs(j - 3)][i - 1] = 'O';
        }
    }

    public void makeMove(Game game) {
        while (true) {
            System.out.print("Enter the coordinates: ");
            Scanner sc = new Scanner(System.in);
            if (!sc.hasNextInt()) {
                System.out.println("You should enter numbers!");
                continue;
            }

            int i = sc.nextInt();
            int j = sc.nextInt();

            if (i > 3 || j > 3 || i < 1 || j < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (getElement(game, i, j) != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                markMove(game, i, j);
                break;
            }
        }
    }

}
