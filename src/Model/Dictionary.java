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
        dic = new HashSet<>(500000);
        setLang(lang);        
    }
    
    public void setLang(Language lang){
        this.lang = lang;
        gen = new Alphabet(lang);
        String filename = "/lib/Dictionaries/" + lang.toString() + "_Dictionary.txt"; 
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
            System.exit(1);
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
    
    public Language lang(){
        return lang;
    }
}
