package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * Created by Dave on 04/06/2015.
 */
public class DieButton extends JButton{
    private int id;
    private boolean selected;

    public DieButton(int id,String s, MouseListener l){
        this.id = id;
        this.setFont(new Font("Arial", Font.BOLD, 30));        
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
    
    public boolean isSelected(){
        return selected;
    }
    public void setAsUnselected(){
        selected=false;
    }

    
}
