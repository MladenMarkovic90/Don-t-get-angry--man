package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class Info implements Initializable {
	
	@FXML TextArea text;

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	    text.setText("The Game gets started when you choose amount of players in Menu Bar\n"+
	            "Current player is marked in the Circle over the button Throw/Continue\n" + 
	            "To play the game, you need to throw and click which circle you want to move\n" + 
	            "To finish the move, you need to click button Continue\n\n" +
				"Made by Mladen Marković\n\n" +
				"mladen.markovic@hotmail.ch");

	}

}
