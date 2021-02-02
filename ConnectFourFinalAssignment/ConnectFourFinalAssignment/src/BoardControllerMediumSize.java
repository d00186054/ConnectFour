



import java.io.FileNotFoundException;
import java.io.IOException;

import game.Board;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * @version 1.0.0 Dec 14, 2020
 * @author Nikola Zlokapa
 * 
 *         This class extends the boardController Class and adds a one new row
 *         and one new column to the board.
 */

public class BoardControllerMediumSize extends BoardController {

	private static final int SIZE = 90;
	private static final int COLUMNS = 8;
	private static final int ROWS = 7;

	//static ConnectFour c4 = new ConnectFour();
	@FXML
	protected Button btn7;

	/**
	 * 
	 * The StartGame Method creates and new board and prints the newly defined board
	 * size to the UI.
	 */
	public void startGame() {
		startGameText.setVisible(false);
		board = new Board(connectAmount, ROWS, COLUMNS);
		drawBoard();
		board.printBoard();
		getPlayersTurnToPrint();
	}

	public BoardControllerMediumSize() {

	}

	/**
	 * When the button is pressed it will call the makeMove method with the column
	 * number passed in.
	 * 
	 * @throws FileNotFoundException File Exception
	 * @throws IOException Io exception
	 */
	public void row_seven_click() throws FileNotFoundException, IOException {
		makeMove(7);
	}

	/**
	 * 
	 * The drawBoard Method prints the board to the UI screen.
	 */
	protected void drawBoard() {
		for (int i = 0; i < COLUMNS; i++) {
			for (int j = 0; j < ROWS; j++) {
				Circle circle = new Circle(SIZE / 2);
				circle.setCenterX(SIZE / 2);
				circle.setCenterY(SIZE / 2);
				circle.setTranslateX(i * (SIZE + 30) + SIZE / 4);
				circle.setTranslateY(j * (SIZE + 20) + SIZE / 4);
				circle.setFill(Color.WHITE);

				anchorPane.getChildren().add(circle);
			}
		}
	}

}
