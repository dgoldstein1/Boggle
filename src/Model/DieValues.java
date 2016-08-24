/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author dave specified values for Die
 */
public class DieValues {

    String[][] english, spanish, italian, french;

    public DieValues() {
        english = new String[][]{
            {"Q", "B", "Z", "J", "X", "K"},
            {"T", "O", "U", "O", "T", "O"},
            {"O", "V", "W", "R", "G", "B"},
            {"A", "A", "A", "F", "S", "R"},
            {"A", "U", "M", "E", "E", "G"},
            {"H", "H", "L", "R", "D", "O"},
            {"N", "H", "D", "T", "H", "O"},
            {"L", "H", "N", "R", "O", "D"},
            {"A", "F", "A", "I", "S", "R"},
            {"Y", "I", "F", "A", "S", "R"},
            {"T", "E", "L", "P", "C", "I"},
            {"S", "S", "N", "S", "E", "U"},
            {"R", "I", "Y", "P", "R", "H"},
            {"D", "O", "R", "D", "L", "N"},
            {"C", "C", "W", "N", "S", "T"},
            {"T", "T", "O", "T", "E", "M"},
            {"S", "C", "T", "I", "E", "P"},
            {"E", "A", "N", "D", "N", "N"},
            {"M", "N", "N", "E", "A", "G"},
            {"U", "O", "T", "O", "W", "N"},
            {"A", "E", "A", "E", "E", "E"},
            {"Y", "I", "F", "P", "S", "R"},
            {"E", "E", "E", "E", "M", "A"},
            {"I", "T", "I", "T", "I", "E"},
            {"E", "T", "I", "L", "I", "C"}
        };

        spanish = new String[][]{
            {"Q", "B", "Z", "J", "X", "L"},
            {"T", "O", "U", "O", "T", "O"},
            {"O", "V", "C", "R", "G", "R"},
            {"A", "A", "A", "F", "S", "R"},
            {"A", "U", "M", "E", "E", "O"},
            {"E", "H", "L", "R", "D", "O"},
            {"N", "H", "D", "T", "H", "O"},
            {"L", "H", "N", "R", "O", "D"},
            {"A", "D", "A", "I", "S", "R"},
            {"U", "I", "F", "A", "S", "R"},
            {"T", "E", "L", "P", "C", "I"},
            {"S", "S", "N", "S", "E", "U"},
            {"R", "I", "Y", "P", "R", "H"},
            {"D", "O", "R", "D", "L", "N"},
            {"C", "C", "N", "N", "S", "T"},
            {"T", "T", "O", "T", "E", "M"},
            {"S", "C", "T", "I", "E", "P"},
            {"E", "A", "N", "D", "N", "N"},
            {"M", "N", "N", "E", "A", "G"},
            {"U", "O", "T", "O", "N", "N"},
            {"A", "E", "A", "E", "E", "H"},
            {"Y", "I", "F", "P", "S", "R"},
            {"E", "E", "E", "E", "M", "A"},
            {"I", "T", "A", "T", "I", "E"},
            {"E", "T", "I", "L", "A", "C"}
        };

        italian = new String[][]{
            {"A", "A", "E", "I", "O", "T"},
            {"A", "C", "F", "I", "O", "R"},
            {"E", "E", "F", "H", "I", "S"},
            {"E", "L", "P", "S", "T", "U"},
            {"A", "F", "G", "I", "P", "R"},
            {"A", "B", "I", "L", "R", "T"},
            {"A", "D", "E", "N", "V", "Z"},
            {"E", "G", "I", "N", "T", "V"},
            {"A", "B", "C", "I", "M", "O"},
            {"B", "E", "F", "L", "N", "O"},
            {"A", "B", "M", "O", "O", "Q"},
            {"A", "I", "M", "O", "R", "S"},
            {"E", "G", "L", "N", "O", "U"},
            {"A", "B", "F", "Q", "S", "Z"},
            {"C", "D", "I", "L", "M", "O"},
            {"A", "C", "D", "E", "M", "P"},
            {"C", "E", "N", "O", "T", "U"},
            {"E", "H", "I", "N", "R", "S"},
            {"A", "C", "G", "I", "P", "T"},
            {"C", "I", "L", "M", "O", "R"},
            {"A", "C", "E", "L", "R", "S"},
            {"D", "E", "N", "O", "S", "T"},
            {"E", "I", "L", "O", "R", "U"},
            {"A", "C", "G", "P", "S", "V"},
            {"D", "I", "M", "O", "T", "V"}
        };

        french = new String[][]{
            {"E", "T", "U", "K", "N", "O"},
            {"E", "C", "G", "T", "I", "N"},
            {"D", "E", "C", "A", "M", "P"},
            {"I", "E", "L", "R", "U", "W"},
            {"E", "H", "I", "F", "S", "E"},
            {"R", "E", "C", "A", "L", "S"},
            {"E", "N", "T", "D", "O", "S"},
            {"O", "F", "X", "R", "I", "A"},
            {"N", "A", "V", "E", "D", "Z"},
            {"E", "I", "O", "A", "T", "A"},
            {"G", "L", "E", "N", "Y", "U"},
            {"B", "M", "A", "Q", "J", "O"},
            {"T", "L", "I", "B", "R", "A"},
            {"S", "P", "U", "L", "T", "E"},
            {"A", "I", "M", "S", "O", "R"},
            {"E", "N", "H", "R", "I", "S"}
        };
    }

