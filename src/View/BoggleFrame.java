package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Dave on 02/06/2015.
 */
public class BoggleFrame extends JFrame{
    private Container contentPane;
    private MainSound sounds;
    private BoardPanel board;
    private WordPanel screen;
    private BoggleControlPanel controls;
    private WordEnteredPanel currWordPanel;

    public BoggleFrame(int rows, String[] ids, MouseListener listener) {
        super("Boggle!");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        setPreferredSize(new Dimension(700,500));
        setLayout(new BorderLayout());
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                board.t.stop();
                dispose();
            }
        });

        contentPane = getContentPane();
        sounds = new MainSound();
        initPanels(ids,rows,listener);

        pack();
        setVisible(true);

    }
    private void initPanels(String[] ids, int rows, MouseListener listener){
        board = new BoardPanel(ids,sounds,rows,listener);
        screen = new WordPanel();
        controls = new BoggleControlPanel(listener);
        currWordPanel = new WordEnteredPanel();
        contentPane.add(board, BorderLayout.CENTER);
        contentPane.add(screen, BorderLayout.WEST);
        contentPane.add(controls,BorderLayout.EAST);
        contentPane.add(currWordPanel,BorderLayout.SOUTH);
    }
    public void fillBoard(String[] letters){
        //todo
    }
    public void clearBoard(){
        board.clear();
        currWordPanel.clear();
    }


    /*
        Update methods called by Notifier
     */

    public void squareSelected(int id, String s){
        board.selectSquare(id);
        currWordPanel.addLetter(s);
    }
    public void correctWord(String s){
        board.highlightWord(Color.GREEN);
        screen.writeLine(s);
        currWordPanel.clear();
    }
    public void incorrectWord(){
        board.highlightWord(Color.RED);
        currWordPanel.clear();
    }



}
