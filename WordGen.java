//@Riku Fukumori

import java.util.Scanner;

//Main class which takes in a string of text and generates a text of that style
public class WordGen {
    public static void main(String args[]) {
        if (args.length == 0) {
	    // no args, so print usage line and do nothing else
            System.out.println("Usage: java WordGen <level>");
        } else {
	    // convert first argument to k
            int k = Integer.parseInt(args[0]);
            Scanner in = new Scanner(System.in);
	    //use textBuffer to see and read text properly in terminal
            StringBuffer textBuffer = new StringBuffer();
            while (in.hasNextLine()) {
                String line = in.nextLine();
                textBuffer.append(line);
                textBuffer.append("\n");
            }
            String text = textBuffer.toString();
	    //calls Table class
            Table table = new Table();
	    //sets user inputted k to new k value using setK method from Table
            table.setK(k);

	    //feed text into the table using the add method from Table class
            for ( int i = 0 ; i < text.length()-k ; i++) {
                table.add(text.substring(i , i+k+1 ));
            }
	    //gets random starting string for generated text from the getStart Table method
	    String start = table.getStart();
	    //loops through generating words from the table strings and their corresponding frequencies
	    //once a subsequent letter is picked, then looks at next k letters ending at the letter just placed
            for ( int i = 0 ; i < 500 ; i++){
		start = start + table.pickNext(start.substring(start.length()-k,start.length() ));
            }
            System.out.println(start);
        }
    }
}
