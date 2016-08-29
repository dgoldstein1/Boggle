/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * Created by Dave on 8.24.2016 16 (4 row) / 25 (5 row) dice
 *
 */
public class Dice {

    private boolean challengeMode;
    private Language lang;
    private int rows;
    private ArrayList<Die> dice;
    private DieValues values;

    public Dice(boolean challengeMode, Language lang, int rows) {
        this.challengeMode = challengeMode;
        this.lang = lang;
        this.rows = rows;
        this.values = new DieValues();
        updateDice();
    }

    /**
     * initializes/updates dice from instance variables
     */
    private void updateDice() {
        if (dice == null) {
            dice = new ArrayList<>(rows * rows);
            for (int i = 0; i < rows * rows; i++) {
                dice.add(new Die(i, values.values(i, lang)));
            }
        } else {
            dice.stream().forEach((d) -> {
                d.options = values.values(d.id, lang);
            });
        }
        shuffle();

    }

    public void shuffle() {
        //shuffle order of dice
        ArrayList<Die> temp = new ArrayList<>(dice.size());

        int loc = 0;
        while (!dice.isEmpty()) {
            loc = (int) (Math.random() * dice.size());
            temp.add(dice.get(loc));
            dice.remove(loc);
        }
        dice = temp;

        //shuffle side facing up
        for (int i = 0; i < dice.size(); i++) {
            dice.get(i).id = i; //reorder ids after shuffle
            dice.get(i).shuffle();
        }
    }

    public void setState(int id, SquareState ss) {
        dice.get(id).state = ss;
    }

    public void setAll(SquareState ss) {
        for (Die d : dice) {
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
        String[] toReturn = new String[dice.size()];
        try {
            for (int i = 0; i < dice.size(); i++) {
                toReturn[i] = dice.get(i).currLetter;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return toReturn;
    }

    public void setRows(int rows) {
        //todo
    }

    public void setLang(Language l) {
        lang = l;
        updateDice();
    }

    public void setChallengeMode(boolean cm) {
        //todo
    }

    public String currLetter(int id) {
        return dice.get(id).currLetter;
    }

    public Die die(int id) {
        return dice.get(id);
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
