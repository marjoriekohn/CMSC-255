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
 * Ownership enum allows for three different types of ownership for a server rack.
 */
public enum Ownership {
	/**
	 * The server rack is rented.
	 */
	RENTAL,
	
	/**
	 * The server rack is owned.
	 */
	OWNED,
	
	/**
	 * The server rack is financed.
	 */
	FINANCED
}
