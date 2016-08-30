package Model;

import Controller.PuzzleObserver;

import java.util.*;

/**
 * Created by Dave on 01/06/2015.
 */
public class Puzzle {

    private Dictionary dic;
    private int points, score;
    private PuzzleObserver obs;
    private String currWord;
    private Set<String> correctWords;
    private Dice dice;

    public Puzzle(Language lang, int rows, PuzzleObserver obs) {
        this.obs = obs;
        dice = new Dice(false, lang, rows);
        currWord = "";
        dic = new Dictionary(lang);
        correctWords = new HashSet<String>(200);
        points = score = 0;
    }

    public void printPuzzle() {
        //todo: arabic is backwards
        for (int id = 0; id < dice.rows * dice.rows; id++) {
            if (id % dice.rows == 0) {
                System.out.println("");
                System.out.println("------------------------");
                System.out.print("| ");
            }
            System.out.print(dice.currLetters()[id] + " | ");
        }
        System.out.println('\n' + "------------------------");
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
        resetMove();
        dice.shuffle();

    }

    public void enterPushed() {
        if (dic.isWord(currWord) && !correctWords.contains(currWord)) {
            correctWords.add(currWord);
            points += determinePoints(currWord);
            obs.correctWord(currWord, points);
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
     * determines value of a word using boggle rules
     *
     * @param s
     * @return
     */
    private int determinePoints(String s) {
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
        return dic.lang();
    }

    public void setLang(Language l) {
        dic.setLang(l);
        dice.setLang(l);
    }

    public String[] getPuzzle() {
        return dice.currLetters();
    }

    public int rows() {
        return dice.rows;
    }
    
    public ArrayList<Integer> higlightedIds(){
        return dice.highlightedSquares();
    }
    public String currWord(){
        return currWord;
    }

}
