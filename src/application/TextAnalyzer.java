package application;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

public class TextAnalyzer { 
	
	//Method used to read URL content and write to .txt file
    protected static void WriteFileContents() throws IOException {
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
        }
    
    //Method used to read .txt file after formatted and count words and their occurrences 
    static void CountWords(Map<String, Integer> words) throws Exception{
    	try (FileReader fileReader = new FileReader("poem.txt");
    			Scanner scanner=new Scanner(fileReader)){
    		while (scanner.hasNext()) {
    			String word = scanner.next().toUpperCase();
		    	Integer count = words.get(word);
		    	
				if(count != null)
					count++;
				else
					count = 1;
				
				words.put(word, count);
            }
        }    	
    }
}
