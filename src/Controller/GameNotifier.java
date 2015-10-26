package Controller;

import View.BoggleButton;
import View.BoggleFrame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observer;

/**
 * Created by Dave on 04/06/2015.
 */
public class GameNotifier extends MouseAdapter implements PuzzleObserver{
    private Game g;

    public GameNotifier(Game game){
        g = game;
    }

    /*
        notifier methods
     */

    public void squarePushed(int id,String s){
        g.updateViewSquares(id, s);
    }
    public void correctWord(String s){
        g.correctWord(s);
    }
    public void incorrectWord(){
        g.incorrectWord();
    }
    public void clear(){
        g.clearPushed();
    }

    /*
        MouseAdapter methods
     */

    @Override
    public void mousePressed(MouseEvent e){
        BoggleButton button = (BoggleButton) e.getComponent();
        String actionCommand = button.getActionCommand();

        if(actionCommand.equals("letter_button")){
            g.squarePushed(button.getId());
        }
        else if(actionCommand.equals("enter")){
            g.enterPushed();
        }
        else if(actionCommand.equals("clear")){
            g.clearPushed();
        }


    }
}
