package View;

import javax.swing.*;

public class WordEnteredPanel extends JPanel {
    private JTextField textArea;
    private JLabel currWordLabel;
    private String currWord;

    public WordEnteredPanel(){
        currWordLabel = new JLabel("Current word: ");
        currWord = "";
        textArea = new JTextField(20);
        textArea.setEditable(false);
        this.add(currWordLabel);
        this.add(textArea);

    }

    public void addLetter(String s){
        currWord += s;
        textArea.setText(currWord);
    }

    public void clear(){
        currWord="";
        textArea.setText(null);
    }

}