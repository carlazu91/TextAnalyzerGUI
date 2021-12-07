package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.sql.*;

/**
 * 
 * @author czumaran
 */

public class TextAnalyzer { 
	/**
	 * This method produces a hashmap with the words and the number of times they are used within the poem
	 * @param words with String and Integer parameters 
	 * @throws FileNotFoundException if the file does not exist
	 * @throws IOException if the file could not be created or there was an error during the creation
	 */
	
    //Method used to read .txt file after formatted and count words and their occurrences 
    public static void CountWords(Map<String, Integer> words) throws FileNotFoundException, IOException{
    	//Variable
    	int count = 0;
    	
    	//Attempt to create file and read URL using scanner
        try (FileWriter fileWriter = new FileWriter("poem.txt")) {
        	URL url = new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm");
    		InputStream in = url.openStream();
    		Scanner scan = new Scanner(in);
    		
    		//Scanner will read each line and print it to the .txt file 
    		while(scan.hasNextLine()) {
    			String line = scan.nextLine();
    			if(count > 80 && count < 240) {
    				line = line.replaceAll("\\<.*?\\>", "");
    				line = line.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");
    				line = line.replaceAll("&mdash;"," ");
    				line = line.replaceAll("[^\\p{L}\\p{Z}]","");
    				fileWriter.write(line);
    			}
    			count++;
    		}
    		scan.close();
          }
        
        //File is being read and words are counted and added to the Hashmap 
    	try (FileReader fileReader = new FileReader("poem.txt");
    			Scanner scanner = new Scanner(fileReader)){
    		while (scanner.hasNext()) {
    			String word = scanner.next().toUpperCase();
		    	Integer counter = words.get(word);
		    	
				if(counter != null)
					counter++;
				else
					counter = 1;
				
				words.put(word, counter);
				
    		}
        } 
    } 
}
