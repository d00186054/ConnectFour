



import java.io.FileNotFoundException;
import java.io.IOException;

import game.Board;
import game.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import util.Util;

/**
 * @version 1.0.0 Nov 25, 2020
 * @author Nikola Zlokapa
 * 
 *         Controller object for the board fxml, It has a size, column and rows
 *         variable used for drawing the the circle discs on the board. It has
 *         fxml text variables for displaying information about the current game
 *         and buttons to determine what column the player wishes to drop his
 *         disc.
 */

public class BoardController {

	private static final int SIZE = 90;
	private static final int COLUMNS = 7;
	private static final int ROWS = 6;
	protected Board board;
	protected int connectAmount;

	//static ConnectFour c4 = new ConnectFour();

	@FXML
	protected Text displayText, displayText1, startGameText;
	@FXML
	protected AnchorPane anchorPane;
	@FXML
	protected Button btn0, btn1, btn2, btn3, btn4, btn5, btn6;

	protected boolean resetBoardDraw = false;

	public Player player1;
	public Player player2;

	public BoardController() {

	}

	/**
	 * Once the screen is initialised call the methods to draw the board on screen
	 * and to output the board to the console.
	 */
	public void initialize() {

	}

	public void startGame() {
		
		
		
		displayPlayersTurn(player1);
		startGameText.setVisible(false);
		board = new Board(connectAmount, ROWS, COLUMNS);
		drawBoard();
		board.printBoard();
		getPlayersTurnToPrint();
	}

	/**
	 * When the button is pressed it will call the makeMove method with the column
	 * number passed in.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void row_zero_click() throws FileNotFoundException, IOException {
		makeMove(0);
	}

	/**
	 * When the button is pressed it will call the makeMove method with the column
	 * number passed in.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void row_one_click() throws FileNotFoundException, IOException {
		makeMove(1);
	}

	/**
	 * When the button is pressed it will call the makeMove method with the column
	 * number passed in.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void row_two_click() throws FileNotFoundException, IOException {
		makeMove(2);
	}

	/**
	 * When the button is pressed it will call the makeMove method with the column
	 * number passed in.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void row_three_click() throws FileNotFoundException, IOException {
		makeMove(3);
	}

	/**
	 * When the button is pressed it will call the makeMove method with the column
	 * number passed in.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void row_four_click() throws FileNotFoundException, IOException {
		makeMove(4);
	}

	/**
	 * When the button is pressed it will call the makeMove method with the column
	 * number passed in.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void row_five_click() throws FileNotFoundException, IOException {
		makeMove(5);
	}

	/**
	 * When the button is pressed it will call the makeMove method with the column
	 * number passed in.
	 * 
	 * @throws FileNotFoundException File Exception
	 * @throws IOException Io Exception
	 */
	public void row_six_click() throws FileNotFoundException, IOException {
		makeMove(6);
	}

	/**
	 * Draw white circles on the screen to represent each row and column on the
	 * board.
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

	/**
	 * Draw a yellow or red disc on the board.
	 * 
	 * @param row    the row the disc will be drawn on.
	 * @param column the column the disc will be drawn on.
	 * @param player the current player, to detemine the colour of the disc to draw.
	 * @return
	 */
	protected Circle drawDisc(int row, int column, int player) {
		Circle circle = new Circle(SIZE / 2);
		circle.setCenterX(SIZE / 2);
		circle.setCenterY(SIZE / 2);
		circle.setTranslateX(row * (SIZE + 30) + SIZE / 4);
		circle.setTranslateY(column * (SIZE + 20) + SIZE / 4);
		if (player == 1) {
			circle.setFill(Color.YELLOW);
		} else {
			circle.setFill(Color.RED);
		}
		return circle;

	}

	/**
	 * Set Textbox output to game over.
	 */
	protected void displayTextOutput() {
		if (displayText.getText() == "GAME OVER") {
			displayText.setText("");
		}
		displayText1.setText("");
	}

	/**
	 * call the draw board method to reset the board to all white spaces.
	 */
	protected void resetBoard() {
		if (resetBoardDraw == true) {
			drawBoard();
			resetBoardDraw = false;
		}

	}

	/**
	 * First we call the resetboard to check if the board needs to be reset (if game
	 * has finished) followed by displaying the text output on screen. find the
	 * lowest available column row to place disc. Then the makeMove method from the
	 * board object is called which will return the outcome of the game after the
	 * move has been made. depending on the outcome of the game we pass the players
	 * and result of the game to the util method getResult and finally output the
	 * disc onto the screen in the correct position. Lastly we check to see if the
	 * result returned was not null and if it wasn't that means the game has
	 * finished.
	 * 
	 * @param column The column of the board the disc is being placed in.
	 * @throws IOException
	 */
	protected void makeMove(int column) throws IOException {
		resetBoard();
		displayTextOutput();
		int row = board.findLowestFreePositionInColumn(column);
		String resetBoard = board.makeMove(column);
		resetBoard = Util.getResult(player1, player2, resetBoard);
		drawPlayersMove(board.getPlayersTurn(), row, column);

		if (resetBoard != "This is a invalid move") {
			displayText.setText("GAME OVER");
			displayText1.setText(resetBoard);
			resetBoardDraw = true;
		}

	}

	/**
	 * Update the textbox to display the current players turn.
	 * 
	 * @param p a player object.
	 */
	protected void displayPlayersTurn(Player p) {
		displayText.setText(p.getUsername() + "'s Turn " + p.getColour());
	}

	/**
	 * Draw a disc on the position a player wishes to drop their disc, and update
	 * the textbox to show next players turn.
	 * 
	 * @param player current player object.
	 * @param row    row selected by player to drop disc.
	 * @param column column selected by player to drop disc.
	 */
	protected void drawPlayersMove(int player, int row, int column) {
		if (row >= 0) {
			anchorPane.getChildren().add(drawDisc(column, row, player));
			if (player == 1) {
				displayPlayersTurn(player2);
			} else if (player == 2) {
				displayPlayersTurn(player1);
			}
		}
	}
	

	protected void getPlayersTurnToPrint() {
		if(board.getPlayersTurn() == 1) {
			displayPlayersTurn(player2);
		}else {
			displayPlayersTurn(player1);
		}
	}

}
