package Model;

import Controller.PuzzleObserver;

import java.util.*;

/**
 * Created by Dave on 01/06/2015.
 */
public class Puzzle {
    private Map<Integer,Square> ids;
    private Dictionary dic;
    private Language lang;
    private int rows;
    private PuzzleObserver obs;
    private String currWord;
    private Square lastSquarePressed;
    private Set<String> correctWords;

    public Puzzle(Language lang,int rows, PuzzleObserver obs){
        this.lang = lang;
        this.rows = rows;
        this.obs = obs;
        currWord="";
        lastSquarePressed = null;
        dic = new Dictionary(lang);
        correctWords = new HashSet<String>(200);
        ids = new HashMap<Integer, Square>(rows * rows);
        initializeRows();
    }
    private void initializeRows(){
        System.out.print("Creating puzzle...");
        for(int id=0;id<rows*rows;id++){
            Square sq = new Square(dic.getRandomLetter(),id);
            ids.put(id,sq);
        }
        System.out.println("done");
    }
    public void printPuzzle(){
        //todo: arabic is backwards
        for(int id=0;id<rows*rows;id++){
            if(id % rows == 0){
                System.out.println("");
                System.out.println("------------------------");
                System.out.print("| ");
            }
            System.out.print(ids.get(id).getLetter() + " | ");
        }
        System.out.println('\n' + "------------------------");
    }
    public String[] getPuzzle(){
        String[] idList = new String[rows * rows];
        for(int i=0;i<rows * rows;i++){
            idList[i] = (ids.get(i)).getLetter();
        }
        return idList;
    }
    public void resetBoard(){
        for(int i=0;i<rows * rows;i++){
            ids.get(i).setState(SquareState.UNSELECTED);
        }
        currWord="";
        lastSquarePressed=null;
    }
    private boolean areTouching(int previous,int curr){
        if(Math.abs(previous - curr) > rows + 1) //not within one row
            return  false;

        previous %= rows;
        curr %= rows;
        if(previous + 1 == curr || previous - 1 == curr || previous == curr)
            return true;

        return false;

    }
    private void legalSquareSelected(int id,Square s){
        currWord += s.getLetter();
        lastSquarePressed = s;
        s.setState(SquareState.SELECTED);
        obs.squarePushed(id,s.getLetter());
    }

    /*
        action events
     */

    public void squarePushed(int id){
        Square s = ids.get(id);
        if(s.getState()==SquareState.SELECTED) return; //alreaddy selected
        if(lastSquarePressed==null){ //first push
            obs.clear();
        }
        else {
            if(!areTouching(lastSquarePressed.getId(),id)) return; //touching previous
        }
        legalSquareSelected(id,s);
    }

    public void enterPushed(){
        if(dic.isWord(currWord) && !correctWords.contains(currWord)){
            correctWords.add(currWord);
            obs.correctWord(currWord);
        }
        else{ //not a word or already in correctWords
            obs.incorrectWord();
        }
        this.resetBoard();
    }


}
