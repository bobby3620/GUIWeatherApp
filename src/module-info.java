module GraphicalUserInterface {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.graphics;
    requires org.json;

    opens application to javafx.graphics, javafx.fxml;
}
