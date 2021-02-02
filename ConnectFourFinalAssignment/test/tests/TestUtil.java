package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import game.MatchResult;
import game.Player;
import util.Util;

class TestUtil {

	@Test
	void testGetDate() {
		
		
		LocalDateTime dateObject = LocalDateTime.now();
		DateTimeFormatter formatDateObj = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
		String formattedDate = dateObject.format(formatDateObj);
		
		assertEquals(Util.getDate(), formattedDate);
	
	}
	
	@Test
	void testGetResultDraw() {
		Player player = new Player("Nikola");
		Player player2 = new Player("Player2");
		
		
		try {
			assertEquals(Util.getResult(player, player2, "0"), "0");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	@Test
	void testGetResultPlayer1Win() {
		Player player = new Player("Nikola");
		Player player2 = new Player("Player2");
		
		
		try {
			assertEquals(Util.getResult(player, player2, "2"), "Nikola Won!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	@Test
	void testGetResultPlayer2Win() {
		Player player = new Player("Nikola");
		Player player2 = new Player("Player2");
		
		
		try {
			assertEquals(Util.getResult(player, player2, "1"), "Player2 Won!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	@Test
	void testGetResultInvalidResult() {
		Player player = new Player("Nikola");
		Player player2 = new Player("Player2");
		
		
		try {
			assertEquals(Util.getResult(player, player2, "This is a invalid move"), "This is a invalid move");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	

}
