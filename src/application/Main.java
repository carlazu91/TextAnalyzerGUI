package application;
	
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.sql.*;

/** 
 * @author czumaran
 */
public class Main extends Application {
	Stage window;
	
	/**
	 * This is the main method used to execute the program 
	 * 
	 * @param args will output the GUI using JavaFX
	 * 
     */
	
	public static void main(String[] args) {
		launch(args);	
	}
	
	/**
	 * This method is used to create the GUI for JavaFX
	 * 
	 * @param primaryStage will set the Windows stage for GUI
	 * @throws FileNotFoundException if the file does not exist
	 * @throws IOException if the file could not be created or there was an error during the creation
	 * 
     */
	
	@SuppressWarnings("unchecked")
	@Override
	
	public void start(Stage primaryStage) throws FileNotFoundException, IOException {
		//Setting Title to Window
		window = primaryStage;
		window.setTitle("Text Analyzer");
		
		//Close Program Button and from "x"
		window.setOnCloseRequest(e -> {
			e.consume();
			closeProgram();
		});
		
		
		//VBox - Top Section includes Label 
		VBox top = new VBox();
		top.setPadding(new Insets (10,0,20,0));
		
		Label l1 = new Label();
		l1.setText("The text being analyzed:\nThe Raven by Edgar Allan Poe");
		
		top.getChildren().addAll(l1);
		
		//HBox - Bottom Section includes URL and Exit Button
		HBox bottom = new HBox(40);
		bottom.setPadding(new Insets (10,0,0,0));
		
		Hyperlink link = new Hyperlink();
		link.setText("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm");
		link.setOnAction(e -> getHostServices().showDocument(link.getText()));
		
		Button close = new Button("Exit");
		close.setOnAction(e -> closeProgram());
		
		bottom.getChildren().addAll(link, close);
		
		
		//TableView - Center Section includes the Hashmap to table columns
		Map<String, Integer> words = new HashMap<String, Integer>();
		TextAnalyzer.CountWords(words);
		
	 	/* DB portion - could not get it working properly, below shows the code 
	 	 * for the Hashmap to be read and add those values to the DB, then read the DB and the values added 
	 	// db parameters - 
    	String url       = "jdbc:mysql://localhost:3306/word occurrences";
    	String user      = "root";
    	String password  = "mypassword123";
    	String sql = "INSERT INTO word(word) VALUES(?)";


    	try(Connection conn = DriverManager.getConnection(url, user, password);
    			 PreparedStatement pst = null;) {
    		
    		//Would insert Hasmap Values
    		for (Map.Entry<String, Integer> next : words) {
    			  pst.executeUpdate("INSERT INTO table (Value, Key) VALUES("+next.getValue()+",'"+next.getKey()+"');");
    			}
    		
    		//Would read the database values
        	ResultSet resSet = pst.executeQuery("SELECT * FROM table");

        	while (resSet.next()) {
        	  words.put(resSet.getString("Value"), resSet.getInt("Key"));
        	}
           
    	} catch(SQLException e) {
    	   System.out.println(e.getMessage());
    	}
    	*/
			
		TableColumn<Map.Entry<String, Integer>, String> column1 = new TableColumn<>("Key");
        column1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Integer>, String>, ObservableValue<String>>() {

            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, Integer>, String> p) {
                return new SimpleStringProperty(p.getValue().getKey());
            }
        });

        TableColumn<Map.Entry<String, Integer>, Number> column2 = new TableColumn<>("Value");
        column2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Integer>, Number>, ObservableValue<Number>>() {

            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Map.Entry<String, Integer>, Number> p) {
                return new SimpleIntegerProperty(p.getValue().getValue());
            }
        });
        
        ObservableList<Entry<String,Integer>> items = FXCollections.observableArrayList(words.entrySet());
        final TableView<Map.Entry<String,Integer>> table = new TableView<>(items);
        
        column2.setSortType(TableColumn.SortType.DESCENDING);
        table.getColumns().setAll(column1, column2);
        table.getSortOrder().addAll(column2);
		
		//BorderPane Layout
		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(12,12,12,12));
		
		bp.setTop(top);
		bp.setCenter(table);
		bp.setBottom(bottom);
			
		//Set Scene
		Scene s = new Scene(bp, 430, 600);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	
	/**
	 * This method will execute the Confirm Box class to determine the boolean to exit application 
	 * 
     */
	private void closeProgram() {
		Boolean answer = ConfirmBox.display("Confirm", "Are you sure you want to exit?");
		if(answer) //if true
			window.close();
		
	}
}




  
    

