package application;

import java.io.IOException;

import javafx.event.ActionEvent;

/**
 * Sample Skeleton for 'StartController.fxml' Controller Class
 */

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class StartController {
	static String nameStr = null;
	static int s=32;
    @FXML // fx:id="playerName"
    private TextField playerName; // Value injected by FXMLLoader

    @FXML // fx:id="playBtn"
    private Button playBtn; // Value injected by FXMLLoader

    @FXML // fx:id="errorLabel"
    private Label errorLabel; // Value injected by FXMLLoader
    @FXML
    public void initialize() {
    	playBtn.setDisable(true);
    }
       
    @FXML
    void play(ActionEvent event) {
		try {
			nameStr = playerName.getText();
			if(nameStr.charAt(0)>='a'&&nameStr.charAt(0)<='z')
				nameStr = nameStr.substring(0, 1).toUpperCase() + nameStr.substring(1);
		    Stage stageMain = (Stage) playBtn.getScene().getWindow();
	    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("21Scene.fxml"));
	    	Parent root1;
			root1 = (Parent) fxmlLoader.load();
	    	Stage stage = new Stage();
	    	stage.setScene(new Scene(root1));  
	    	stage.setTitle("21");
	    	stage.show();
	    	stageMain.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void onKeyTyped(KeyEvent event) {
		if (playerName.getText().trim().equals("")) {
			playBtn.setDisable(true);
			errorLabel.setVisible(true);
			errorLabel.setText("You must enter a name!");
		}
		else {
			playBtn.setDisable(false);
			errorLabel.setVisible(false);
			}
    }

    public static String getName() {
    	return nameStr;
    }

}
