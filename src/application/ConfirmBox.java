package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** 
 * @author czumaran
 */

public class ConfirmBox {
	
	static boolean answer; 
	
	/**
	 * The method is used output a windows where user will need to choose whether they would like to exit GUI application 
	 * @param title is the title of the window 
	 * @param message is the message that will output on the window for user to read
	 * @return answer - the value of the boolean depending on users choice
	 */
	
	public static boolean display(String title, String message) {
		
		Stage window = new Stage();
		
		//Stage
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		window.setMinHeight(200);
		
		//label 
		Label l = new Label();
		l.setText(message);
		
		//Create the two buttons
		Button yb = new Button("Yes");
		Button nb = new Button("No");
		
		yb.setOnAction(e -> {
			answer = true;
			window.close();
		});
		
		nb.setOnAction(e -> {
			answer = false;
			window.close();
		});
				
		//Layout
		VBox layout = new VBox(10);
		layout.getChildren().addAll(l, yb, nb);
		layout.setAlignment(Pos.CENTER);
		
		
		Scene s = new Scene(layout);
		window.setScene(s);
		window.showAndWait();
	
		return answer;
	}
	
}
