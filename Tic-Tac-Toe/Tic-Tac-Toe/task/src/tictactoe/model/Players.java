package tictactoe.model;

public class Players {
    public static final String PLAYER_X = "X";
    public static final String PLAYER_O = "O";

    public static String changePlayer(String tempPlayer) {
        if (tempPlayer.equals(PLAYER_X)) tempPlayer = PLAYER_O;
        else tempPlayer = PLAYER_X;
        return tempPlayer;
    }
}
