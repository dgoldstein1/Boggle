/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * Created by Dave on 8.24.2016 16 (4 row) / 25 (5 row) dice
 *
 */
public class BoggleDice {

    private boolean challengeMode;
    private Language lang;
    private int rows;
    private Die[] dice;
    private DieValues values;

    public BoggleDice(boolean challengeMode, Language lang, int rows) {
        this.challengeMode = challengeMode;
        this.lang = lang;
        this.rows = rows;
        this.dice = new Die[rows * rows];
        values = new DieValues();
        initDice();
    }

    /**
     * initializes dice from instance variables
     */
    private void initDice() {
        dice = null; //garbage collector
        dice = new Die[rows * rows];
        for (int i = 0 ; i < rows * rows ; i++){
            dice[i] = new Die(i,values.values(i, lang));
        }
    }

    public void shuffleDice() {
        for (Die d : dice) {
            d.shuffle();
        }
    }
    
    public void setState(int id,SquareState ss){
        dice[id].state = ss;
    }
    
    public void setAll(SquareState ss){
        for (Die d : dice){
            d.state = ss;
        }
    }
    
    /**
     * getters and setters
     */

    /**
     * @return letters currently facing 'up'
     */
    public String[] currLetters() {
        String[] toReturn = new String[dice.length];
        try {
            for (int i = 0; i < dice.length; i++) {
                toReturn[i] = dice[i].currLetter;
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return toReturn;
    }
    
    public void setRows(int rows){
        //todo
    }
    
    public void setLang(Language l){
        //todo
    }
    
    public void setChallengeMode(boolean cm){
        //todo
    }
    
    public String currLetter(int id){
        return dice[id].currLetter;
    }
    
    public Die die(int id){
        return dice[id];
    }
 
    

    /**
     * Die holding six letters specified by Alphabet
     */
    class Die {

        final int faces = 6;
        int id;
        String[] options;
        String currLetter;
        SquareState state;

        Die(int id, String[] options) {
            this.id = id;
            this.options = options;
            this.currLetter = options[(int) (Math.random() * faces)];
            this.state = SquareState.UNSELECTED;
        }

        /**
         * chooses new letter from options
         */
        public void shuffle() {
            currLetter = options[(int) (Math.random() * faces)];
        }

        public void setOptions(String[] options) {
            this.options = options;
        }

    }

}
