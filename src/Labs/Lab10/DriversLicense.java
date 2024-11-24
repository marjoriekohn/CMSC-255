/**
 * Lab10: Demonstrates the use of inheritance and polymorphism by creating a generic Card that is extended by a debit card, ID card, and driver's license class.
 *
 * @author Maggie Kohn
 * @version 2024.10.23
 * CMSC255.002
 */

package Labs.Lab10;

/**
 * Represents a driver's license with a name, ID number, expiration year, and expiration month. Extends the IDCard class.
 * @see IDCard
 */
public class DriversLicense extends IDCard{
	/**
	 * The expiration year.
	 */
	private int expirationYear;
	/**
	 * The expiration month.
	 */
	private Month expirationMonth;
	
	/**
	 * Default constructor that initializes the name to "Jane Smith", the ID number to 0, the expiration year to 1969, and the expiration month to January.
	 */
	public DriversLicense() {
		super();
		this.expirationYear = 1969;
		this.expirationMonth = Month.values()[0];
	}
	
	/**
	 * Constructor that initializes the name, ID number, expiration year, and expiration month to the specified values.
	 * @param name The name of the cardholder.
	 * @param idNumber The ID number.
	 * @param expirationYear The expiration year.
	 * @param expirationMonth The expiration month.
	 */
	public DriversLicense(String name, int idNumber, int expirationYear, Month expirationMonth) {
		super(name, idNumber);
		this.expirationYear = expirationYear;
		this.expirationMonth = expirationMonth;
	}
	
	/**
	 * Returns the expiration year.
	 * @return The expiration year.
	 */
	public int getExpirationYear() {
		return this.expirationYear;
	}
	
	/**
	 * Sets the expiration year.
	 * @param expirationYear The expiration year.
	 */
	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}
	
	/**
	 * Returns the expiration month.
	 * @return The expiration month.
	 */
	public Month getExpirationMonth() {
		return this.expirationMonth;
	}
	
	/**
	 * Sets the expiration month.
	 * @param expirationMonth The expiration month.
	 */
	public void setExpirationMonth(Month expirationMonth) {
		this.expirationMonth = expirationMonth;
	}
	
	/**
	 * Returns the cardholder's name, ID number, expiration year, and expiration month.
	 * @return A string, Card Holder: name ID Number: number Expiration Month & Year: month year.
	 */
	@Override
	public String toString() {
		return super.toString() + " Expiration Month & Year: " + this.expirationMonth + " " + this.expirationYear;
	}
}