    public String[] values(int id, Language l) {
        if (id > 25){
            System.out.println("id = " + id);
            System.exit(1);
        }

        //existing boggle languages
        switch (l) {
            case ENGLISH:
                return english[id];
            case SPANISH:
                return spanish[id];
            case ITALIAN:
                return italian[id];               
            case FRENCH:
                if (id > 16){
                    System.out.println("only 4x4 supported in french");
                    System.exit(1);
                }
                return french[id];

        }

        //nonexisting boggle languages
        String[] alphabet = null;
        switch (l) {
            case ARABIC:
                alphabet = new String[]{"ا", "ا", "ا", "ل", "ن", "م",
                    "ي", "ي", "و", "و", "و", "ه", "ب", "ر", "ع", "ء", "ف", "ق", "د", "ت", "س", "ك", "ح", "ج", "خ", "ش", "ز", "ط", "ض", "ص", "غ", "ظ", "ح", "ح"};
            case AFRIKAANS:
                alphabet = new String[]{"A", "B", "C", "D", "E", "F", "G",
                    "H", "I", "J", "K", "L", "M", "N", "O", "P", "Qu", "R", "S",
                    "T", "U", "V", "X", "Y", "Z", "É", "Ê", "Ò"
                };
                break;
            case CROATIAN:
                alphabet = new String[]{"A", "B", "C", "D", "E", "F", "G",
                    "H", "I", "J", "K", "L", "M", "N", "O", "P", "Qu", "R", "S",
                    "T", "U", "V", "X", "Y", "Z", "Dž", "Č", "Ć", "Đ", "Lj", "Nj", "Š", "Ž", "Æ"
                };
                break;
            case RUSSIAN:
                alphabet = new String[]{"А", "Б", "В", "Г", "Д", "Е", "Ж",
                    "З", "И", "Й", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т",
                    "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ", "Ъ", "Ь", "Ю", "Я"
                };
                break;
            case GERMAN:
                alphabet = new String[]{
                    "Ä", "Ö", "Ü", "ß",
                    "A", "B", "C", "D", "E", "F", "G",
                    "H", "I", "J", "K", "L", "M", "N", "O", "P", "Qu", "R", "S",
                    "T", "U", "V", "X", "Y", "Z"
                };
                break;
            case LATIN:
                alphabet = new String[]{
                    "A", "B", "C", "D", "E", "F", "G",
                    "H", "I", "J", "K", "L", "M", "N", "O", "P", "Qu", "R", "S",
                    "T", "U", "V", "X", "Y", "Z"
                };
                break;
            case ITALIAN:
                alphabet = new String[]{};
                break;
            default:
                break;
        }
        String[] temp = new String[6];
        for (int i = 0; i < 6; i++) {
            temp[i] = alphabet[(int) (Math.random() * 6)];
        };
        return temp;

    }

}
