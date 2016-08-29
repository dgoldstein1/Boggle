package Model;

import View.FileReaderFrame;
import java.io.FileReader;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;
import java.io.*;
import java.util.HashSet;
import java.util.List;
import javax.swing.JFrame;
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

    public Dictionary(Language lang) {
        dic = new HashSet<>(500000);
        setLang(lang);
    }

    public void setLang(Language lang) {

        this.lang = lang;
        this.filename = "/lib/Dictionaries/" + lang.toString() + "_Dictionary.txt";        
        reader = new FileReader();

    }

    public boolean isWord(String s) {
        return (dic.contains(s.toUpperCase()));
    }

    public Language lang() {
        return lang;
    }

    /**
     * reads in file and shows progress taken from
     */
    class FileReader {

        private FileReaderFrame frame;

        public FileReader() {
            EventQueue.invokeLater(() -> {
                frame = new FileReaderFrame(lang);
                final JProgressBar pb = frame.getbar();
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
                try {
                    InputStream is = getClass().getResourceAsStream(filename);
                    BufferedReader br = new BufferedReader((new InputStreamReader(is, "UTF-8")));
                    System.out.print("Reading " + filename + " ...");

                    int totalLines = 1;
                    while (br.readLine() != null) {
                        totalLines++;
                    }
                    
                    is = getClass().getResourceAsStream(filename);
                    br = new BufferedReader((new InputStreamReader(is, "UTF-8")));
                    int linesRead = 0;
                    String word = "";
                    while (word != null) {
                        word = br.readLine();
                        dic.add(word);
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
