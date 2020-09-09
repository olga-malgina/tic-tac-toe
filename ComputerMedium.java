package tictactoe;

public class ComputerMedium extends Computer {

    // change from passing Game game argument to 2 arguments - game and n
    // for hard mode rewrote it to work with one-dimensional array
    public boolean findWinningSpot(Game game) {
        char target = Player.calcMove(game);
        boolean won = false;
        // check if there is any winning spot horizontally
        for (int i = 0; i < Game.n; ++i) {
            if (Game.game[i][0] == Game.game[i][1] && Game.game[i][0] != ' ' && Game.game[i][2] == ' ') {
                Game.game[i][2] = target;
                return true;
            } else if (Game.game[i][0] == Game.game[i][2] && Game.game[i][0] != ' ' && Game.game[i][1] == ' ') {
                Game.game[i][1] = target;
                return true;
            } else if (Game.game[i][1] == Game.game[i][2] && Game.game[i][1] != ' ' && Game.game[i][0] == ' ') {
                Game.game[i][0] = target;
                return true;
            }
        }

        // check if there is any winning spot vertically
        for (int j = 0; j < Game.n; ++j) {
            if (Game.game[0][j] == Game.game[1][j] && Game.game[0][j] != ' ' && Game.game[2][j] == ' ') {
                Game.game[2][j] = target;
                return true;
            } else if (Game.game[0][j] == Game.game[2][j] && Game.game[0][j] != ' ' && Game.game[1][j] == ' ') {
                Game.game[1][j] = target;
                return true;
            } else if (Game.game[1][j] == Game.game[2][j] && Game.game[1][j] != ' ' && Game.game[0][j] == ' ') {
                Game.game[0][j] = target;
                return true;
            }
        }

        // check if there is any winning spot diagonally
        if (Game.game[0][0] == Game.game[1][1] && Game.game[0][0] != ' ' && Game.game[2][2] == ' ') {
            Game.game[2][2] = target;
            return true;
        } else if (Game.game[0][0] == Game.game[2][2] && Game.game[0][0] != ' ' && Game.game[1][1] == ' ') {
            Game.game[1][1] = target;
            return true;
        } else if (Game.game[1][1] == Game.game[2][2] && Game.game[1][1] != ' ' && Game.game[0][0] == ' ') {
            Game.game[0][0] = target;
            return true;
        }
        if (Game.game[0][2] == Game.game[1][1] && Game.game[0][2] != ' ' && Game.game[2][0] == ' ') {
            Game.game[2][0] = target;
            return true;
        } else if (Game.game[0][2] == Game.game[2][0] && Game.game[0][2] != ' ' && Game.game[1][1] == ' ') {
            Game.game[1][1] = target;
            return true;
        } else if (Game.game[1][1] == Game.game[2][0] && Game.game[1][1] != ' ' && Game.game[0][2] == ' ') {
            Game.game[0][2] = target;
            return true;
        }

        return false;
    }

    char[] createSingleBoard(Game game) {
        char[] singleBoard = new char[Game.n * Game.n];
        int counter = 0;
        for (int i = 0; i < Game.n; ++i) {
            for (int j = 0; j < Game.n; ++j) {
                singleBoard[counter] = Game.game[i][j];
                ++counter;
            }
        }
        return singleBoard;
    }


    public void makeMove(Game game) {
        System.out.println("Making move level \"medium\"");
        //boolean winning = hasWinningSpot(createSingleBoard(game), Player.calcMove(game));
        boolean winning = findWinningSpot(game);
        if (!winning) {
            super.makeMove(game);
        }
    }

}
