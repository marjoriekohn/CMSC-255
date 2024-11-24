/**
 * Lab10: Demonstrates the use of inheritance and polymorphism by creating a generic Card that is extended by a debit card, ID card, and driver's license class.
 *
 * @author Maggie Kohn
 * @version 2024.10.23
 * CMSC255.002
 */

package Labs.Lab10;

/**
 * Represents a debit card with a card number and pin. Extends the Card class.
 * @see Card
 */
public class DebitCard extends Card{
	/**
	 * The card number.
	 */
	private int cardNumber;
	/**
	 * The pin.
	 */
	private int pin;
	
	/**
	 * Default constructor that initializes the name to "Jane Doe", the card number to 00000000, and the pin to 0.
	 */
	public DebitCard() {
		super("Jane Doe");
		this.cardNumber = 00000000;
		this.pin = 0;
	}
	
	/**
	 * Constructor that initializes the name, card number, and pin to the specified values.
	 * @param name The name of the cardholder.
	 * @param cardNumber The card number.
	 * @param pin The pin.
	 */
	public DebitCard(String name, int cardNumber, int pin) {
		super(name);
		this.cardNumber = cardNumber;
		this.pin = pin;
	}
	
	/**
	 * Returns the card number.
	 * @return The card number.
	 */
	public int getCardNumber() {
		return this.cardNumber;
	}
	
	/**
	 * Sets the card number.
	 * @param cardNumber The card number.
	 */
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	/**
	 * Returns the pin.
	 * @return The pin.
	 */
	public int getPin() {
		return this.pin;
	}
	
	/**
	 * Sets the pin.
	 * @param pin The pin.
	 */
	public void setPin(int pin) {
		this.pin = pin;
	}
	
	/**
	 * Returns the cardholder's name, card number.
	 * @return A string, Card Holder: name Card Number: number
	 */
	@Override
	public String toString() {
		return super.toString() + " Card Number: " + this.cardNumber;
	}
}
