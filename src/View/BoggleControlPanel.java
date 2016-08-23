package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * Created by Dave on 04/06/2015.
 */
public class BoggleControlPanel extends JPanel{
    private JButton enter, clear;

    public BoggleControlPanel(MouseListener listener){
        setLayout(new GridLayout(2,1));
        //setPreferredSize(new Dimension(150,240));
        setBorder(BorderFactory.createLineBorder(Color.black));

        enter = new BoggleButton("enter",listener);
        clear = new BoggleButton("clear",listener);
        add(enter);add(clear);
    }
}
