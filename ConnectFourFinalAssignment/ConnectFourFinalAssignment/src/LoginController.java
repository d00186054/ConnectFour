



import java.net.URL;

import game.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.Util;

/**
 * @version 1.0.0 Nov 25, 2020
 * @author Nikola Zlokapa
 * 
 *         <p>
 *         The LoginController object is used to control the fmxl UI for the
 *         LoginUI object.
 *         </p>
 */
public class LoginController {


	ObservableList<String> sizes = FXCollections.observableArrayList("Original (6x7)", "Medium (7x8)", "Large (8x9)");
	ObservableList<String> connect = FXCollections.observableArrayList("Original (4)", "Five (5)", "Six (6)");

	@FXML
	private TextField p1Username, p2Username;
	@FXML
	private Button loginBtn;
	@FXML
	private ChoiceBox choiceBoxSize, choiceBoxConnect;

	
	@FXML
	private void initialize() {
		choiceBoxSize.setValue("Original (6x7)");
		choiceBoxSize.setItems(sizes);
		choiceBoxConnect.setValue("Original (4)");
		choiceBoxConnect.setItems(connect);
	}

	/**
	 * 
	 * @return The amount of discs to connect selected by user.
	 */
	public int getConnectAmount() {
		if (choiceBoxConnect.getValue().equals("Original (4)")) {
			return 4;
		} else if (choiceBoxConnect.getValue().equals("Five (5)")) {
			return 5;
		} else {
			return 6;
		}
	}

	public LoginController() {
	}

	
	/**
	 * 
	 * @param event  	
	 * On button click of loginBtn load the fxml for the board, create 2 player
	 * objects with user names that are inputed in the text files, set the current
	 * scene and close the login scene.
	 */
	@FXML
	public void startGame(ActionEvent event) {

	

		try {
			URL u;

			if (choiceBoxSize.getValue().equals("Original (6x7)")) {
				u = getClass().getResource("/fxml/board.fxml");
			} else if (choiceBoxSize.getValue().equals("Medium (7x8)")) {
				u = getClass().getResource("/fxml/boardMed.fxml");
			} else {
				u = getClass().getResource("/fxml/boardLarge.fxml");
			}

			FXMLLoader loader = new FXMLLoader(u);
			Pane root = loader.load();
			int num = getConnectAmount();

			BoardController bc = loader.getController();

			if (p1Username.getText().length() > 0) {
				bc.player1 = new Player(p1Username.getText());
			} else {
				bc.player1 = new Player("Guest 1");
			}

			if (p2Username.getText().length() > 0) {
				bc.player2 = new Player(p2Username.getText());
			} else {
				bc.player2 = new Player("Guest 2");
			}

			bc.connectAmount = getConnectAmount();

			Stage primaryStage = new Stage();
			primaryStage.setTitle("Connect Four");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();

			primaryStage.setResizable(false);
			((Node) event.getSource()).getScene().getWindow().hide();// close currentstage

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
