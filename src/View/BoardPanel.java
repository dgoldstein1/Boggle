package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created by Dave on 02/06/2015.
 */
public class BoardPanel extends JPanel {

    private DieButton[] squares;
    private MainSound sounds;
    int rows;
    private boolean highlightOn;
    private Color currHighLightColor;
    private int highlightTimes;
    private MouseListener listener;
    Timer t;

    public BoardPanel(String[] ids, MainSound sounds, int rows, final MouseListener listener) {
        setLayout(new GridLayout(rows, rows));
        setBorder(BorderFactory.createLineBorder(Color.black));
        setPreferredSize(new Dimension(240, 240));

        this.sounds = sounds;
        this.rows = rows;
        highlightOn = false;
        currHighLightColor = null;
        highlightTimes = 0;
        this.listener = listener;
        squares = new DieButton[rows * rows];
        for (int i = 0; i < rows * rows; i++) {
            squares[i] = new DieButton(i, ids[i], listener);
            this.add(squares[i]);
        }
        resetBoardText(ids);        
        initHighlightTimer(10, 25);

    }

    /**
     * displays new ids
     * @param ids
     */
    public void resetBoardText(String[] ids) {
        clear();        
        for (int i = 0; i < rows * rows; i++) {
            squares[i].setText(ids[i]);                       
        }
    }

    public void selectSquare(int id) {
        squares[id].setAsHighlighted(Color.YELLOW);
    }

    public void clear() {
        currHighLightColor = null;
        highlightOn = false;
        highlightTimes = 0;
        for (DieButton b : squares) {
            b.clear();
        }

    }
    
    public void highlightIds(ArrayList<Integer> ids,Color c){
        clear();        
        for(int i = 0 ; i < ids.size() ; i++){
            squares[ids.get(i)].setAsHighlighted(c);
        }
        
        
    }

    /*
      highlighting words after enter is pressed
     */
    public void highlightSelected(Color c) {
        currHighLightColor = c;
        highlightOn = true;
    }

    private void initHighlightTimer(final int speed, final int pauseTm) {
        t = new Timer(speed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (highlightOn) {
                    highlightTimes++;
                    for (DieButton b : squares) {
                        if (b.isSelected()) {
                            b.setAsHighlighted(currHighLightColor);
                            b.setAsUnselected();
                            return;
                        }
                    }
                    if (highlightTimes > pauseTm) {
                        clear();
                    }
                }
            }
        });
        t.start();
    }

}
