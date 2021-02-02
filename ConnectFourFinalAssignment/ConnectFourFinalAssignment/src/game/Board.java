package game;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @version 1.0.0 Nov 25, 2020
 * @author Nikola Zlokapa
 * 
 *         <p>
 *         The Board object is used to represent the connectFour board.
 *         </p>
 */

public class Board {
	private int[][] board;
	private int playersTurn;
	private int lastDiscPlacedRow;
	private int lastDiscPlacedColumn;
	private int connectAmount;
	private int rows;
	private int columns;

	/**
	 * Constructor one used for the UI version of the board, Set to a 6x7 board.
	 * 
	 * @param connectAmount value to determine how many discs in a row are required
	 *                       to win.
	 */

	public Board(int connectAmount) {

		if(connectAmount > 3) {
			this.connectAmount = connectAmount;
		}else {
			throw new IllegalArgumentException("Connect amount has to be greater than 3");
		}
		this.rows = 6;
		this.columns = 7;
		this.board = new int[rows][columns];
		this.playersTurn = determineWhoMovesFirst();

	}

	/**
	 * Constructor two used for the console version of the board, where users can
	 * choose any size board to play on.
	 * 
	 * @param connectAmount value to determine how many discs in a row are required
	 *                       to win.
	 * @param rows           Value to determine how many rows the board should have.
	 * @param columns        Value to determine how many columns the board should
	 *                       have.
	 */
	public Board(int connectAmount, int rows, int columns) {
		if(rows > 5 && columns > 6 && columns >= rows) {
			this.rows = rows;
			this.columns = columns;
		}else {
			throw new IllegalArgumentException("Board has to be at least 6x7");
		}
		if(connectAmount > 3) {
			this.connectAmount = connectAmount;
		}else {
			throw new IllegalArgumentException("Connect amount has to be greater than 3");
		}
		this.board = new int[rows][columns];
		this.playersTurn = determineWhoMovesFirst();
		
	}

	/**
	 *  Method to reset the board once the game has finished.
	 */
	private void setBoard() {
		board = new int[rows][columns];
	}
	
	/**
	 * 
	 * @return The number of rows in the board.
	 */
	public int getRows() {
		return rows;
	}
	
	/**
	 * 
	 * @return The number of columns in the board.
	 */
	public int getColumns() {
		return columns;
	}
	
	/**
	 * 
	 * @return The amount of discs you have to connect in a row to win.
	 */
	public int getConnectAmount() {
		return connectAmount;
	}
	

