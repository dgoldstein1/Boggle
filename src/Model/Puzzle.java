package Model;

import Controller.PuzzleObserver;
import Model.BoggleDice.Die;

import java.util.*;

/**
 * Created by Dave on 01/06/2015.
 */
public class Puzzle {
    private Dictionary dic;
    private int rows,points,score;
    private PuzzleObserver obs;
    private String currWordSelected;
    private int lastSquarePressed;
    private Set<String> correctWords;
    private BoggleDice dice;

    public Puzzle(Language lang,int rows, PuzzleObserver obs){
        this.rows = rows;
        this.obs = obs;
        dice = new BoggleDice(false,lang,rows);
        currWordSelected="";
        lastSquarePressed = -1;
        dic = new Dictionary(lang);
        correctWords = new HashSet<String>(200);
        points = score = 0;
    }

    public void printPuzzle(){
        //todo: arabic is backwards
        for(int id=0;id<rows*rows;id++){
            if(id % rows == 0){
                System.out.println("");
                System.out.println("------------------------");
                System.out.print("| ");
            }
            System.out.print(dice.currLetters()[id] + " | ");
        }
        System.out.println('\n' + "------------------------");
    }

    /**
     * sets all tiles as unselected
     * resets current word
     */
    public void resetMove(){
        dice.setAll(SquareState.UNSELECTED);
        currWordSelected="";
        lastSquarePressed=-1;
    }
    
    /**
     * resets board and makes new puzzle
     */
    public void newPuzzle(){
        resetMove();
        //todo
        
    }
    private boolean areTouching(int previous,int curr){
        if(Math.abs(previous - curr) > rows + 1) //not within one row
            return  false;

        previous %= rows;
        curr %= rows;
        return previous + 1 == curr || previous - 1 == curr || previous == curr;

    }
    private void legalSquareSelected(int id){        
        String letter = dice.currLetter(id);
        currWordSelected += letter;
        lastSquarePressed = id;
        dice.setState(id,SquareState.SELECTED);
        obs.squarePushed(id,letter);
    }

    /*
        action events
     */

    public void squarePushed(int id){
        Die selected = dice.die(id);
        if(selected.state==SquareState.SELECTED) return; //alreaddy selected
        if(lastSquarePressed==-1){ //first push
            obs.clear();
        }
        else {
            if(!areTouching(lastSquarePressed,id)) return; //touching previous
        }
        legalSquareSelected(id);
    }

    public void enterPushed(){
        if(dic.isWord(currWordSelected) && !correctWords.contains(currWordSelected)){
            correctWords.add(currWordSelected);
            points += determinePoints(currWordSelected);
            obs.correctWord(currWordSelected,points);
        }
        else{ //not a word or already in correctWords
            obs.incorrectWord();
        }
        this.resetMove();
    }
    
    /**
     * determines value of a word using boggle rules
     * @param s
     * @return 
     */
    private int determinePoints(String s){
        int len = s.length();
        if (len < 4) return 1;
        switch(len){
            case(5):
                return 2;
            case(6):
                return 3;
            case(7):
                return 5;
            default: //8+
                return 11;
        }
    }
    
    /*
     
        getters and settesr
    */

    
    public Language getLang(){
        return dic.lang();
    }
    public void setLang(Language l){
        dic.setLang(l);
    }
    public String[] getPuzzle(){
        return dice.currLetters();
    }
    public int rows(){
        return rows;
    }

}
