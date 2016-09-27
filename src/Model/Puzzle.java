package Model;

import Controller.PuzzleObserver;
import View.ResultsFrame;

import java.util.*;

/**
 * Created by Dave on 01/06/2015.
 */
public class Puzzle {

    private Dictionary dic;
    private int points, score;
    private final PuzzleObserver obs;
    private String currWord;
    private final Set<String> correctWords;
    private final Dice dice;
    private View.ResultsFrame frame;
    private Language lang;

    public Puzzle(Language lang, int rows, PuzzleObserver obs) {
        this.lang = lang;
        this.obs = obs;
        dice = new Dice(false, lang, rows);
        currWord = "";
        dic = new Dictionary(lang,dice);
        correctWords = new HashSet<>(200);
        points = score = 0;
    }

    /**
     * sets all tiles as unselected resets current word
     */
    public void resetMove() {
        dice.setAll(SquareState.UNSELECTED);
        currWord = "";
    }

    /**
     * resets board and makes new puzzle
     */
    public void newPuzzle() {
        correctWords.clear();
        resetMove();
        dice.shuffle();
        dic.init(this.lang,dice);

    }

    public void enterPushed() {
        if (dic.isWord(currWord) && !correctWords.contains(currWord)) {
            correctWords.add(currWord);
            points += determinePoints(currWord);
            obs.correctWord(currWord, points, correctWords.size());
        } else { //not a word or already in correctWords
            obs.incorrectWord();
        }
        this.resetMove();
    }

    /**
     * text field is updated
     *
     * @param s new word
     */
    public void wordFieldUpdated(String s) {
        currWord = s;
        dice.selectWord(s);

    }

    /**
     * ends game and displays results
     */
    public void endPuzzle() {
        frame = new ResultsFrame(obs,correctWords, dic.getDic());
        
    }


    

    /**
     * determines value of a word using boggle rules
     *
     * @param s
     * @return
     */
    public int determinePoints(String s) {
        int len = s.length();
        if (len <= 4) {
            return 1;
        }
        switch (len) {
            case (5):
                return 2;
            case (6):
                return 3;
            case (7):
                return 5;
            default: //8+
                return 11;
        }
    }

    /*
     
        getters and setters
     */
    public Language getLang() {
        return lang;
    }

    public void setLang(Language l) {
        this.lang = l;
        dice.setLang(l);
    }

    public String[] getPuzzle() {
        return dice.currLetters();
    }

    public int rows() {
        return dice.rows;
    }

    public ArrayList<Integer> higlightedIds() {
        return dice.highlightedSquares();
    }

    public String currWord() {
        return currWord;
    }

    public Set<String> correctWords() {
        return correctWords;
    }

    public int points() {
        return points;
    }
    
 

}
