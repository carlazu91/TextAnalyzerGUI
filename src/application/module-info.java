module TextAnalyzer {
	requires javafx.controls;
	requires javafx.base;
	requires org.junit.jupiter.api;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml;
}
