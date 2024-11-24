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
 * Cooling enum allows for four different types of cooling for a server rack.
 */
public enum Cooling {
	
	/**
	 * The server rack is air cooled.
	 */
	AIR,
	
	/**
	 * The server rack is liquid cooled.
	 */
	LIQUID,
	
	/**
	 * The server rack is cooled with a heat sink.
	 */
	HEAT_SINK,
	
	/**
	 * The server rack uses a hybrid of cooling techniques.
	 */
	HYBRID
}
