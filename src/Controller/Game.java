package Controller;

import Model.Language;
import Model.Puzzle;
import View.BoggleFrame;


/**
 * Created by Dave on 03/06/2015.
 */
public class Game {
    private Puzzle p;
    private BoggleFrame frame;
    private GameNotifier gameNotifier;

    public Game (Language lang, int rows){
        gameNotifier = new GameNotifier(this);
        p = new Puzzle(lang,rows,gameNotifier);
        frame = new BoggleFrame(rows,p.getPuzzle(),gameNotifier);
    }

    //in from View
    public void squarePushed(int id){
        p.squarePushed(id);
    }
    public void enterPushed(){
        p.enterPushed();
    }
    public void clearPushed(){
        p.resetMove();
        this.clearBoard();
    }
    //in from puzzle
    public void updateViewSquares(int id, String s){
        frame.squareSelected(id, s);
    }
    public void clearBoard(){
        frame.clearBoard();
    }
    public void correctWord(String s, int newPoints){
        frame.correctWord(s,newPoints);
    }
    public void incorrectWord(){
        frame.incorrectWord();
    }
    
    public Language getLang(){
        return p.getLang();
    }
    
    public void setLang(Language l){
        p.setLang(l);
        newPuzzle();
    }
    public void newPuzzle(){
        p.newPuzzle();
        p.printPuzzle();
        frame.resetBoard(p.getPuzzle());
        
    }

}
