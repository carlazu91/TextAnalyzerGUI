module TextAnalyzer {
	requires javafx.controls;
	requires javafx.base;
	requires org.junit.jupiter.api;
	
	opens application to javafx.graphics, javafx.fxml;
}
