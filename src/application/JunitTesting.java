package application;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class JunitTesting {
	  @TempDir
	  static Path TempDir;
	  	  
	  //Testing URL validation - making sure URL is valid and working
	  @Test
	  @Order(1)
	  void urlIsValid() throws MalformedURLException {
		  URL testURL = new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm");
		  
		  assertTrue(testURL.getAuthority().equals("www.gutenberg.org"), "This URL has a valid Authority");
	  }
	  
	  //Simple test to write file and verify it exist and the lines written match what is in the file 
	  @Test
	  @Order(2)
	  void WriteFile() throws IOException{
	    Path poem = TempDir.resolve("poem.txt");

	    List<String> lines = Arrays.asList("nevermore", "evermore", "The Raven"); 
	    Files.write(poem, lines);
	      
	      assertAll(
	              () -> assertTrue(Files.exists(poem), "File should exist"),
	              () -> assertLinesMatch(lines, Files.readAllLines(poem)));
	  }
	  
	  // Testing Exception for when method is missing or null HashMap parameter
	  @Test
	  @Order(3)
	  void exceptionTesting() {
		    Throwable exception = assertThrows(NullPointerException.class, () -> TextAnalyzer.CountWords(null));
		    assertTrue(true, exception.getMessage());
		}
}
