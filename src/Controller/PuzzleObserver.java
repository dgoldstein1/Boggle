package Controller;

/**
 * Created by Dave on 04/06/2015.
 */
public interface PuzzleObserver {
    void correctWord(String s,int newPoints);
    void incorrectWord();
    void clear();
}
