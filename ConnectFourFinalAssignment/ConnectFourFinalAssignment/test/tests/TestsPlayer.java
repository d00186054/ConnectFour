package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.Board;
import game.Player;

class TestsPlayer {
	
	

	@Test
	void testCreatePlayer() {
		Player player = new Player("Nikola");
		assertEquals("Nikola", player.getUsername());
		assertEquals("YELLOW", player.getColour());
	}
	
	@Test
	void testPlayerDiscColour() {
		Player player2 = new Player("Nikola");
		assertEquals("RED", player2.getColour());
		
	}
	
	@Test
	void testPlayertoString() {
		Player player2 = new Player("Nikola");
		assertEquals(player2.getUsername(), player2.toString());
		
	}
	
	@Test
	void testPlayertoStringWinner() {
		Player player2 = new Player("Nikola");
		assertEquals(player2.getUsername() + " Won!", player2.toStringWinner());
		
	}
	

}
