package tictactoe;

import java.util.Scanner;

public class UserUser extends Game {

    // game cycle for user vs user
    public void play() {

        draw(game);

        while (true) {
            makeUserMove(game);
            draw(game);
            String state1 = calcState(game);
            if (!state1.equals("Game not finished")) {
                System.out.println(state1);
                return;
            }
        }
    }

}
