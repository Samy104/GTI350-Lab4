package gti350.golfscore.domain;

/**
 * A golf course.
 * The magic number 18 is often used here. It's hardly a magic number.
 * 
 * @author Simon RG
 */
public class Course {
	
	private int id;
	private String name;
	private int drawableId;
	
	/**
	 * Array containing the par for each hole.
	 * This array must have a length of 18.
	 */
	private int[] pars;
	
	/**
	 * Fully dependency-injected constructor.
	 */
	public Course(int id, String name, int[] pars, int drawableId) {
		if (pars.length != 18) {
			throw new IllegalArgumentException("Length of 'pars' array must be 18.");
		}
		
		this.id = id;
		this.name = name;
		this.pars = pars;
		this.drawableId = drawableId;
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
	
	public int getDrawableId() {
		return this.drawableId;
	}
	
	public void setDrawableId(int drawableId) {
		this.drawableId = drawableId;
	}
	
	public int[] getPars() {
		return pars;
	}
	
	/**
	 * Returns the par for the specified hole.
	 * The hole must be between 1 and 18, otherwise this
	 * returns 0.
	 * 
	 * @param hole 
	 * @return par
	 */
	public int getPar(int hole) {
		if (hole < 1 || hole > 18) {
			return 0;
		}
		
		return pars[hole - 1];
	}
	
	/**
	 * Sets the par for the specified hole.
	 * The hole must be between 1 and 18, otherwise this
	 * does nothing.
	 * 
	 * @param hole
	 * @param par
	 */
	public void setPar(int hole, int par) {
		if (hole < 1 || hole > 18) return;
		pars[hole - 1] = par;
	}
	
	/**
	 * Increments the par by 1 for the specified hole.
	 * The hole must be between 1 and 18, otherwise this
	 * does nothing.
	 * 
	 * @param hole
	 */
	public void incrementPar(int hole) {
		if (hole < 1 || hole > 18) return;
		pars[hole - 1]++;
	}

	/**
	 * Decrements the par by 1 for the specifield hole.
	 * The hole must be between 1 and 18, otherwise this
	 * does nothing.
	 * 
	 * @param hole
	 */
	public void decrementPar(int hole) {
		if (hole < 1 || hole > 18 || pars[hole - 1] <= 0) return;
		pars[hole - 1]--;
	}
	
	public int getOUT() {
		int sum = 0;
		
		for (int i = 0; i < 9; i++) {
			sum += pars[i];
		}
		
		return sum;
	}
	
	public int getIN() {
		int sum = 0;
		
		for (int i = 9; i < 18; i++) {
			sum += pars[i];
		}
		
		return sum;
	}
	
	public int getTOT() {
		int sum = 0;
		
		for (int i = 0; i < 18; i++) {
			sum += pars[i];
		}
		
		return sum;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
