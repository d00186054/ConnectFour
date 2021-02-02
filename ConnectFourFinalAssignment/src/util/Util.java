package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import game.MatchResult;
import game.Player;

/**
 * @version 1.0.0 Nov 25, 2020
 * @author Nikola Zlokapa
 * 
 *         <p>
 *         The Util class is a private class which contains helper methods
 *         </p>
 */

public final class Util {

	private static String location = "connect_four_results.txt";

	private Util() {
	}

	/**
	 * @return return the current date and time formatted.
	 */
	public static String getDate() {
		LocalDateTime dateObject = LocalDateTime.now();
		DateTimeFormatter formatDateObj = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
		String formattedDate = dateObject.format(formatDateObj);

		return formattedDate;
	}

	/**
	 * @param outcome String containing the game outcome.
	 * @throws IOException
	 * 
	 * 				Assign a file, create a new one if it doesnt already exist. add
	 *              the current date&time to the string passed in with the match
	 *              outcome and append to the end of the file.
	 */
	protected static void saveResultsToFile(String outcome) throws IOException {

		File f = new File(location);
		if (!f.exists()) {
			f.createNewFile();
		}
		outcome += ": Date:  " + getDate() + "\n";
		FileOutputStream outputStream = new FileOutputStream(location, true);
		byte[] strToBytes = outcome.getBytes();
		outputStream.write(strToBytes);
		outputStream.close();
	}

	/**
	 * 
	 * Check to see if result is not null, and depending on the result
	 *              create a new matchResult object and then saves the result to the text file.
	 *              
	 * @param player1 Player one object used to determine winner.
	 * @param player2 Player two object used to determine winner.
	 * @param result  String containing outcome of match.
	 * @throws IOException File error
	 * 
	 *@return The result of the game.
	 */
	public static String getResult(Player player1, Player player2, String result) throws IOException {

		if (result != "This is a invalid move") {
			MatchResult mr;
			if (result.contains("2")) {
				result = player1.toStringWinner();
				mr = new MatchResult(player1, player2, 1);
			} else if (result.contains("1")) {
				result = player2.toStringWinner();
				mr = new MatchResult(player2, player1, 1);
			} else {
				mr = new MatchResult(player1, player2, 0);
			}
			saveResultsToFile(mr.toString());
			return result;
		}else {
			return result;
		}
		
	}

	

}
