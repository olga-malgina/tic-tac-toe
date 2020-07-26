package tictactoe;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public static int n; // the size of the board
    public static char[][] game; // array which is the board of the game

    // constructor creates an empty board
    public Game() {
        n = 3;
        game = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(game[i], ' ');
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

    // return element of the array from input coordinates
    public static char getElement(char[][] game, int i, int j) {
        return game[Math.abs(j - 3)][i - 1];
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
    public static void makeMove(char[][] game, int i, int j) {
        if (calcMove(game) == 'X') {
            game[Math.abs(j - 3)][i - 1] = 'X';
        } else {
            game[Math.abs(j - 3)][i - 1] = 'O';
        }
    }

    public void makeUserMove(char[][] game) {
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
                makeMove(game, i, j);
                break;
            }
        }
    }

    // computer make random move - for easy and medium levels
    public static void computerRandomMove(char[][] game) {
        while (true) {
            Random random = new Random();
            int i = random.nextInt(n - 1 + 1) + 1;
            int j = random.nextInt(n - 1 + 1) + 1;
            if (getElement(game, i, j) == ' ') {
                makeMove(game, i, j);
                break;
            }
        }
    }

    // computer move of level "easy"
    public static void computerEasyMove(char[][] game) {
        System.out.println("Making move level \"easy\"");
        computerRandomMove(game);
    }

    // computer move of level "medium
    public static void computerMediumMove(char[][] game) {
        System.out.println("Making move level \"medium\"");
        char target = calcMove(game);
        boolean won = false;
        // check if there is any winning spot horizontally
        for (int i = 0; i < n; ++i) {
            if (game[i][0] == game[i][1] && game[i][0] != ' ' && game[i][2] == ' ') {
                game[i][2] = target;
                return;
            } else if (game[i][0] == game[i][2] && game[i][0] != ' ' && game[i][1] == ' ') {
                game[i][1] = target;
                return;
            } else if (game[i][1] == game[i][2] && game[i][1] != ' ' && game[i][0] == ' ') {
                game[i][0] = target;
                return;
            }
        }

        // check if there is any winning spot vertically
        for (int j = 0; j < n; ++j) {
            if (game[0][j] == game[1][j] && game[0][j] != ' ' && game[2][j] == ' ') {
                game[2][j] = target;
                return;
            } else if (game[0][j] == game[2][j] && game[0][j] != ' ' && game[1][j] == ' ') {
                game[1][j] = target;
                return;
            } else if (game[1][j] == game[2][j] && game[1][j] != ' ' && game[0][j] == ' ') {
                game[0][j] = target;
                return;
            }
        }

        // check if there is any winning spot diagonally
        if (game[0][0] == game[1][1] && game[0][0] != ' ' && game[2][2] == ' ') {
            game[2][2] = target;
            won = true;
        } else if (game[0][0] == game[2][2] && game[0][0] != ' ' && game[1][1] == ' ') {
            game[1][1] = target;
            won = true;
        } else if (game[1][1] == game[2][2] && game[1][1] != ' ' && game[0][0] == ' ') {
            game[0][0] = target;
            won = true;
        }
        if (game[0][2] == game[1][1] && game[0][2] != ' ' && game[2][0] == ' ') {
            game[2][0] = target;
            won = true;
        } else if (game[0][2] == game[2][0] && game[0][2] != ' ' && game[1][1] == ' ') {
            game[1][1] = target;
            won = true;
        } else if (game[1][1] == game[2][0] && game[1][1] != ' ' && game[0][2] == ' ') {
            game[0][2] = target;
            won = true;
        }

        // make random move if there are no winning positions
        if (!won) {
            computerRandomMove(game);
        }
    }
}
