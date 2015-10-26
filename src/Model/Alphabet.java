package Model;

/**
 * Created by Dave on 01/06/2015.
 * to be used by Dictionary. Weighted random letter generator
 */
public class Alphabet {
    private String[] alphabet;

    public Alphabet(Language lang){

        //Weighted alphabets

        if(lang == Language.ENGLISH){
            alphabet = new String[]{"A", "A", "A", "A", "A", "A",
                    "B", "B", "C", "C", "D", "D", "D", "E", "E", "E", "E", "E",
                    "E", "E", "E", "E", "E", "E", "F", "F", "G", "G", "H", "H",
                    "H", "H", "H", "I", "I", "I", "I", "I", "I", "J", "K", "L",
                    "L", "L", "L", "M", "M", "N", "N", "N", "N", "N", "N", "O",
                    "O", "O", "O", "O", "O", "P", "P", "Qu", "R", "R", "R", "R",
                    "R", "S", "S", "S", "S", "S", "S", "T", "T", "T", "T", "T",
                    "T", "T", "T", "T", "U", "U", "U", "V", "V", "X", "Y", "Y", "Y",
                    "Z"
            };
        }

        if(lang == Language.ARABIC) {
            alphabet = new String[]{"ا","ا","ا","ل","ن","م",
                    "ي","ي","و","و","و","ه","ب","ر","ع","ء","ف","ق","د","ت","س","ك","ح"
                    ,"ج","خ","ش","ز","ط","ض","ص","غ","ظ","ح","ح"};
        }
        else if(lang == Language.FRENCH){
            alphabet = new String[] {"A","A","A","A","A"
                    ,"A","A","B","B","C","C","C","D","D","D","D"
                    ,"E","E","E","E","E","E","E","E","E","E","E"
                    ,"E","E","F","F","G","H","I","I","I","I","I"
                    ,"I","I","I","J","K","L","L","L","L","L","M"
                    ,"M","M","N","N","N","N","N","N","N","O","O"
                    ,"O","O","O","P","P","Qu","Qu","V","V","W","X"
                    ,"Y","Z","A","E","E","E","U"
            };
        }
        else if(lang == Language.SPANISH){
            alphabet = new String[] {"A","A","A","A","A","A"
                    ,"A","A","A","A","A","A","B","B","C","C","C","C"
                    ,"D","D","D","D","D","E","E","E","E","E","E","E"
                    ,"E","E","E","E","E","E","F","G","G","H","I","I"
                    ,"I","I","I","I","H","J","L","L","L","L","L","M"
                    ,"M","M","N","N","N","N","N","N","N","O","O","O"
                    ,"O","O","O","O","O","O","P","P","P","Qu","Qu"
                    ,"R","R","R","R","R","R","R","S","S","S","S","S"
                    ,"S","S","T","T","T","T","T","U","U","U","V","Z"
            };
        }

        //nonWeighted Alphabets

        else if(lang == Language.AFRIKAANS){
            alphabet = new String[] {"A","B","C","D","E","F","G",
                    "H","I","J","K","L","M","N","O","P","Qu","R","S",
                    "T","U","V","X","Y","Z","É","Ê","Ò"
            };
        }
        //Gaj Latin
        else if(lang == Language.CROATIAN){
            alphabet = new String[] {"A","B","C","D","E","F","G",
                    "H","I","J","K","L","M","N","O","P","Qu","R","S",
                    "T","U","V","X","Y","Z","Dž","Č","Ć","Đ","Lj","Nj","Š","Ž","Æ"
            };
        }
        else if(lang==Language.RUSSIAN) {
            alphabet = new String[]{"А", "Б", "В", "Г", "Д", "Е", "Ж",
                    "З", "И", "Й", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т",
                    "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ", "Ъ", "Ь", "Ю", "Я"
            };
        }

        else if(lang==Language.GERMAN){
            alphabet = new String[] {
                    "Ä","Ö","Ü","ß",
                    "A","B","C","D","E","F","G",
                    "H","I","J","K","L","M","N","O","P","Qu","R","S",
                    "T","U","V","X","Y","Z"
            };
        }
        else if(lang==Language.LATIN){
            alphabet = new String[] {
                    "A","B","C","D","E","F","G",
                    "H","I","J","K","L","M","N","O","P","Qu","R","S",
                    "T","U","V","X","Y","Z"
            };
        }
        else if (lang==Language.ITALIAN){
            alphabet = new String[] {

            };
        }


    }

    public String getRandomLetter(){
        int n = (int) (Math.random() * alphabet.length);
        return alphabet[n];
    }


}
