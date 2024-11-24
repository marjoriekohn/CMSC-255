/**
 * Lab10: Demonstrates the use of inheritance and polymorphism by creating a generic Card that is extended by a debit card, ID card, and driver's license class.
 *
 * @author Maggie Kohn
 * @version 2024.10.23
 * CMSC255.002
 */

package Labs.Lab10;

/**
 * Represents a generic card with a name.
 */
public class Card {
	/**
	 * The name of the cardholder.
	 */
	private String name;
	
	/**
	 * Default constructor that initializes the name to an empty string.
	 */
	public Card() {
		this.name = "";
	}
	
	/**
	 * Constructor that initializes the name to the specified value.
	 * @param name The name of the cardholder.
	 */
	public Card(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the name of the cardholder.
	 * @return The name of the cardholder.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the name of the cardholder.
	 * @param name The name of the cardholder.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the cardholder's name.
	 * @return A string, Card Holder: cardholder's name.
	 */
	@Override
	public String toString() {
		return "Card Holder: " + this.name;
	}
}
