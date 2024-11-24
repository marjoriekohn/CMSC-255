/**
 * Project 5 - Data Centers Cost Analysis
 * <p>
 * This program models a data center and calculates the mean, highest, and lowest values of construction cost, IT load, and operating cost.
 * <hr>
 * @author Maggie Kohn
 * @version 11.08.2024
 * CMSC255.002
 */

package Projects.Project5;

/**
 * Represents a data center.
 */
public class DataCenter {
	/**
	 * Represents the name of the construction firm.
	 */
	private String constructionFirm;
	/**
	 * Represents the cost of construction.
	 */
	private double constructionCost;
	/**
	 * Represents the IT load.
	 */
	private double ITLoad;
	/**
	 * Represents the operating cost.
	 */
	private double operatingCost;
	
	/**
	 * Default constructor, sets the construction firm to an empty string, the construction cost, the IT load, and the operating cost to 0.0.
	 */
	public DataCenter() {
		this.constructionFirm = "";
		this.constructionCost = 0.0;
		this.ITLoad = 0.0;
		this.operatingCost = 0.0;
	}
	
	/**
	 * Parameterized constructor, sets the construction firm, the construction cost, the IT load, and the operating cost to the specified values.
	 * @param constructionFirm the name of the construction firm
	 * @param constructionCost the cost of construction
	 * @param ITLoad the IT load
	 * @param operatingCost the operating cost
	 */
	public DataCenter(String constructionFirm, double constructionCost, double ITLoad, double operatingCost) {
		this.constructionFirm = constructionFirm;
		this.constructionCost = constructionCost;
		this.ITLoad = ITLoad;
		this.operatingCost = operatingCost;
	}
	
	/**
	 * Returns the name of the construction firm.
	 * @return the name of the construction firm
	 */
	public String getConstructionFirm() {
		return constructionFirm;
	}
	
	/**
	 * Sets the name of the construction firm to the specified value.
	 * @param constructionFirm the name of the construction firm
	 */
	public void setConstructionFirm(String constructionFirm) {
		this.constructionFirm = constructionFirm;
	}
	
	/**
	 * Returns the cost of construction.
	 * @return the cost of construction
	 */
	public double getConstructionCost() {
		return constructionCost;
	}
	
	/**
	 * Sets the cost of construction to the specified value.
	 * @param constructionCost the cost of construction
	 */
	public void setConstructionCost(double constructionCost) {
		this.constructionCost = constructionCost;
	}
	
	/**
	 * Returns the IT load.
	 * @return the IT load
	 */
	public double getITLoad() {
		return ITLoad;
	}
	
	/**
	 * Sets the IT load to the specified value.
	 * @param ITLoad the IT load
	 */
	public void setITLoad(double ITLoad) {
		this.ITLoad = ITLoad;
	}
	
	/**
	 * Returns the operating cost.
	 * @return the operating cost
	 */
	public double getOperatingCost() {
		return operatingCost;
	}
	
	/**
	 * Sets the operating cost to the specified value.
	 * @param operatingCost the operating cost
	 */
	public void setOperatingCost(double operatingCost) {
		this.operatingCost = operatingCost;
	}
	
	/**
	 * Returns a string representation of the data center.
	 * @return a string representation of the data center
	 */
	public String toString() {
		return
			constructionFirm + " " +
				String.format("%.2f", constructionCost) + " " +
				String.format("%.2f", ITLoad) + " " +
				String.format("%.2f", operatingCost);
	}
	
	/**
	 * Returns the value of the specified attribute.
	 * @param attribute the attribute to retrieve
	 * @return the value of the specified attribute
	 */
	public double getAttribute(DataCenterAttributes attribute) {
		switch (attribute) {
			case CONSTRUCTION_COST:
				return constructionCost;
			case IT_LOAD:
				return ITLoad;
			case OPERATING_COST:
				return operatingCost;
			default:
				throw new IllegalArgumentException("Invalid attribute" + attribute);
		}
	}
}
