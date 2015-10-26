import Controller.Game;
import Model.Language;
import Model.Puzzle;
import View.WelcomeFrame;

import java.util.ArrayList;

/**
 * Created by Dave on 01/06/2015.
 */
public class BoggleRun {
    public static void main(String[] args) {
        new Game(Language.LATIN,4);
        //new WelcomeFrame();
    }
}
