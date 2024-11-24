package Labs.Lab13;

/**
 * The type Packaged good.
 */
public class PackagedGood extends Product{
	private int quantity;
	private double pricePerUnit;
	
	/**
	 * Instantiates a new Packaged good.
	 */
	public PackagedGood() {
		super();
		this.quantity = 0;
		this.pricePerUnit = 0.0;
	}
	
	/**
	 * Instantiates a new Packaged good.
	 *
	 * @param aName      the name
	 * @param aRating    the rating
	 * @param numRatings the num ratings
	 * @param aQuantity  the quantity
	 * @param perUnit    the per unit
	 */
	public PackagedGood(String aName, double aRating, int numRatings, int aQuantity, double perUnit) {
		super(aName, aRating, numRatings);
		this.quantity = Math.max(aQuantity, 0);
		this.pricePerUnit = Math.max(perUnit, 0.0);
	}
	
	/**
	 * Gets quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Sets quantity.
	 *
	 * @param quantity the quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = Math.max(quantity, 0);
	}
	
	/**
	 * Gets price per unit.
	 *
	 * @return the price per unit
	 */
	public double getPricePerUnit() {
		return pricePerUnit;
	}
	
	/**
	 * Sets price per unit.
	 *
	 * @param pricePerUnit the price per unit
	 */
	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = Math.max(pricePerUnit, 0.0);
	}
	
	@Override
	public double getPrice() {
		return quantity * pricePerUnit;
	}
	
	@Override
	public String toString() {
		String price = String.format("%.2f", pricePerUnit);
		return super.toString() + "," + quantity + "," + price;
	}
}
