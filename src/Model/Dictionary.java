package Model;

import View.FileReaderFrame;
import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;
import java.io.*;
import java.util.HashSet;
import java.util.List;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 * Created by Dave on 01/06/2015.
 */
public class Dictionary {

    private HashSet<String> dic;
    private Language lang;
    private String filename;
    private FileReader reader;
    private InputStream is;
    private BufferedReader br;

    public Dictionary(Language lang,Dice dice) {
        dic = new HashSet<>(100000000);
        init(lang,dice);
    }

    
    /**
     * reads in target language
     * if word is in dice, put it in dictionary
     * @param lang
     * @param dice 
     */
    public void init(Language lang, Dice dice) {
        this.lang = lang;
        this.filename = "/lib/Dictionaries/" + lang.toString().toUpperCase() + "_Dictionary.txt";
        reader = new FileReader(dice);

    }

    public boolean isWord(String s) {
        return (dic.contains(s.toUpperCase()));
    }

 

    public int size() {
        return dic.size();
    }

    public HashSet<String> getDic() {
        return dic;
    }

    /**
     * reads in file and shows progress taken from
     */
    class FileReader {

        private FileReaderFrame frame;
        private JProgressBar pb;
        private Dice dice;

        public FileReader(Dice dice) {
            this.dice = dice;
            EventQueue.invokeLater(() -> {
                frame = new FileReaderFrame(lang);
                pb = frame.getbar();
                final ReadFileWorker worker = new ReadFileWorker();
                worker.addPropertyChangeListener((PropertyChangeEvent evt) -> {
                    if ("progress".equalsIgnoreCase(evt.getPropertyName())) {
                        pb.setValue(worker.getProgress());
                    }
                });

                worker.execute();
            });

        }

        class ReadFileWorker extends SwingWorker<List<String>, String> {

            @Override
            protected List<String> doInBackground() {
                System.out.print("Reading " + filename + " ...");
                try {
                    InputStream is = getClass().getResourceAsStream(filename);
                    BufferedReader br = new BufferedReader((new InputStreamReader(is, "UTF-8")));

                    int totalLines = 1;
                    while (br.readLine() != null) {
                        totalLines++;
                    }

                    if (dic != null) {
                        dic.clear();
                    }

                    is = getClass().getResourceAsStream(filename);
                    br = new BufferedReader((new InputStreamReader(is, "UTF-8")));
                    int linesRead = 0;
                    String word = "";
                    while (word != null) {
                        word = br.readLine();
                        if (dice.inPuzzle(word)) dic.add(word);
                        pb.setString(word);
                        linesRead++;
                        setProgress(Math.round(((float) linesRead / totalLines) * 100f));

                    }
                    is.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;

            }

            @Override
            protected void done() {
                System.out.println("done");
                frame.finishAndDispose();

            }

        }

    }
}
