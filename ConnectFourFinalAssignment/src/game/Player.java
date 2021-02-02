package game;

/**
 * @version 1.0.0 Nov 25, 2020
 * @author Nikola Zlokapa
 * 
 *         <p>
 *         The Player Object contains a Username, Disc Colour and a ID.
 *         </p>
 */
public class Player {

	private String username;
	private String discColour;
	private static int id = 1;

	/**
	 * @param username String containing the players user name.
	 */
	public Player(String username) {
		this.username = username;
		if (id == 1) {
			this.discColour = "YELLOW";
		} else {
			this.discColour = "RED";
		}
		id++;
	}

	/**
	 * @return Players user name.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return Players disc colour
	 */
	public String getColour() {
		return discColour;
	}

	/**
	 * @return players user name, Won, players disc colour.
	 */
	public String toString() {
		return username;
	}
	
	/**
	 * 
	 * @return The user name with Won! appended to the end.
	 */
	public String toStringWinner() {
		return username + " Won!" ;
	}

}
