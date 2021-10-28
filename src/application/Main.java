package application;
	
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


public class Main extends Application {
	Stage window;
	
	public static void main(String[] args) throws Exception{
		TextAnalyzer.WriteFileContents();
		launch(args);	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) throws Exception {
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

	private void closeProgram() {
		Boolean answer = ConfirmBox.display("Confirm", "Are you sure you want to exit?");
		if(answer) //if true
			window.close();
		
	}
}




  
    

