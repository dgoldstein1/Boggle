package Controller;

/**
 * Created by Dave on 04/06/2015.
 */
public interface PuzzleObserver {
    void squarePushed(int id, String s);
    void correctWord(String s,int newPoints);
    void incorrectWord();
    void clear();
}
