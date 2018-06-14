//Fiona Yonkman Lab2 FrequencyListClass

import structure5.*;
import java.util.Random;

public class FrequencyList {
    //global variables
    Vector list;
    Random r;
    private int total;

    //constructor which initializes global variables
    public FrequencyList( ) {
        list = new Vector(60);
        r = new Random();
        total = 0;
    }

    //method which picks next letter based on the probability of it being
    //a certain letter
    public String pickNext() {
        int rand = r.nextInt(total);
        int count = 0;
	//go through the vector and return letter based on the list of the same letter
        for ( int i = 0 ; i < list.size() ; i++ ){
            Association temp = (Association)list.get(i);
            if ( rand < (int)temp.getValue() + count ) {
                return (String) temp.getKey();
            } else {
                count = count + (int)temp.getValue();
            }
        }
        throw new RuntimeException("Should not reach here");
    }


    //updates list with letter and increases frequency
    public void addLetter( String letter ) {
        Vector list = this.list;
        int i;
	//if the letter is already in the frequency list, 
	//increases the association's value by one
        for ( i = 0 ; i < list.size() ; i++ ) {
            Association wordInfo = (Association) list.get(i);
            String character = (String) wordInfo.getKey();
            if (character.equals(letter)) {
                int f = (int) wordInfo.getValue();
                wordInfo.setValue(f + 1);
                this.total++;
                break;
            }
        }
	//if letter not already in the vector, creates a new association
	//with that letter and a frequency of one
        if ( i == list.size()) {
            list.add(new Association(letter, 1));
            this.total++;
        }
    }


    //prints the list of frequency pairs
    public String toString() {
        String string = "";
        for ( int i = 0; i < list.size() ; i++ ){
            Association wordInfo = (Association) list.get(i);
            string = string + "(" + wordInfo.getKey() + ", " + wordInfo.getValue() + "), ";
        }
        return "{ " + string.substring(0,string.length()-2) + " }";
    }
}
