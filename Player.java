package tictactoe;

interface Player {
    void makeMove(Game game);

    static char calcMove(Game game) {
        int counterX = 0;
        int counterO = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Game.game[i][j] == 'X') {
                    counterX++;
                } else if (Game.game[i][j] == 'O') {
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
}
