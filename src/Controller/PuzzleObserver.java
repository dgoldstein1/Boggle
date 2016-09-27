package Controller;

/**
 * Created by Dave on 04/06/2015.
 */
public interface PuzzleObserver {
    void correctWord(String s,int newPoints,int nWords);
    void incorrectWord();
    void clear();
    void newGame();
    int getPoints(String s);
}
