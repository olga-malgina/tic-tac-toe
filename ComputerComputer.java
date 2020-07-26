package tictactoe;

public class ComputerComputer extends Game {
    String player1;
    String player2;

    public ComputerComputer(String player1, String player2) {
        super();
        this.player1 = player1;
        this.player2 = player2;
    }

    public void play() {
        draw(game);
        while(true) {
            if (player1.equals("easy")) {
                computerEasyMove(game);
            } else if (player1.equals("medium")) {
                computerMediumMove(game);
            }
            draw(game);
            String state1 = calcState(game);
            if (!state1.equals("Game not finished")) {
                System.out.println(state1);
                break;
            }

            if(player2.equals("easy")) {
                computerEasyMove(game);
            } else if (player2.equals("medium")) {
                computerMediumMove(game);
            }
            draw(game);
            String state2 = calcState(game);
            if (!state2.equals("Game not finished")) {
                System.out.println(state2);
                break;
            }
        }
    }
}
