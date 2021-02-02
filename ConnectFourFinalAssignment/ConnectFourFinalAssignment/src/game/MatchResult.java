package game;

/**
 * @version 1.0.0 Nov 25, 2020
 * @author Nikola Zlokapa
 * 
 *         <p>
 *         The MatchResult object contains 2 player objects and a result
 *         </p>
 */
public class MatchResult {

	private Player p1;
	private Player p2;
	private int result;

	/**
	 * @param p1     Player one object (winner passed as p1 if game was not a draw.)
	 * @param p2     Player two object
	 * @param result outcome of the match.
	 */
	public MatchResult(Player p1, Player p2, int result) {
		this.p1 = p1;
		this.p2 = p2;
		this.result = result;
	}

	/**
	 * @return string outcome depending on match result.
	 * 
	 */
	public String toString() {

		if (result == 0) {
			return "Match: " + p1.toString() + " vs " + p2.toString() + ", Result: Draw";
		} else if (result == 1) {
			return "Match: " + p1.toString() + " vs " + p2.toString() + ", Result: " + p1.toString() + " Won!";
		} else {
			return "Error returning result!";
		}

	}

}
