package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * Created by Dave on 04/06/2015.
 */
public class BoggleButton extends JButton{
    private int id;
    private boolean selected;

    public BoggleButton(int id,String s, MouseListener l){
        this.id = id;
        selected =false;
        setText(s);
        setActionCommand("letter_button");
        addMouseListener(l);

    }
    public void setAsHighlighted(Color c){
        selected=true;
        setContentAreaFilled(false);
        setOpaque(true);
        setBackground(c);
        setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));
    }
    public void clear(){
        selected=false;
        setBackground(null);
        setBorder(null);
        setContentAreaFilled(true);
    }
    /*
        getters and setters
     */
    public int getId(){
        return id;
    }
    public boolean isSelected(){
        return selected;
    }
    public void setAsUnselected(){
        selected=false;
    }

    //for non Letter buttons
    public BoggleButton(String actionCommand, MouseListener l){
        setActionCommand(actionCommand);
        setText(actionCommand);
        addMouseListener(l);
        id = -1;
    }
}
