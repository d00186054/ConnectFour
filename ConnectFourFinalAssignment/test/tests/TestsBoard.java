package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.Board;

class TestsBoard {

	@Test
	void testCreateDefaultBoard() {
		Board board = new Board(5);

		assertEquals(6, board.getRows());
		assertEquals(7, board.getColumns());
		assertEquals(5, board.getConnectAmount());
	}

	@Test
	void testCreateDefaultBoardConnectAmountNegative() {
		try {
			Board board = new Board(-5);
			fail("Connect amount has to be greater than 3");
		} catch (IllegalArgumentException ex) {
		}

	}

	@Test
	void testCreateDefaultBoardConnectAmountLessThan4() {
		try {
			Board board = new Board(3);
			fail("Connect amount has to be greater than 3");
		} catch (IllegalArgumentException ex) {
		}
	}

	@Test
	void testCreateCustomBoard() {
		Board board = new Board(4, 8, 8);

		assertEquals(8, board.getRows());
		assertEquals(8, board.getColumns());
		assertEquals(4, board.getConnectAmount());
	}

	@Test
	void testCreateCustomBoardNegativeConnectAmount() {
		try {
			Board board = new Board(-4, 8, 7);
			fail("Connect amount has to be greater than 3");
		} catch (IllegalArgumentException ex) {
		}
	}

	@Test
	void testCreateCustomBoardNegativeRowsAmount() {

		try {
			Board board = new Board(4, -8, 7);
			fail("Board has to be at least 6x7");
		} catch (IllegalArgumentException ex) {
		}
	}

	@Test
	void testCreateCustomBoardNegativeColumnsAmount() {
		try {
			Board board = new Board(4, 8, -7);
			fail("Board has to be at least 6x7");
		} catch (IllegalArgumentException ex) {
		}
	}

	@Test
	void testCreateCustomBoardConnectAmountThree() {
		try {
			Board board = new Board(3, 8, 7);
			fail("Connect amount has to be greater than 3");
		} catch (IllegalArgumentException ex) {
		}
	}

	@Test
	void testCreateCustomBoardTooSmall() {
		try {
			Board board = new Board(3, 4, 3);
			fail("Board has to be at least 6x7");
		} catch (IllegalArgumentException ex) {
		}
	}

	@Test
	void testSetNextTurn() {
		Board board = new Board(4, 8, 8);

		if (board.getPlayersTurn() == 1) {
			board.makeMove(1);
			assertEquals(2, board.getPlayersTurn());
		} else {
			board.makeMove(1);
			assertEquals(1, board.getPlayersTurn());
		}
	}

	@Test
	void testFindLowestPositionInColumn() {
		Board board = new Board(4, 6, 7);
		board.makeMove(1);
		board.makeMove(1);
		board.makeMove(1);
		board.makeMove(1);
		assertEquals(1, board.findLowestFreePositionInColumn(1));
		assertEquals(5, board.findLowestFreePositionInColumn(5));
		board.makeMove(1);
		assertEquals(0, board.findLowestFreePositionInColumn(1));
	}
	
	@Test
	void testFindLowestPositionInColumnOutsideOfRange() {
		Board board = new Board(4, 6, 7);
		
		try {
			assertEquals(1, board.findLowestFreePositionInColumn(7));
			fail("Column not in range");
		} catch (IllegalArgumentException ex) {
		}
	}
	
	@Test
	void testFindLowestPositionInColumnNegative() {
		Board board = new Board(4, 6, 7);
		
		try {
			assertEquals(1, board.findLowestFreePositionInColumn(-1));
			fail("Column not in range");
		} catch (IllegalArgumentException ex) {
		}
	}

