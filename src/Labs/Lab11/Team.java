/**
 * Team.java is a class that represents a team with a name, number of goals, and number of shots.
 *
 * @author Maggie Kohn
 * @version 2024.10.30
 * CMSC255.002
 */

package Labs.Lab11;

/**
 * Represents a team with a name, number of goals, and number of shots.
 */
public class Team {
	/** The name of the team
	 */
	private String name;
	/** The number of goals scored by the team
	 */
	private int numGoals;
	/** The number of shots taken by the team
	 */
	private double numShots;
	
	/**
	 * Constructs a team with default values.
	 */
	public Team() {
		this.name = "";
		this.numGoals = 0;
		this.numShots = 0;
	}
	
	/**
	 * Constructs a team with the given values.
	 * @param name the name of the team
	 * @param numGoals the number of goals scored by the team
	 * @param numShots the number of shots taken by the team
	 */
	public Team(String name, int numGoals, double numShots) {
		this.name = name;
		this.numGoals = numGoals;
		this.numShots = numShots;
	}
	
	/**
	 * Returns the name of the team.
	 * @return the name of the team
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the team.
	 * @param name the name of the team
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the number of goals scored by the team.
	 * @return the number of goals scored by the team
	 */
	public int getNumGoals() {
		return numGoals;
	}
	
	/**
	 * Sets the number of goals scored by the team.
	 * @param numGoals the number of goals scored by the team
	 */
	public void setNumGoals(int numGoals) {
		this.numGoals = numGoals;
	}
	
	/**
	 * Returns the number of shots taken by the team.
	 * @return the number of shots taken by the team
	 */
	public double getNumShots() {
		return numShots;
	}
	
	/**
	 * Sets the number of shots taken by the team.
	 * @param numShots the number of shots taken by the team
	 */
	public void setNumShots(double numShots) {
		this.numShots = numShots;
	}
}
