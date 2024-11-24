package Labs.Lab13;

/**
 * The type Produce.
 */
public class Produce extends Product {
	/**
	 * The Weight.
	 */
	private double weight;
	/**
	 * The Price per lb.
	 */
	private double pricePerLb;
	
	/**
	 * Instantiates a new Produce.
	 */
	public Produce() {
		this.weight = 0.0;
		this.pricePerLb = 0.0;
	}
	
	/**
	 * Instantiates a new Produce.
	 *
	 * @param aName      the name
	 * @param aRating    the rating
	 * @param numRatings the num ratings
	 * @param aWeight    the weight
	 * @param perLb      the per lb
	 */
	public Produce(String aName, double aRating, int numRatings, double aWeight, double perLb) {
		super(aName, aRating, numRatings);
		this.weight = Math.max(aWeight, 0.0);
		this.pricePerLb = Math.max(perLb, 0.0);
	}
	
	/**
	 * Gets weight.
	 *
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}
	
	/**
	 * Sets weight.
	 *
	 * @param weight the weight
	 */
	public void setWeight(double weight) {
		this.weight = Math.max(weight, 0.0);
	}
	
	/**
	 * Gets price per lb.
	 *
	 * @return the price per lb
	 */
	public double getPricePerLb() {
		return pricePerLb;
	}
	
	/**
	 * Sets price per lb.
	 *
	 * @param pricePerLb the price per lb
	 */
	public void setPricePerLb(double pricePerLb) {
		this.pricePerLb = Math.max(pricePerLb, 0.0);
	}
	
	@Override
	public double getPrice() {
		return weight * pricePerLb;
	}
	
	@Override
	public String toString() {
		String weight = String.format("%.2f", this.weight);
		String price = String.format("%.2f", pricePerLb);
		return super.toString() + "," + weight + "," + price;
	}
}
