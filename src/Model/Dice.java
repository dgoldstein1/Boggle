/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * Created by Dave on 8.24.2016 16 (4 row) / 25 (5 row) dice
 *
 */
public class Dice {

    private boolean challengeMode;
    private Language lang;
    public int rows;
    private ArrayList<Die> dice;
    private DieValues values;
    private ArrayList<String> wordsInPuzzle;

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
        for (Iterator<Die> it = dice.iterator(); it.hasNext();) {
            Die d = it.next();
            d.state = ss;
        }
    }

    /**
     * checks if s is in puzzle
     *
     * @param s
     * @return
     */
    public boolean inPuzzle(String s) {
        if (s == null || s.length() < 3) {
            return false;
        }
        s = s.toLowerCase();

        for (Die d : diceContaining(s.charAt(0))) {
            if (inPuzzleHelper(1, s, d) == true) {

                return true;
            }
        }

        return false;
    }

    

    /**
     *
     *
     * /**
     * recursive helper
     *
     * @param s
     * @param index
     * @param current
     * @return found
     */
    private boolean inPuzzleHelper(int index, String s, Die current) {
        if (index == s.length()) {
            return true;
        }

        for (Die d : current.surroundingDice()) {
            if (d.equals(Character.toString(s.charAt(index)))) {
                if (inPuzzleHelper(index + 1, s, d) == true) {
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * selects (highlights) all combinations of s on board
     *
     * @param s
     */
    public void selectWord(String s) {
        s = s.toLowerCase();
        setAll(SquareState.UNSELECTED);
        if (s == null || s.length() == 0) {
            return;
        }
        for (Die d : diceContaining(s.charAt(0))) {
            selectDiceHelper(1, s, d, null);
        }

    }

    /**
     * recursive search for words, sets as selected
     *
     * @param index
     * @param s word entered
     * @param current starting point
     * @param path internal
     * @param word found
     */
    private boolean selectDiceHelper(int index, String s, Die current, ArrayList<Die> path) {
        if (path == null) {
            path = new ArrayList<>(s.length());
            index = 1;
        }
        path.add(current);
        if (index == s.length()) {//word found (reached end)
            for (Die d : path) {
                d.state = SquareState.SELECTED;
            }
            return true;
        }
        for (Die d : current.surroundingDice()) {
            if (d.equals(Character.toString(s.charAt(index))) && !path.contains(d)) {
                selectDiceHelper(index + 1, s, d, path);
            }
        }
        return false;

    }

    /**
     * gets all dice showing letter
     *
     * @param c
     * @return
     */
    private ArrayList<Die> diceContaining(char c) {
        ArrayList<Die> temp = new ArrayList<>(25);
        dice.stream().filter((d) -> (d.equals(c))).forEach((d) -> {
            temp.add(d);
        });
        return temp;
    }

    /**
     * are these two ids touching
     *
     * @param square1
     * @param square2
     * @return
     */
    private boolean areTouching(int square1, int square2) {
        if (Math.abs(square1 - square2) > rows + 1 || square1 == square2) //not within one row
        {
            return false;
        }

        square1 %= rows;
        square2 %= rows;
        return square1 + 1 == square2 || square1 - 1 == square2 || square1 == square2;

    }

    /**
     * @return list of selected squares
     */
    public ArrayList<Integer> highlightedSquares() {
        ArrayList<Integer> toReturn = new ArrayList<>(25);
        dice.stream().filter((d) -> (d.state.equals(SquareState.SELECTED))).forEach((d) -> {
            toReturn.add(d.id);
        });
        return toReturn;
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
        dice = null;
        updateDice();
    }

    public void setChallengeMode(boolean cm) {
        //todo
    }

    public String currLetter(int id) {
        return dice.get(id).currLetter;
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
            for (int i = 0; i < options.length; i++) {
                options[i] = options[i].toLowerCase();
            }
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

        public boolean letterandSelection(char c) {
            return state.equals(SquareState.SELECTED) && currLetter.equals(c);
        }

        /**
         * gets list of surrounding dice
         *
         * @param id
         * @param c
         * @return
         */
        private ArrayList<Die> surroundingDice() {
            ArrayList<Die> temp = new ArrayList<>(8);
            dice.stream().filter((d) -> (areTouching(d.id, id))).forEach((d) -> {
                temp.add(d);
            });
            return temp;

        }

        public boolean equals(String s) {
            return s.equals(currLetter);
        }

        public boolean equals(char c) {
            return currLetter.equals(Character.toString(c));
        }

    }

}
