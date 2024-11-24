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
 * DataCenterServerRackTest tests the DataCenter and ServerRack classes.
 * @see DataCenter
 * @see ServerRack
 */
public class DataCenterServerRackTest {
	/**
	 * The main method creates two data centers and three server racks, adds the server racks to the data centers, and prints the data centers.
	 */
	public static void main(String[] args) {
		
		DataCenter dataCenter1 = new DataCenter("VCU2", 198267, 1254730.00);
		DataCenter dataCenter2 = new DataCenter("Simple Servers", 872946, 524330.00);
		
		ServerRack serverRack1 = new ServerRack("HP", 12086, 125000.00, OS.LINUX, Cooling.LIQUID, Ownership.RENTAL);
		ServerRack serverRack2 = new ServerRack("Dell", 47168, 250000.00, OS.WINDOWS, Cooling.HEAT_SINK, Ownership.FINANCED);
		ServerRack serverRack3 = new ServerRack("Asus", 94679, 136467.00, OS.MAC, Cooling.LIQUID, Ownership.OWNED);
		
		dataCenter1.addServerRack(serverRack1);
		dataCenter1.addServerRack(serverRack2);
		dataCenter2.addServerRack(serverRack3);
		
		System.out.println(dataCenter1);
		System.out.println(dataCenter2);
	}
}
