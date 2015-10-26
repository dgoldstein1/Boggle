package Model;

/**
 * Created by Dave on 01/06/2015.
 */
public class Player {
    private int gameScore, correctAttempts, incorrectAttempts;
    private String playerName;

    public Player(String name){
        playerName = name;
        gameScore = correctAttempts = incorrectAttempts = 0;
    }
}
