package tictactoe;

import java.util.Stack;

public class ComputerHard extends ComputerMedium {

    static class Move {
        int index;
        int score;
    }

    int[] findAvailableSpots(char[] board) {
        int emptyCounts = 0;
        for (char c : board) {
            if (c == ' ') {
                ++emptyCounts;
            }
        }
        int[] availableSpots = new int[emptyCounts];
        int counter = 0;
        for (int i = 0; i < board.length; ++i) {
            if (board[i] == ' ') {
                availableSpots[counter] = i;
                ++counter;
            }
        }
        return availableSpots;
    }

    public boolean isWinning(char[] board, char player) {
        return (board[0] == player && board[1] == player && board[2] == player) ||
                (board[3] == player && board[4] == player && board[5] == player) ||
                (board[6] == player && board[7] == player && board[8] == player) ||
                (board[0] == player && board[3] == player && board[6] == player) ||
                (board[1] == player && board[4] == player && board[7] == player) ||
                (board[2] == player && board[5] == player && board[8] == player) ||
                (board[0] == player && board[4] == player && board[8] == player) ||
                (board[2] == player && board[4] == player && board[6] == player);
    }


    public int convertI(int index) {
        return index / 3;
    }

    public int convertJ(int index) {
        return index % 3;
    }


    // changing it from returning int to returning a Move instance
    Move miniMax(char[] newBoard, char player, char originalPlayer) {
        int[] availSpots = findAvailableSpots(newBoard);
        Move result = new Move();
        if (isWinning(newBoard, originalPlayer)) {
            result.score = 10;
            return result;
        } else {
            // change condition player -> originalPlayer
            if (originalPlayer == 'X') {
                if (isWinning(newBoard, 'O')) {
                    result.score = -10;
                    return result;
                }
            } else {
                if (isWinning(newBoard, 'X')) {
                    result.score = -10;
                    return result;
                }
            }
        }
        if (availSpots.length == 0) {
            result.score = 0;
            return result;
        }

        //create a stack of moves
        Stack<Move> moves = new Stack<Move>();

        // loop through available spots and collect the score of the corresponding moves
        for (int availSpot : availSpots) {
            Move move = new Move();
            move.index = availSpot;
            newBoard[availSpot] = player;

            // changed move.score for
            if (player == 'X') {
                move.score = miniMax(newBoard, 'O', originalPlayer).score;
            } else {
                move.score = miniMax(newBoard, 'X', originalPlayer).score;
            }
            // return the board to its original state
            newBoard[availSpot] = ' ';
            moves.push(move);
        }


        Move bestMove = new Move();

        int bestScore;
        if (originalPlayer == player) {
            bestScore = Integer.MIN_VALUE;
            for (Move move : moves) {
                if (move.score > bestScore) {
                    bestScore = move.score;
                    bestMove.index = move.index;
                    bestMove.score = move.score;
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (Move move : moves) {
                if (move.score < bestScore) {
                    bestScore = move.score;
                    bestMove.index = move.index;
                    bestMove.score = move.score;
                }
            }
        }
        return bestMove;
    }

    public void makeMove(Game game) {
        System.out.println("Making move level \"hard\"");
        Move nextMove = miniMax(createSingleBoard(game), Player.calcMove(game), Player.calcMove(game));
        int i = convertI(nextMove.index);
        int j = convertJ(nextMove.index);
        Game.game[i][j] = Player.calcMove(game);
    }
}
