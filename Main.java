package tictactoe;

import java.util.Scanner;

public class Main {

    // read the string from input and turn into 2d array
    public static char[][] readMatrix() {
        System.out.print("Enter cells: ");

        Scanner sc = new Scanner(System.in);

        char[][] matrix = new char[3][3];
        String newLine = sc.nextLine();
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (newLine.charAt(counter) == '_') {
                    matrix[i][j] = ' ';
                } else {
                    matrix[i][j] = newLine.charAt(counter);
                }
                counter++;
            }
        }

        return matrix;
    }

    // draw current state of the game
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

    // return element of the array from input coordinates
    public static char getElement(char[][] game, int i, int j) {
        return game[Math.abs(j - 3)][i - 1];
    }


    // calculate current state of the game
    public static void calcState(char[][] game) {
        char result;

        if (game[0][0] == game [1][1] && game [0][0] == game[2][2] && game[0][0] != ' ') {
            result = game[0][0];
            System.out.println(result + " wins");
            return;
        }

        if (game[0][2] == game [1][1] && game [0][2] == game[2][0] && game[0][2] != ' ') {
            result = game[0][2];
            System.out.println(result + " wins");
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (game[i][0] == game[i][1] && game[i][0] == game[i][2] && game[i][0] != ' ') {
                result = game[i][0];
                System.out.println(result + " wins");
                return;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (game[0][j] == game[1][j] && game[0][j] == game[2][j] && game[0][j] != ' ') {
                result = game[0][j];
                System.out.println(result + " wins");
                return;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game[i][j] == ' ') {
                    System.out.println("Game not finished");
                    return;
                }
            }
        }

        System.out.println("Draw");
    }

    // determine whose move is next (X or O)
    public static char calcMove(char[][] game) {
        int counterX = 0;
        int counterO = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game[i][j] == 'X') {
                    counterX++;
                } else if (game[i][j] == 'O') {
                    counterO++;
                }
            }
        }
        if (counterO == counterX) {
            return 'X';
        } else {
            return 'O';
        }
    }

    // read the next move
    public static void readMove(char[][] game, int i, int j) {

        if (calcMove(game) == 'X') {
            game[Math.abs(j - 3)][i - 1] = 'X';
        } else {
            game[Math.abs(j - 3)][i - 1] = 'O';
        }
    }

    // establish game cycle of entering coordinates
    public static void launchCycle(char[][] game) {
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
                //continue;
            } else {
                readMove(game, i, j);
                break;
            }


        }
    }

    public static void main(String[] args) {

        char[][] newGame = readMatrix();

        draw(newGame);
        launchCycle(newGame);
        draw(newGame);
        calcState(newGame);
    }
}
