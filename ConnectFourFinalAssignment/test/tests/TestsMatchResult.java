package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.MatchResult;
import game.Player;

class TestsMatchResult {

	@Test
	void testCreateMatchResultWinner() {
		
		Player player = new Player("Nikola");
		Player player2 = new Player("Player2");
		MatchResult mr = new MatchResult(player, player2, 1);
		assertEquals("Match: " + player.toString() + " vs " + player2.toString() + ", Result: " + player.toString() + " Won!", mr.toString());
	}
	
	@Test
	void testCreateMatchResultDraw() {
		
		Player player = new Player("Nikola");
		Player player2 = new Player("Player2");
		MatchResult mr = new MatchResult(player, player2, 0);
		assertEquals("Match: " + player.toString() + " vs " + player2.toString() + ", Result: Draw", mr.toString());
		
	}
	
}
