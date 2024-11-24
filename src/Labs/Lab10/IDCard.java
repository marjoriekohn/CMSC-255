/**
 * Lab10: Demonstrates the use of inheritance and polymorphism by creating a generic Card that is extended by a debit card, ID card, and driver's license class.
 *
 * @author Maggie Kohn
 * @version 2024.10.23
 * CMSC255.002
 */
package Labs.Lab10;

/**
 * Represents an ID card with a name and ID number. Extends the Card class.
 * @see Card
 */
public class IDCard extends Card{
	/**
	 * The ID number.
	 */
	private int idNumber;
	
	/**
	 * Default constructor that initializes the name to "Jane Smith" and the ID number to 0.
	 */
	public IDCard() {
		super("Jane Smith");
		this.idNumber = 0;
	}
	
	/**
	 * Constructor that initializes the name and ID number to the specified values.
	 * @param name The name of the cardholder.
	 * @param idNumber The ID number.
	 */
	public IDCard(String name, int idNumber) {
		super(name);
		this.idNumber = idNumber;
	}
	
	/**
	 * Returns the ID number.
	 * @return The ID number.
	 */
	public int getIdNumber() {
		return this.idNumber;
	}
	
	/**
	 * Sets the ID number.
	 * @param idNumber The ID number.
	 */
	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}
	
	/**
	 * Returns the cardholder's name and ID number.
	 * @return A string, Card Holder: name ID Number: number.
	 */
	@Override
	public String toString() {
		return super.toString() + " ID Number: " + this.idNumber;
	}
}
