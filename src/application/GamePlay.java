package application;


import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class GamePlay {
	private static final int STAND_PAT_POINT = 17;
	private static Deck deck;
	private static Player player1;
	private static Hand dealersHand;
	private static char b='\u25A0';
	
	public static void initilize(String name, Label playerLabel, Label dealerLabel, TextField walletTF, Button standBtn, Button hitMeBtn, Slider sldr) {
		playerLabel.setVisible(false);
		dealerLabel.setVisible(false);
		standBtn.setDisable(true);
		hitMeBtn.setDisable(true);
		sldr.setMax(100);
		
		deck  = new Deck();
		player1 = new Player(name, Integer.valueOf((int)sldr.getMax()), deck);
		dealersHand = new Hand(deck);
		dealerLabel.setText(dealersHand.firstCard().toString()+", "+b);
	    playerLabel.setText(player1.toString());
	    walletTF.setText(Integer.toString(player1.getAmuont())+"$");
	}
	public static void ResetGame(Label playerLabel, Label dealerLabel, TextField walletTF, Button standBtn, Button hitMeBtn, Slider sldr, int resultType, Button betBtn) {
		dealersHand = new Hand(deck);
		player1.newHand(deck);
		playerLabel.setVisible(false);
		dealerLabel.setVisible(false);
		standBtn.setDisable(true);
		
		hitMeBtn.setDisable(true);
		if (resultType == 1)
			player1.setAmuont(player1.getAmuont()+Integer.valueOf((int)sldr.getValue()));
		else if (resultType == 0 ) {
			player1.setAmuont(player1.getAmuont()-Integer.valueOf((int)sldr.getValue()));
			if (player1.getAmuont() <= 0) {
				Alert alert = new Alert(AlertType.ERROR);
		        alert.setTitle("You lost");
		        alert.setHeaderText(null);
		        alert.setContentText("You lost all your money, go home loser.");
		        alert.showAndWait();
		        Platform.exit();
			}
		}
		sldr.setMax(player1.getAmuont());
		sldr.setValue(2);
		dealerLabel.setText(dealersHand.firstCard().toString()+", "+b);
	    playerLabel.setText(player1.toString());
	    walletTF.setText(Integer.toString(player1.getAmuont())+"$");
    	betBtn.setDisable(false);
    	sldr.setDisable(false);
}
	
	static void Game(Label playerLabel, Label dealerLabel, TextField walletTF, int flag, Button standBtn, Button hitMeBtn, Slider sldr, Button betBtn)  {
	String msg = null;
	if (flag == 1) {
	    if (player1.getHand() < 21)
	    {
	    	   player1.hit(deck);
	    	   playerLabel.setText(player1.toString());
	    }
	    if (player1.getHand() > 21) {
	  		dealerLabel.setText(dealersHand.toString());
	    	msg = "Dealers Hand: " + dealersHand.toString() + " (total:" + dealersHand.value() + ")\n\n" + player1.getName() + " Hand: " + player1.toString() + " (total:" + player1.getHand() + ")";
	    	EndGame("Sorry, you lost!", msg, playerLabel, dealerLabel, walletTF, standBtn, hitMeBtn, sldr, 0, betBtn);
	    } 
	}
    else
    {
       while ( dealersHand.value() < STAND_PAT_POINT ) {
    	   dealersHand.hit(deck.dealCard());
       }
       dealerLabel.setText(dealersHand.toString());
       msg = "Dealers Hand: " + dealersHand.toString() + " (total:" + dealersHand.value() + ")\n\n" + player1.getName() + " Hand: " + player1.toString() + " (total:" + player1.getHand() + ")";
       if (dealersHand.value() > 21 || player1.getHand() > dealersHand.value())
    	   EndGame("Congratulations, you won!", msg, playerLabel, dealerLabel, walletTF, standBtn, hitMeBtn, sldr,1, betBtn);
       else if ((dealersHand.value() == player1.getHand()) && (dealersHand.value() < 21) && (player1.getHand() < 21)) {
    	   EndGame("Its a tie!", msg, playerLabel, dealerLabel, walletTF, standBtn, hitMeBtn, sldr, 2, betBtn);
       }
       else
    	   EndGame("Sorry, you lost!", msg, playerLabel, dealerLabel, walletTF, standBtn, hitMeBtn, sldr,0, betBtn);
    }
	}
	
    private static void EndGame(String result, String headerStr, Label playerLabel, Label dealerLabel, TextField walletTF, Button standBtn, Button hitMeBtn, Slider sldr, int resultType, Button betBtn) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Result");
        alert.setHeaderText(headerStr);
        alert.setContentText(result);
        alert.showAndWait();
    	playerLabel.setText("");
    	dealerLabel.setText("");
    	ResetGame(playerLabel, dealerLabel, walletTF, standBtn, hitMeBtn, sldr, resultType, betBtn);
    	
    }
}
