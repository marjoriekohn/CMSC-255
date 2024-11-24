package Labs.Lab13;

/**
 * The type Appliance.
 */
public class Appliance extends Product{
	private double basePrice;
	private int warrantyLength;
	
	/**
	 * Instantiates a new Appliance.
	 */
	public Appliance() {
		this.basePrice = 0.0;
		this.warrantyLength = 0;
	}
	
	/**
	 * Instantiates a new Appliance.
	 *
	 * @param aName      the name
	 * @param aRating    the rating
	 * @param numRatings the number of ratings
	 * @param price      the price
	 * @param warranty   the warranty
	 */
	public Appliance(String aName, double aRating, int numRatings, double price, int warranty) {
		super(aName, aRating, numRatings);
		this.basePrice = getPrice();
		this.warrantyLength = warranty;
		
	}
	
	/**
	 * Gets base price.
	 *
	 * @return the base price
	 */
	public double getBasePrice() {
		return basePrice;
	}
	
	/**
	 * Sets base price.
	 *
	 * @param basePrice the base price
	 */
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	
	/**
	 * Gets warranty length.
	 *
	 * @return the warranty length
	 */
	public int getWarrantyLength() {
		return warrantyLength;
	}
	
	/**
	 * Sets warranty length.
	 *
	 * @param warrantyLength the warranty length
	 */
	public void setWarrantyLength(int warrantyLength) {
		this.warrantyLength = warrantyLength;
	}
	
	@Override
	public double getPrice() {
		return basePrice + basePrice * 0.05 * warrantyLength;
	}
	
	@Override
	public String toString() {
		String price = String.format("%.2f", basePrice);
		return super.toString() + "," + price + "," + warrantyLength;
	}
}
