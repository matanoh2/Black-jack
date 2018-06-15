/**
 * Sample Skeleton for '21Scene.fxml' Controller Class
 */

package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class ControllerBJ {
    @FXML // fx:id="nameLabel"
    private Label nameLabel; // Value injected by FXMLLoder

    @FXML // fx:id="hitMeBtn"
    private Button hitMeBtn; // Value injected by FXMLLoader

    @FXML // fx:id="StandBtn"
    private Button StandBtn; // Value injected by FXMLLoader

    @FXML // fx:id="sldr"
    private Slider sldr; // Value injected by FXMLLoader

    @FXML // fx:id="playerLabel"
    private Label playerLabel; // Value injected by FXMLLoader

    @FXML // fx:id="dealerLabel"
    private Label dealerLabel; // Value injected by FXMLLoader
    @FXML // fx:id="walletTF"
    private TextField walletTF; // Value injected by FXMLLoader
    
    @FXML // fx:id="betBtn"
    private Button betBtn; // Value injected by FXMLLoader
  
    @FXML // fx:id="sldrLabel"
    private Label sldrLabel; // Value injected by FXMLLoader


    public void initialize() {
    	nameLabel.setText(StartController.getName());
    	sldrLabel.setText(Integer.valueOf((int)sldr.getMin())+"$");
    	GamePlay.initilize(StartController.getName(), playerLabel, dealerLabel, walletTF, StandBtn, hitMeBtn, sldr);
    	sldr.valueProperty().addListener(
    			new ChangeListener<Number>() {
    				@Override
    				public void changed(ObservableValue<? extends Number> ov, Number oldValue,Number newValue) {
    					sldrLabel.setText(String.valueOf(newValue.intValue())+"$");
    				}
				}
    			);
    }
       
    @FXML
    void hitMe(ActionEvent event) {
    	GamePlay.Game(playerLabel, dealerLabel, walletTF, 1, StandBtn, hitMeBtn, sldr, betBtn);
    }

    @FXML
    void stand(ActionEvent event) {
    	GamePlay.Game(playerLabel, dealerLabel, walletTF, 0, StandBtn, hitMeBtn, sldr, betBtn);
    }
    
    @FXML
    void betPressed(ActionEvent event) {
    	int temp;
    	playerLabel.setVisible(true);
    	dealerLabel.setVisible(true);
    	StandBtn.setDisable(false);
    	hitMeBtn.setDisable(false);
    	temp=Integer.valueOf(walletTF.getText().substring(0, walletTF.getText().length()-1))-Integer.valueOf((int)sldr.getValue());
    	walletTF.setText(Integer.toString(temp)+"$");
    	betBtn.setDisable(true);
    	sldr.setDisable(true);
    }
}
