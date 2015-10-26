package View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Dave on 02/06/2015.
 */
public class WordPanel extends JPanel {
    private JTextArea screen;

    public WordPanel(){
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));
        setPreferredSize(new Dimension(150,240));
        screen = new JTextArea("Welcome to Boggle! \n");
        screen.setEditable(false);
        screen.setLineWrap(true);
        this.add(screen);
    }

    public void write(String s){
        screen.append(s);
    }
    public void writeLine(String s){
        screen.append(s);
        screen.append("\n");
    }

}