	@Test
	void testDrawGame() {

		// This test looks messy thats cause I had to find a way of filling the boards
		// without having a winner to test the game finished as a draw
		Board board = new Board(4, 6, 7);

		for (int i = 0; i < 6; i++) {

			for (int j = 0; j < 7; j++) {
				if (i == 6 && j == 6) {
					break;
				}

				board.makeMove(i);
				board.makeMove(j);
				board.makeMove(j);
				board.makeMove(i);

			}
			board.makeMove(i);

		}
		board.makeMove(0);
		board.makeMove(2);
		board.makeMove(1);
		board.makeMove(0);
		board.makeMove(2);

		assertEquals("GAME OVER! DRAW!", board.makeMove(1));

	}
	
	
	@Test
	void testWinnerColumn1Connect4() {

		Board board = new Board(4, 6, 7);

			for (int i = 0; i < 3; i++) {
				
				board.makeMove(1);
				board.makeMove(0);
			}

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(1));
	}
	
	@Test
	void testWinnerColumn2Connect4() {

		Board board = new Board(4, 6, 7);

			for (int i = 0; i < 3; i++) {
				
				board.makeMove(2);
				board.makeMove(0);
			}

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(2));
	}
	
	@Test
	void testWinnerColumn3Connect4() {

		Board board = new Board(4, 6, 7);

			for (int i = 0; i < 3; i++) {
				
				board.makeMove(3);
				board.makeMove(0);
			}

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(3));
	}
	
	@Test
	void testWinnerColumn4Connect4() {

		Board board = new Board(4, 6, 7);

			for (int i = 0; i < 3; i++) {
				
				board.makeMove(4);
				board.makeMove(0);
			}

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(4));
	}
	
	@Test
	void testWinnerColumn5Connect4() {

		Board board = new Board(4, 6, 7);

			for (int i = 0; i < 3; i++) {
				
				board.makeMove(5);
				board.makeMove(0);
			}

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(5));
	}
	
	@Test
	void testWinnerColumn6Connect4() {

		Board board = new Board(4, 6, 7);

			for (int i = 0; i < 3; i++) {
				
				board.makeMove(6);
				board.makeMove(0);
			}

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(6));
	}
	
	@Test
	void testWinnerColumn0Connect4() {

		Board board = new Board(4, 6, 7);

			for (int i = 0; i < 3; i++) {
				
				board.makeMove(0);
				board.makeMove(1);
			}

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(0));
	}
	
	
	
	
	
	
	@Test
	void testWinnerRowConnect4() {

		Board board = new Board(4, 6, 7);

			for (int i = 0; i < 3; i++) {
				
				board.makeMove(i);
				board.makeMove(i);
			}

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(3));
	}
	
	@Test
	void testWinnerDiagonalPositiveSlopeWinningDiscPos1() {

		Board board = new Board(4, 6, 7);

		board.makeMove(0);
		board.makeMove(1);
		board.makeMove(1);
		board.makeMove(2);
		board.makeMove(1);
		board.makeMove(2);
		board.makeMove(2);
		board.makeMove(3);
		board.makeMove(3);
		board.makeMove(3);

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(3));
	}
	
	@Test
	void testWinnerDiagonalPositiveSlopeWinningDiscPos2() {

		Board board = new Board(4, 6, 7);

		board.makeMove(0);
		board.makeMove(1);
		board.makeMove(1);
		board.makeMove(2);
		board.makeMove(3);
		board.makeMove(3);
		board.makeMove(3);
		board.makeMove(5);
		board.makeMove(3);
		board.makeMove(2);
		

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(2));
	}
	
	@Test
	void testWinnerDiagonalPositiveSlopeWinningDiscPos3() {

		Board board = new Board(4, 6, 7);

		board.makeMove(0);
		board.makeMove(1);
		board.makeMove(3);
		board.makeMove(2);
		board.makeMove(3);
		board.makeMove(3);
		board.makeMove(2);
		board.makeMove(6);
		board.makeMove(2);
		board.makeMove(6);
		board.makeMove(3);
		board.makeMove(6);
		
		

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(1));
	}

	@Test
	void testWinnerDiagonalPositiveSlopeWinningDiscPos4() {

		Board board = new Board(4, 6, 7);

		board.makeMove(5);
		board.makeMove(1);
		board.makeMove(3);
		board.makeMove(2);
		board.makeMove(3);
		board.makeMove(3);
		board.makeMove(2);
		board.makeMove(6);
		board.makeMove(2);
		board.makeMove(6);
		board.makeMove(3);
		board.makeMove(6);
		board.makeMove(1);
		board.makeMove(1);
		
		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(0));
	}

	@Test
	void testWinnerDiagonalNegativeSlopeWinningDiscPos4() {


		Board board = new Board(4, 6, 7);

		board.makeMove(3);
		board.makeMove(2);
		board.makeMove(2);
		board.makeMove(1);
		board.makeMove(2);
		board.makeMove(1);
		board.makeMove(1);
		board.makeMove(0);
		board.makeMove(0);
		board.makeMove(0);

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(0));
	}

	@Test
	void testWinnerDiagonalNegativeSlopeWinningDiscPos3() {


		Board board = new Board(4, 6, 7);

		board.makeMove(3);
		board.makeMove(2);
		board.makeMove(2);
		board.makeMove(1);
		board.makeMove(2);
		board.makeMove(1);
		board.makeMove(5);
		board.makeMove(0);
		board.makeMove(0);
		board.makeMove(0);
		board.makeMove(0);
		board.makeMove(5);

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(1));
	}
	
	@Test
	void testWinnerDiagonalNegativeSlopeWinningDiscPos2() {


		Board board = new Board(4, 6, 7);

		board.makeMove(3);
		board.makeMove(2);
		board.makeMove(5);
		board.makeMove(1);
		board.makeMove(5);
		board.makeMove(1);
		board.makeMove(1);
		board.makeMove(0);
		board.makeMove(0);
		board.makeMove(0);
		board.makeMove(0);
		board.makeMove(5);


		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(2));
	}
	
	
	@Test
	void testWinnerDiagonalNegativeSlopeWinningDiscPos1() {


		Board board = new Board(4, 6, 7);

		board.makeMove(6);
		board.makeMove(2);
		board.makeMove(2);
		board.makeMove(1);
		board.makeMove(2);
		board.makeMove(1);
		board.makeMove(1);
		board.makeMove(0);
		board.makeMove(0);
		board.makeMove(0);
		board.makeMove(0);
		board.makeMove(2);

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(3));
	}
	
	
	
	@Test
	void testPlaceDiscInFullColumn() {


		Board board = new Board(4, 6, 7);

		for (int i = 0; i < 6; i++) {

			for (int j = 0; j < 7; j++) {
				if (i == 6 && j == 6) {
					break;
				}

				board.makeMove(i);
				board.makeMove(j);
				board.makeMove(j);
				board.makeMove(i);

			}
			board.makeMove(i);

		}
		board.makeMove(0);
		board.makeMove(2);
		board.makeMove(1);
		board.makeMove(0);
		board.makeMove(2);

		assertEquals("This is a invalid move", board.makeMove(0));
		assertEquals("This is a invalid move", board.makeMove(2));
		assertEquals("This is a invalid move", board.makeMove(3));
		assertEquals("This is a invalid move", board.makeMove(4));
		assertEquals("This is a invalid move", board.makeMove(5));
		assertEquals("This is a invalid move", board.makeMove(6));
		

	}
	
	@Test
	void testPlaceDiscOutsideOfColumnRange() {
		Board board = new Board(4, 6, 7);
		
		assertEquals("This is a invalid move", board.makeMove(7));
		assertEquals("This is a invalid move", board.makeMove(-1));
	}
	
	
	
	@Test
	void testWinnerColum1Connect6() {

		Board board = new Board(6, 6, 7);

			for (int i = 0; i < 5; i++) {
				
				board.makeMove(1);
				board.makeMove(0);
			}

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(1));
	}
	
	@Test
	void testWinnerColumn2Connect6() {

		Board board = new Board(6, 6, 7);

			for (int i = 0; i < 5; i++) {
				
				board.makeMove(2);
				board.makeMove(0);
			}

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(2));
	}
	
	@Test
	void testWinnerColumn3Connect6() {

		Board board = new Board(6, 6, 7);

			for (int i = 0; i < 5; i++) {
				
				board.makeMove(3);
				board.makeMove(0);
			}

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(3));
	}
	
	@Test
	void testWinnerColumn4Connect6() {

		Board board = new Board(6, 6, 7);

			for (int i = 0; i < 5; i++) {
				
				board.makeMove(4);
				board.makeMove(0);
			}

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(4));
	}
	
	@Test
	void testWinnerColumn5Connect6() {

		Board board = new Board(6, 6, 7);

			for (int i = 0; i < 5; i++) {
				
				board.makeMove(5);
				board.makeMove(0);
			}

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(5));
	}
	
	@Test
	void testWinnerColumn6Connect6() {

		Board board = new Board(6, 6, 7);

			for (int i = 0; i < 5; i++) {
				
				board.makeMove(6);
				board.makeMove(0);
			}

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(6));
	}
	
	@Test
	void testWinnerColumn0Connect6() {

		Board board = new Board(6, 6, 7);

			for (int i = 0; i < 5; i++) {
				
				board.makeMove(0);
				board.makeMove(1);
			}

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(0));
	}
	
	
	
	
	@Test
	void testWinnerColum1Connect5() {

		Board board = new Board(5, 6, 7);

			for (int i = 0; i < 4; i++) {
				
				board.makeMove(1);
				board.makeMove(0);
			}

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(1));
	}
	
	@Test
	void testWinnerColumn2Connect5() {

		Board board = new Board(5, 6, 7);

			for (int i = 0; i < 4; i++) {
				
				board.makeMove(2);
				board.makeMove(0);
			}

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(2));
	}
	
	@Test
	void testWinnerColumn3Connect5() {

		Board board = new Board(5, 6, 7);

			for (int i = 0; i < 4; i++) {
				
				board.makeMove(3);
				board.makeMove(0);
			}

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(3));
	}
	
	@Test
	void testWinnerColumn4Connect5() {

		Board board = new Board(5, 6, 7);

			for (int i = 0; i < 4; i++) {
				
				board.makeMove(4);
				board.makeMove(0);
			}

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(4));
	}
	
	@Test
	void testWinnerColumn5Connect5() {

		Board board = new Board(5, 6, 7);

			for (int i = 0; i < 4; i++) {
				
				board.makeMove(5);
				board.makeMove(0);
			}

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(5));
	}
	
	@Test
	void testWinnerColumn6Connect5() {

		Board board = new Board(5, 6, 7);

			for (int i = 0; i < 4; i++) {
				
				board.makeMove(6);
				board.makeMove(0);
			}

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(6));
	}
	
	@Test
	void testWinnerColumn0Connect5() {

		Board board = new Board(5, 6, 7);

			for (int i = 0; i < 4; i++) {
				
				board.makeMove(0);
				board.makeMove(1);
			}

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(0));
	}
	
	@Test
	void testWinnerRowConnect5() {

		Board board = new Board(5, 6, 7);

			for (int i = 0; i < 4; i++) {
				
				board.makeMove(i);
				board.makeMove(i);
			}

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(4));
	}
	
	
	@Test
	void testWinnerRowConnect6() {

		Board board = new Board(6, 6, 7);

			for (int i = 0; i < 5; i++) {
				
				board.makeMove(i);
				board.makeMove(i);
			}

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(5));
	}
	
	
	@Test
	void testWinnerDiagonalPositiveSlopeConnect5() {

		Board board = new Board(5, 7, 8);

		board.makeMove(5);
		board.makeMove(1);
		board.makeMove(3);
		board.makeMove(2);
		board.makeMove(3);
		board.makeMove(3);
		board.makeMove(2);
		board.makeMove(6);
		board.makeMove(2);
		board.makeMove(6);
		board.makeMove(3);
		board.makeMove(6);
		board.makeMove(1);
		board.makeMove(1);
		board.makeMove(4);
		board.makeMove(4);
		board.makeMove(4);
		board.makeMove(4);
		board.makeMove(4);
		board.makeMove(6);
		
		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(0));
	}

	@Test
	void testWinnerDiagonalNegativeSlopeConnect5() {


		Board board = new Board(5, 7, 8);

		board.makeMove(3);
		board.makeMove(2);
		board.makeMove(2);
		board.makeMove(1);
		board.makeMove(2);
		board.makeMove(1);
		board.makeMove(1);
		board.makeMove(0);
		board.makeMove(0);
		board.makeMove(0);
		board.makeMove(0);
		board.makeMove(7);
		board.makeMove(1);
		board.makeMove(7);
		board.makeMove(2);
		board.makeMove(7);
		board.makeMove(3);
		board.makeMove(7);
		board.makeMove(4);
		board.makeMove(6);

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(0));
	}
	
	
	@Test
	void testWinnerDiagonalPositiveSlopeConnect6() {

		Board board = new Board(6, 7, 9);

		board.makeMove(5);
		board.makeMove(1);
		board.makeMove(3);
		board.makeMove(2);
		board.makeMove(3);
		board.makeMove(3);
		board.makeMove(2);
		board.makeMove(6);
		board.makeMove(2);
		board.makeMove(6);
		board.makeMove(3);
		board.makeMove(6);
		board.makeMove(1);
		board.makeMove(1);
		board.makeMove(4);
		board.makeMove(4);
		board.makeMove(4);
		board.makeMove(4);
		board.makeMove(4);
		board.makeMove(6);
		board.makeMove(5);
		board.makeMove(5);
		board.makeMove(5);
		board.makeMove(5);
		board.makeMove(5);
		board.makeMove(5);
		
		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(0));
	}

	@Test
	void testWinnerDiagonalNegativeSlopeConnect6() {


		Board board = new Board(6, 7, 9);

		board.makeMove(3);
		board.makeMove(2);
		board.makeMove(2);
		board.makeMove(1);
		board.makeMove(2);
		board.makeMove(1);
		board.makeMove(1);
		board.makeMove(0);
		board.makeMove(0);
		board.makeMove(0);
		board.makeMove(0);
		board.makeMove(7);
		board.makeMove(1);
		board.makeMove(7);
		board.makeMove(2);
		board.makeMove(7);
		board.makeMove(3);
		board.makeMove(7);
		board.makeMove(4);
		board.makeMove(6);
		board.makeMove(1);
		board.makeMove(6);
		board.makeMove(3);
		board.makeMove(6);
		board.makeMove(4);
		board.makeMove(6);
		board.makeMove(5);
		board.makeMove(0);
		

		assertEquals("Player " + board.getPlayersTurn() + " WON!!", board.makeMove(0));
	}
	
	
	
	
	

}
