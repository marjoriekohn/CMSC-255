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

/**
 * ServerRack class models a single server rack, which a data center may contain many of.
 * @see DataCenter
 */
public class ServerRack {
	/**
	 * the brand of a server rack
	 */
	private String brand;
	/**
	 * the ID of a server rack
	 */
	private int rackID;
	/**
	 * the operating cost of a server rack
	 */
	private double operatingCost;
	/**
	 * the operating system of a server rack
	 */
	private OS operatingSystem;
	/**
	 * the cooling system of a server rack
	 */
	private Cooling cooling;
	/**
	 * the ownership of a server rack
	 */
	private Ownership ownership;
	
	/**
	 * The default constructor for ServerRack sets the brand to an empty string, the rackID to 0, the operatingCost to 0, the operatingSystem, cooling, and ownership to the first value in their respective enums.
	 */
	public ServerRack () {
		brand = "";
		rackID = 0;
		operatingCost = 0;
		operatingSystem = OS.values()[0];
		cooling = Cooling.values()[0];
		ownership = Ownership.values()[0];
	}
	
	/**
	 * The parameterized constructor for ServerRack sets the brand, rackID, operatingCost, operatingSystem, cooling, and ownership to the given values.
	 * @param aBrand the brand of the server rack
	 * @param aRackID the ID of the server rack
	 * @param anOperatingCost the operating cost of the server rack
	 * @param anOperatingSystem the operating system of the server rack
	 * @param aCooling the cooling system of the server rack
	 * @param anOwnership the ownership of the server rack
	 */
	public ServerRack (String aBrand, int aRackID, double anOperatingCost, OS anOperatingSystem, Cooling aCooling, Ownership anOwnership) {
		brand = aBrand;
		rackID = aRackID;
		operatingCost = anOperatingCost;
		operatingSystem = anOperatingSystem;
		cooling = aCooling;
		ownership = anOwnership;
	}
	
	/**
	 * Gets the brand of a server rack.
	 *
	 * @return the brand of a server rack
	 */
	public String getBrand() {
		return brand;
	}
	
	/**
	 * Sets the brand of a server rack.
	 *
	 * @param brand the brand of a server rack
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	/**
	 * Gets the ID of a server rack.
	 *
	 * @return the ID of a server rack
	 */
	public int getRackID() {
		return rackID;
	}
	
	/**
	 * Sets the ID of a server rack.
	 *
	 * @param rackID the ID of a server rack
	 */
	public void setRackID(int rackID) {
		this.rackID = rackID;
	}
	
	/**
	 * Gets the operating cost of a server rack.
	 *
	 * @return the operating cost of a server rack
	 */
	public double getOperatingCost() {
		return operatingCost;
	}
	
	/**
	 * Sets the operating cost of a server rack.
	 *
	 * @param operatingCost the operating cost of a server rack
	 */
	public void setOperatingCost(double operatingCost) {
		this.operatingCost = operatingCost;
	}
	
	/**
	 * Gets the operating system of a server rack.
	 *
	 * @return the operating system of a server rack
	 */
	public OS getOperatingSystem() {
		return operatingSystem;
	}
	
	/**
	 * Sets the operating system of a server rack.
	 *
	 * @param operatingSystem the operating system of a server rack
	 */
	public void setOperatingSystem(OS operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	
	/**
	 * Gets the cooling system of a server rack.
	 *
	 * @return the cooling system of a server rack
	 */
	public Cooling getCooling() {
		return cooling;
	}
	
	/**
	 * Sets the cooling system of a server rack.
	 *
	 * @param cooling the cooling system of a server rack
	 */
	public void setCooling(Cooling cooling) {
		this.cooling = cooling;
	}
	
	/**
	 * Gets the ownership of a server rack.
	 *
	 * @return the ownership of a server rack
	 */
	public Ownership getOwnership() {
		return ownership;
	}
	
	/**
	 * Sets the ownership of a server rack.
	 *
	 * @param ownership the ownership of a server rack
	 */
	public void setOwnership(Ownership ownership) {
		this.ownership = ownership;
	}
	
	/**
	 * A string representing a server rack, with the operating cost formatted to two decimal places.
	 *
	 * @return a string representation of a server rack
	 */
	public String toString() {
		return "\n\t" + brand +
			"\n\t" + rackID +
			"\n\t" + String.format("%.2f", operatingCost) +
			"\n\t" + operatingSystem +
			"\n\t" + cooling +
			"\n\t" + ownership +
			"\n";
	}
}
