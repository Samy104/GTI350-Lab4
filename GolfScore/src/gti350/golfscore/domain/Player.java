package gti350.golfscore.domain;

/**
 * A golf player.
 * The magic number 18 is often used here. It's hardly a magic number.
 * 
 * @author Simon RG
 */
public class Player {

	private int id;
	private String name;
	private int gameId;
	
	/**
	 * Array containing the par for each hole.
	 * This array must have a length of 18.
	 */
	private int[] scores;
	
	/**
	 * Fully dependency-injected constructor.
	 * 
	 * @throws IllegalArgumentException when the length of 'scores' array is not 18.
	 */
	public Player(int id, String name, int[] scores, int gameId) {
		if (scores.length != 18) {
			throw new IllegalArgumentException("Length of 'scores' array must be 18.");
		}
		
		this.id = id;
		this.name = name;
		this.scores = scores;
		this.gameId = gameId;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getGameId() {
		return this.gameId;
	}
	
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	
	/**
	 * Returns the score for the specified hole.
	 * The hole must be between 1 and 18, otherwise this
	 * returns 0.
	 * 
	 * @param hole 
	 * @return score
	 */
	public int getScore(int hole) {
		if (hole < 1 || hole > 18) return 0;
		return scores[hole - 1];
	}

	/**
	 * Sets the score for the specified hole.
	 * The hole must be between 1 and 18, otherwise this
	 * returns 0.
	 * 
	 * @param hole 
	 * @param score
	 */
	public void setScore(int hole, int score) {
		if (hole < 1 || hole > 18) return;
		scores[hole - 1] = score;
	}
	
	/**
	 * Increments the score by 1 for the specified hole.
	 * The hole must be between 1 and 18, otherwise this
	 * does nothing.
	 * 
	 * @param hole
	 */
	public void incrementScore(int hole) {
		if (hole < 1 || hole > 18) return;
		scores[hole - 1]++;
	}
	
	/**
	 * Decrements the score by 1 for the specified hole.
	 * The hole must be between 1 and 18, otherwise this
	 * does nothing.
	 * 
	 * @param hole
	 */
	public void decrementScore(int hole) {
		if (hole < 1 || hole > 18) return;
		if (scores[hole - 1] > 0) {
			scores[hole - 1]--;
		}
	}
	
	public int getOUT() {
		int sum = 0;
		
		for (int i = 0; i < 9; i++) {
			sum += scores[i];
		}
		
		return sum;
	}
	
	public int getIN() {
		int sum = 0;
		
		for (int i = 9; i < 18; i++) {
			sum += scores[i];
		}
		
		return sum;
	}
	
	public int getTOT() {
		int sum = 0;
		
		for (int i = 0; i < 18; i++) {
			sum += scores[i];
		}
		
		return sum;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
