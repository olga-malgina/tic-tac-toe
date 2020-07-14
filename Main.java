package tictactoe;

import java.util.*;

public class Main {

    public static int n; // the size of the board

    // create an empty board
    public static char[][] createBoard() {
        n = 3;
        char[][] game = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(game[i], ' ');
        }
        return game;
    }


    // read the string from input and turn into 2d array -
    // TODO: comment this code as the matrix won't be read from input
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

    // make computer easy move
    public static void computerMakeMove(char[][] game) {
        while (true) {

            Random random = new Random();
            int i = random.nextInt(n - 1 + 1) + 1;
            int j = random.nextInt(n - 1 + 1) + 1;
            if (getElement(game, i, j) == ' ') {
                System.out.println("Making move level \"easy\"");
                readMove(game, i, j);
                break;
            }
        }
    }

    // establish game cycle of entering coordinates
    // TODO: rename this method
    public static void getUserMove(char[][] game) {
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

    // game cycle for computer vs computer
    public static void playComputerComputer() {
        char[][] newGame = createBoard();
        draw(newGame);

        while (true) {
            computerMakeMove(newGame);
            draw(newGame);
            String state = calcState(newGame);
            if (!state.equals("Game not finished")) {
                System.out.println(state);
                break;
            }
        }
    }

    // game cycle - this will be used as computer vs user
    public static void playComputerUser() {
        char[][] newGame = createBoard();
        draw(newGame);

        while (true) {
            getUserMove(newGame);
            draw(newGame);
            String state1 = calcState(newGame);
            if (!state1.equals("Game not finished")) {
                System.out.println(state1);
                break;
            }
            computerMakeMove(newGame);
            draw(newGame);
            String state2 = calcState(newGame);
            if (!state2.equals("Game not finished")) {
                System.out.println(state2);
                break;
            }
        }
    }

    // choosing the game mode. need to implement logic of infinite cycle which is broken only by exit?
    public static void chooseMode() {
        System.out.print("Input command: ");
        Scanner sc = new Scanner(System.in);
        String command = sc.next();

        while (true) {
            if (command.equals("exit")) {
                return;
            } else if (command.equals("start")) {
                String player1 = sc.next();
                String player2 = sc.next();
                if (player2.equals(player1) && player2.equals("easy")) {
                    playComputerComputer();
                    return;
                } else if (player1.equals("easy") && player2.equals("user") ||
                        player1.equals("user") && player2.equals("easy")) {
                    playComputerUser();
                    return;
                } else {
                    System.out.println("Bad parameters!");
                }
            } else {
                System.out.println("Bad parameters!");
            }
        }
    }

    public static void main(String[] args) {
        chooseMode();
    }
}
