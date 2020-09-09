package tictactoe;

import java.util.Random;

public class Computer implements Player {

    public void makeMove(Game game) {
        while (true) {
            Random random = new Random();
            int i = random.nextInt(Game.n);
            int j = random.nextInt(Game.n);
            if (Game.game[i][j] == ' ') {
                Game.game[i][j] = Player.calcMove(game);
                break;
            }
        }
    }
}
