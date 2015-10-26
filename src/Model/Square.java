package Model;

/**
 * Created by Dave on 01/06/2015.
 */
public class Square {
    private String letter;
    private int id;
    private SquareState state;

    public Square(String s,int id){
        letter = s;
        this.id = id;
        state = SquareState.UNSELECTED;
    }

    /*
        getters + setters
     */

    public String getLetter() {
        return letter;
    }
    public int getId(){
        return id;
    }
    public SquareState getState() {
        return state;
    }
    public void setState(SquareState s) {
        state = s;
    }

}
