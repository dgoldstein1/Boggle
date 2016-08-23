package Model;

import Controller.PuzzleObserver;

import java.util.*;

/**
 * Created by Dave on 01/06/2015.
 */
public class Puzzle {
    private Map<Integer,Square> ids;
    private Dictionary dic;
    private int rows,points,score;
    private PuzzleObserver obs;
    private String currWord;
    private Square lastSquarePressed;
    private Set<String> correctWords;

    public Puzzle(Language lang,int rows, PuzzleObserver obs){
        this.rows = rows;
        this.obs = obs;
        currWord="";
        lastSquarePressed = null;
        dic = new Dictionary(lang);
        correctWords = new HashSet<String>(200);
        ids = new HashMap<Integer, Square>(rows * rows);
        initializeRows();
        points = score = 0;
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

    /**
     * sets all tiles as unselected
     * resets current word
     */
    public void resetMove(){
        for(int i=0;i<rows * rows;i++){
            ids.get(i).setState(SquareState.UNSELECTED);
        }
        currWord="";
        lastSquarePressed=null;
    }
    
    /**
     * resets board and makes new puzzle
     */
    public void newPuzzle(){
        resetMove();
        ids.clear();
        initializeRows();
        
    }
    private boolean areTouching(int previous,int curr){
        if(Math.abs(previous - curr) > rows + 1) //not within one row
            return  false;

        previous %= rows;
        curr %= rows;
        return previous + 1 == curr || previous - 1 == curr || previous == curr;

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
            points += determinePoints(currWord);
            obs.correctWord(currWord,points);
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
        String[] idList = new String[rows * rows];
        for(int i=0;i<rows * rows;i++){
            idList[i] = (ids.get(i)).getLetter();
        }
        return idList;
    }
    public int rows(){
        return rows;
    }

}
