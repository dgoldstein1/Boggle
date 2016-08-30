package View;

import Controller.Game;
import Model.Language;
import Model.Puzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Dave on 10/03/2015.
 */
public class WelcomeFrame extends JFrame {
    Container contentPane;
    WelcomeControls welcomeControls;
    MainSound sounds;

    public WelcomeFrame(){
        super("Dave's Boggle");
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
        setPreferredSize(new Dimension(300,350));
        setLayout(new BorderLayout());
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        sounds = new MainSound();
        welcomeControls = new WelcomeControls(sounds);
        contentPane = getContentPane();
        contentPane.add(welcomeControls,BorderLayout.CENTER);
        pack();
        setVisible(true);
    }
}
class WelcomeControls extends JPanel{
    JTextField rowsField;
    MainSound sounds;


    WelcomeControls(MainSound sounds){
        setLayout(new GridLayout(2,2));
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.sounds = sounds;
        initButtons();
    }

    private void initButtons(){

        //rows
        JLabel rowsLabel = new JLabel("Enter Rows: ");
        rowsLabel.setHorizontalAlignment(JLabel.CENTER);
        rowsLabel.setDisplayedMnemonic(KeyEvent.VK_N);
        rowsLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        rowsField = new JTextField(20);
        rowsLabel.setLabelFor(rowsField);

        //mute button and game button
        JButton newGame = new JButton("Boggle!");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeNewGame();
            }
        });
        this.add(rowsLabel);
        this.add(rowsField);
        this.add(newGame);
    }
    private void makeNewGame(){
        int rows=4;
        try {
            rows = Integer.parseInt(rowsField.getText());
        }  catch (NumberFormatException e){
            new PopUpFrame("Input must be an Integer!");
        } catch (IndexOutOfBoundsException e){
            new PopUpFrame("Input must be in range 0-" + "!");
        }catch (NullPointerException e){
            new PopUpFrame("could not find resource");
        }catch (Exception e) {
            new PopUpFrame("Iniput is not in valid range or you're entering something weird");
            e.printStackTrace();
        }
        new Game(Language.spanish,rows);
    }
}
