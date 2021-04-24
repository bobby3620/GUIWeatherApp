package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartApp extends Application{
	@Override
	public void start(Stage stage)  throws Exception{
		// This grabs the corresponding fxml file
		Parent beginStage = FXMLLoader.load(getClass().getResource("FirstStageScene1.fxml"));
		// This sets the scene to the fxml file
		Scene beginStageScene = new Scene(beginStage);
		// Links application.css to the scene
		beginStageScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		// These set and show the scene which is linked to the fxml file or firstStage in this case
		stage.setScene(beginStageScene);
		stage.show();

		char x = 0x0031;
		String s = String.valueOf(x);
		x += 1;
		s = String.valueOf(x);

	}
	
	public static void main(String[] args) {
		launch(args);

	}
}
