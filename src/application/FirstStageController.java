package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class FirstStageController{
	
	// Link to MainController for passing location
	MainController mainController = new MainController();
	
	// Variables for setting up FirstStageScene2
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	// Variables for grabbing user location
	// This links ID in FirstStageScene1.fxml TextField
	@FXML
	TextField locationTextField;
	// To keep track of the amount of locations added
	public static int locationTracker = -1;


	// Linked to FirstStageScene1 onAction = "#switchToScene2"
	// This listens to a click, and will switch to FirstStageScene2.fxml
	// This page asks the user to enter a location
	public void switchToScene2(ActionEvent event) throws Exception{
		root = FXMLLoader.load(getClass().getResource("FirstStageScene2.fxml"));
		// This switches to the FirstStageScene2.fxml file
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		// And then we add a new scene, then set the new scene, then show the new scene
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	// Linked to FirstStageScene2 onAction = "#Ok"
	// This grabs the text that's in the TextField and stored in location
	// Then we open up the MainStageScene
	// Where we display the weather information for the user
	public void Ok(ActionEvent event) throws Exception{
		locationTracker++;
		String location = locationTextField.getText();
		mainController.setLocation(location);
		root = FXMLLoader.load(getClass().getResource("MainStageScene.fxml"));
		// This switches to the FirstStageScene2.fxml file
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		// And then we add a new scene, then set the new scene, then show the new scene
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
