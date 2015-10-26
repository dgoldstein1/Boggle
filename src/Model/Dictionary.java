package Model;

import java.io.*;
import java.util.HashSet;

/**
 * Created by Dave on 01/06/2015.
 */

public class Dictionary {
    private HashSet<String> dic;
    private Language lang;
    private Alphabet gen;

    public Dictionary(Language lang){
        this.lang = lang;
        dic = new HashSet<String>(500000);
        gen = new Alphabet(lang);

        String filename = "/Dictionaries/" + lang.toString() + "_Dictionary.txt";
        try{
            InputStream is = getClass().getResourceAsStream(filename);
            BufferedReader br = new BufferedReader((new InputStreamReader(is,"UTF-8")));
            String word="";
            System.out.print("Loading " + filename + " ...");
            while(word!=null) {
                word = br.readLine();
                dic.add(word);
            }
            System.out.println("done");
        } catch(NullPointerException e){
            System.out.println("could not find file: " + filename);
            e.printStackTrace();
        } catch(UnsupportedEncodingException e){
            System.out.println("could not encode " + filename);
            e.printStackTrace();
        } catch(IOException e){
            System.out.println("IO exception");
            e.printStackTrace();
        }
    }

    public boolean isWord(String s){
        return(dic.contains(s.toUpperCase()));
    }

    /*
        returns 'random' letter based on boggle rules
        default is english, with 'boggle probability'
     */
    public String getRandomLetter(){
        return gen.getRandomLetter();
    }
}
