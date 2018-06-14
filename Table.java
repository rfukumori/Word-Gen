import structure5.*;
import java.util.Random;

//class which updates a table of a k letter string with its letter and frequency pair
public class Table {
    //global variables
    Vector table;
    public int k;

    //constructor which initializes table vector
    public Table(){
        table = new Vector(1000);
    }
    
    //method which sets k to be called in WordGen class
    public int setK( int n){
        this.k = n;
        return k;
    }
    
    //method which adds a k letter string to the table vector
    void add(String str){
        Vector table = this.table;
        boolean found = false;
	//if the k letter string is found in the text, update the frequency list 
        for( int i = 0 ; i < table.size() ; i++ ) {
            Association letters = (Association) table.get(i);
            String characters = (String) letters.getKey();
            FrequencyList fList = (FrequencyList) letters.getValue();
            if( str.substring(0,k).equals(characters)){
                fList.addLetter( str.substring(k,k+1));
                found = true;
                break;
            }
        }
	//if k letter string is not already in the table, add a new association to the table vector
        if( !found ){
            FrequencyList fList = new FrequencyList();
            fList.addLetter(str.substring(k,k+1));
            table.add(new Association(str.substring(0,k), fList ));
        }
    }
    
    //finds frequency list and picks a random letter using the frequency list class's pickNext method
    String pickNext(String str) {
        boolean found = false;
        Random r = new Random();
        String letter = "";
        for( int i = 0 ; i < table.size() ; i++){
            Association letters = (Association) table.get(i);
            String characters = (String) letters.getKey();
            FrequencyList fList = (FrequencyList) letters.getValue();
            if ( str.equals(characters)){
                letter = fList.pickNext();
		found = true;
                break;
            }
        }
	//if the string is at the end of the text, then this picks a random two letter string from the table 
	if (!found){
	    Association letters = (Association) table.get(r.nextInt(table.size() ));
	    letter = (String) letters.getKey();
	}
		
        return letter;
  
    }

    //picks a random k letter string from the table to serve which is called by WordGen to start the generated text
    public String getStart(){
	Random r = new Random();
	Association letters = (Association) table.get(r.nextInt(table.size()));
	String start = (String) letters.getKey();
	return start;
    }

    //print table as a list of k letter strings and their letter and frequency pairs
    public String toString(){
        String string = "";
        for ( int i = 0 ; i < table.size() ; i++ ){
            Association letters = (Association) table.get(i);
            String characters = (String) letters.getKey();
            FrequencyList fList = (FrequencyList) letters.getValue();
            string = string + "[ " + characters + ", " + fList.toString() + " ], ";
        }
        return string.substring(0 , string.length()-2);
    }
}