	/**
	 *  Method used to Print the board to the console.
	 */
	public void printBoard() {
		System.out.println("");
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 1) {
					System.out.print(" x ");
				} else if (board[i][j] == 2) {
					System.out.print(" @ ");
				} else {
					System.out.print(" O ");
				}
			}
			System.out.println("");
		}
		System.out.println("");
	}

	/**
	 * 			 method used for making a move. First validate that the disc is
	 *           being placed in a valid location, Then find the lowest available
	 *           position in the column selected update the board array with the row
	 *           and column and print the updated board. The result is then checked
	 *           to determine if the game is over. If the game is over reset the
	 *           board and print it out again. Then the move is changed to the next
	 *           player
	 * 
	 * @param column The column the player has selected to drop his disc in.
	 * @return return the outcome of the game.
	 */
	public String makeMove(int column) {

		if (validMove(column)) {
			int row = findLowestFreePositionInColumn(column);
			board[row][column] = getPlayersTurn();
			lastDiscPlacedRow = row;
			lastDiscPlacedColumn = column;

			printBoard();
			String winner = getResult();
			if (winner != "This is a invalid move") {
				setBoard();
				printBoard();
			}
			setNextTurn();

			return winner;
		} else {
			System.out.println("This is a invalid move");
			return "This is a invalid move";
		}

	}

	/**
	 * @return The current player turn
	 */
	public int getPlayersTurn() {
		return playersTurn;
	}

	/**
	 * Set current players Turn
	 */
	private void setNextTurn() {
		if (playersTurn == 1) {
			playersTurn = 2;
		} else {
			playersTurn = 1;
		}
	}

	/**
	 * @return a random number either 1 or 2 to randomly determine who will play
	 *         first.
	 */
	private int determineWhoMovesFirst() {
		int randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
		return randomNum;
	}

	/**
	 * 
	 * @param column The column the player has selected to drop their disc.
	 * @return position of the lowest available position in that column.
	 */
	public int findLowestFreePositionInColumn(int column) {
		if(column >= 0 && column < this.columns) {
			int lowestPosition = -1;
			for (int i = 0; i < board.length; i++) {
				if (board[i][column] == 0) {
					lowestPosition = i;
				}
			}
			return lowestPosition;
		}else {
			throw new IllegalArgumentException("Column not in range!");
		}
		
		
		
	}

	/**
	 * 
	 * @return return the outcome of the game, if there was a winner return the
	 *         winner, if it was a draw return draw.
	 *         .
	 */
	protected String getResult() {
		if (checkColumn() || checkRow() || checkDiagonalNegativeSlope() || checkDiagonalPositiveSlope()) {
			System.out.println("Player " + getPlayersTurn() + " WON!!");
			return "Player " + getPlayersTurn() + " WON!!";
		} else if (checkBoardFull()) {
			System.out.println("GAME OVER! DRAW!");
			return "GAME OVER! DRAW!";
		} else {
			return "This is a invalid move";
		}
	}

	/**
	 * 
	 * @param column The column the player has selected to drop their disc in.
	 * @return return if the move was valid or not.
	 */
	private boolean validMove(int column) {
		if (column >= 0 && column < columns && findLowestFreePositionInColumn(column) != -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check if the top of each column on the board is occupied.
	 * 
	 * @return return if the board is full of discs.
	 */
	private boolean checkBoardFull() {
		int counter = 0;
		for (int i = 0; i < rows; i++) {
			if (board[0][i] != 0) {
				counter++;
			}
		}
		if (counter >= rows) {
			return true;
		}
		return false;
	}

	/**
	 * Check if the row the current disc has been placed in has x(connectAmount)
	 * consecutive discs.
	 * 
	 * @return return if the player has x amount of consecutive discs in the row. -
	 */
	private boolean checkRow() {
		int counter = 0;
		if (lastDiscPlacedRow <= rows - connectAmount) {

			for (int i = lastDiscPlacedRow; i < lastDiscPlacedRow + connectAmount; i++) {
				if (board[i][lastDiscPlacedColumn] == getPlayersTurn()) {
					counter++;
				}
			}
		}
		if (counter >= connectAmount) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * @return return if player got (connectAmount) discs in a row in the current
	 *         column or not. |
	 */
	private boolean checkColumn() {
		int counter = 0;

		for (int i = 0; i < columns; i++) {
			if (board[lastDiscPlacedRow][i] == getPlayersTurn()) {
				counter++;
				if (counter >= connectAmount) {
					return true;
				}
			} else {
				counter = 0;
			}
		}

		return false;
	}

	/**
	 * Get the location of the last disc placed (row and column), move one
	 * diagonally down and negatively till I hit the border of either side of the
	 * board ex going from (3, 3) to (0, 6) will take 3 moves. Take this value away
	 * from both my columns and rows to get the lowest position diagonal to the
	 * starting position, then go through the board positively diagonally and count
	 * how many discs are connected of the current players colour, until the end of
	 * the board is reached. Once we reach either border we move diagonally
	 * positively upwards till we hit the other end of the board
	 * 
	 * @return return if the current player has (connectAmount) number of discs in
	 *         a row going diagonally positively. /
	 */
	private boolean checkDiagonalPositiveSlope() {
		int counter = 0;
		int index = 0;

		while (lastDiscPlacedRow + index < rows - 1 && lastDiscPlacedColumn - index > 0) {
			index++;
		}

		int startingPointRow = lastDiscPlacedRow + index;
		int startingPointColumn = lastDiscPlacedColumn - index;

		int i = 0;
		while (startingPointRow - i >= 0 && startingPointColumn + i < columns) {

			if (board[startingPointRow - i][startingPointColumn + i] == getPlayersTurn()) {
				counter++;
				if (counter >= connectAmount) {
					return true;
				}
			} else {
				counter = 0;
			}
			i++;
		}
		return false;
	}

	/**
	 * Get row and column the disc was placed in and get the lowest value of the
	 * two. remove this value from both the row and column the disc was placed in
	 * this will make sure the new starting position is the border of the board
	 * diagonal to the position the dis was placed ex (5,3) to  (2,0). Now move
	 * diagonally negatively downwards till we hit the other end of the board and
	 * count how many discs in a row of the current player there are.
	 * 
	 * @return return if the current player has (connectAmount) number of discs in
	 *         a row going diagonally negatively. \
	 */
	private boolean checkDiagonalNegativeSlope() {
		int counter = 0;
		int minValue = Math.min(lastDiscPlacedRow, lastDiscPlacedColumn);
		int startingPointRow = lastDiscPlacedRow - minValue;
		int startingPointColumn = lastDiscPlacedColumn - minValue;
		int index = 0;

		do {
			if (board[startingPointRow + index][startingPointColumn + index] == getPlayersTurn()) {
				counter++;
				if (counter >= connectAmount) {
					return true;
				}
			} else {
				counter = 0;
			}
			index++;
		} while (startingPointRow + index < rows && startingPointColumn + index < columns);

		return false;
	}

}
