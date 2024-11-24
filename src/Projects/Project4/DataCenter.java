/**
 * Project 4 - Data Centers
 * <p>
 * This program models a data center and server racks. Data centers contain server racks.
 * <hr>
 * @author Maggie Kohn
 * @version 10.16.2024
 * CMSC255.002
 */
package Projects.Project4;

import java.util.ArrayList;

/**
 * DataCenter class models a data center, which contains an arraylist of server racks.
 * @see ServerRack
 */
public class DataCenter {
	/**
	 * the name of the data center
	 */
	private String centerName;
	/**
	 * the capacity of the data center
	 */
	private int capacity;
	/**
	 * the budget of the data center
	 */
	private double budget;
	/**
	 * the list of server racks in the data center
	 */
	private ArrayList<ServerRack> rackList;
	
	/**
	 * The default constructor for DataCenter sets the centerName to an empty string, the capacity to 0, the budget to 0, and initializes the rackList.
	 */
	public DataCenter() {
		centerName = "";
		capacity = 0;
		budget = 0;
		rackList = new ArrayList<ServerRack>();
	}
	
	/**
	 * The parameterized constructor for DataCenter sets the centerName, capacity, and budget to the given values, and initializes the rackList.
	 * @param aCenterName the name of the data center
	 * @param aCapacity the capacity of the data center
	 * @param aBudget the budget of the data center
	 */
	public DataCenter(String aCenterName, int aCapacity, double aBudget) {
		this.centerName = aCenterName;
		this.capacity = aCapacity;
		this.budget = aBudget;
		rackList = new ArrayList<ServerRack>();
	}
	
	/**
	 * Gets the name of the data center.
	 *
	 * @return the name of the data center
	 */
	public String getCenterName() {
		return centerName;
	}
	
	/**
	 * Sets the name of the data center.
	 *
	 * @param centerName the name of the data center
	 */
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	
	/**
	 * Gets the capacity of the data center.
	 *
	 * @return the capacity of the data center
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * Sets the capacity of the data center.
	 *
	 * @param capacity the capacity of the data center
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	/**
	 * Gets the budget of the data center.
	 *
	 * @return the budget of the data center
	 */
	public double getBudget() {
		return budget;
	}
	
	/**
	 * Sets the budget of the data center.
	 *
	 * @param budget the budget of the data center
	 */
	public void setBudget(double budget) {
		this.budget = budget;
	}
	
	/**
	 * Determines if the total operating cost of the server racks in the data center is less than or equal to the budget.
	 * Uses an enhanced for loop to iterate through the rackList and add the operating cost of each server rack to the total operating cost.
	 *
	 * @return true if the total operating cost is less than or equal to the budget, false otherwise
	 */
	public boolean isInBudget() {
		double totalOperatingCost = 0;
		for(ServerRack rack : rackList) {
			totalOperatingCost += rack.getOperatingCost();
		}
		return (totalOperatingCost <= budget);
	}
	
	/**
	 * Adds the passed in server rack to the data center by adding it to the rackList.
	 *
	 * @param serverRack the server rack to add to the data center
	 */
	public void addServerRack(ServerRack serverRack) {
		rackList.add(serverRack);
	}
	
	/**
	 * Gets the number of server racks in the data center by calling .size() on the rackList.
	 *
	 * @return the number of server racks in the data center
	 */
	public int getNumServerRacks() {
		return rackList.size();
	}
	
	/**
	 * Gets the list of server racks in the data center.
	 *
	 * @return the list of server racks in the data center
	 */
	public ArrayList<ServerRack> getServerRacks() {
		return rackList;
	}
	
	/**
	 * Calls the toString method of each server rack in the rackList and appends the results to a StringBuilder.
	 *
	 * @return a string representation of the data center
	 */
	public String toString() {
		StringBuilder dataCenterInfo = new StringBuilder();
		dataCenterInfo.append(centerName);
		dataCenterInfo.append("\n").append(capacity);
		dataCenterInfo.append("\n").append(String.format("%.2f", budget));
		dataCenterInfo.append("\nServerRacks:\n");
		for (ServerRack rack : rackList) {
			dataCenterInfo.append(rack.toString());
		}
		return dataCenterInfo.toString();
	}
}
