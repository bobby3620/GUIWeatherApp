package application;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import static application.FirstStageController.locationTracker;

public class MainController implements Initializable{

	// Classes
	private static OpenWeatherMapAPI owm;

	// FXML Variables -> These are linked to the ID's inside the MainStageScene.fxml
	@FXML
	private ListView<String> myListView;
	
	@FXML 
	private Label location;
	@FXML
	private Label country;
	// Used to set the scene and then stage to display it
	private Parent root;
	private Scene scene;
	private Stage stage;

	// Regular Variables
	private int currentIndex;
	char iChar = 0x2000;
	String tempString;

	// Passes the location and application API_KEY through OWMAPI.java constructor
	public void setLocation(String location) {
		owm = new OpenWeatherMapAPI(location, "2a51d00bd4d6240e7428871625d80ff5");
	}

	// Back to FirstStageScene2 to ask the user for their location
	public void addLocation(ActionEvent event) throws Exception{
		root = FXMLLoader.load(getClass().getResource("FirstStageScene2.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// This method does is it will populate the ListView with the corresponding values from the OWM API
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		try {
			owm.openCurrentWeatherConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(locationTracker < 17){
			for(int i = 0; i <= locationTracker; i++){
				iChar += i;
				tempString = String.valueOf(iChar);
				myListView.getItems().addAll("O" + tempString);
			}
		}else{
			// This will somehow be a displayed thing for maybe like 5 seconds or something like that
			System.out.println("You have added the maximum amount locations, please delete one to add another one"); // -> There are only 17 invisible characters
		}

		// The first .get() must change each time a new location is added
		// however the second .get() stays the same with 0 and 1 because
		// that's the order of which the city then country is added inside each list
		location.setText(owm.getStoreCwListValues().get(locationTracker).get(0).toString());
		country.setText(owm.getStoreCwListValues().get(locationTracker).get(1).toString());

		// This will "listen" to any clicks on the respected item inside the listview
		// then it gets the selected item, and off that we can change the values to be set to
		// the corresponding values clicked on based off the city
		myListView.getSelectionModel().selectedItemProperty().addListener((arg01, arg11, arg2) -> {
			// Since state and country are in the same ArrayList, no need to check for both -> Displays both
			currentIndex = myListView.getSelectionModel().getSelectedIndex();
			//System.out.println("here");
			for(int i = 0; i < owm.getStoreCwListValues().size(); i++){
				for(int j = 0; j == 0; j++){
					if(currentIndex == i){
						location.setText(owm.getStoreCwListValues().get(i).get(j).toString());
						country.setText(owm.getStoreCwListValues().get(i).get(j+1).toString());
					}
				}
			}
		});
	}
}
