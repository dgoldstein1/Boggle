package Controller;

import Model.Language;
import View.DieButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Dave on 04/06/2015.
 */
public class GameNotifier extends MouseAdapter implements Controller.PuzzleObserver{
    private Game g;

    public GameNotifier(Game game){
        g = game;
    }

    /*
        notifier methods
     */

    
    public void correctWord(String s,int newPoints){
        g.correctWord(s,newPoints);
    }
    public void incorrectWord(){
        g.incorrectWord();
    }
    public void clear(){
        g.clearPushed();
    }
    
    public void enterPushed(){
        g.enterPushed();
    }
    
    public void newPuzzle(){
        g.newPuzzle();
    }
    
    public Language getCurrLang(){
        return g.getLang();
    }
    
    public void setLang(Language l){
        g.setLang(l);
    }
    public void wordFieldUpdated(String s){
        g.wordFieldUpdated(s);
    }    

    /*
        MouseAdapter methods
     */

    @Override
    public void mousePressed(MouseEvent e){
        DieButton button = (DieButton) e.getComponent();
        g.squarePushed(button.getText());
       
        


    }
}
