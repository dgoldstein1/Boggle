package Controller;

/**
 * Created by Dave on 04/06/2015.
 */
public interface PuzzleObserver {
    void squarePushed(int id, String s);
    void correctWord(String s);
    void incorrectWord();
    void clear();
}
