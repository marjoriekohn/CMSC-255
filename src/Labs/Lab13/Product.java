package Labs.Lab13;

/**
 * The type Product.
 */
abstract class Product implements Sellable, Comparable<Product> {
	private String name;
	private double rating;
	private int numRatings;
	
	/**
	 * Instantiates a new Product.
	 */
	public Product() {
		this.name = "";
		this.rating = 0.0;
		this.numRatings = 0;
	}
	
	/**
	 * Instantiates a new Product.
	 *
	 * @param aName      the name
	 * @param aRating    the rating
	 * @param numRatings the num ratings
	 */
	public Product(String aName, double aRating, int numRatings) {
		this.name = aName;
		this.numRatings = Math.max(numRatings, 0);
		this.rating = (this.numRatings == 0) ? 0 : Math.min(Math.max(aRating, 0), 5);
	}
	
	/**
	 * Gets name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets name.
	 *
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets rating.
	 *
	 * @return the rating
	 */
	public double getRating() {
		return rating;
	}
	
	/**
	 * Gets num of ratings.
	 *
	 * @return the num ratings
	 */
	public int getNumRatings() {
		return numRatings;
	}
	
	/**
	 * Add rating.
	 *
	 * @param newRating the new rating
	 */
	public void addRating(double newRating) {
		newRating = Math.min(Math.max(newRating, 0), 5);
		rating = (rating * numRatings + newRating) / (numRatings + 1);
		numRatings++;
	}
	
	public int compareTo(Product other) {
		return Double.compare(this.getPrice(), other.getPrice());
	}
	
	public abstract double getPrice();
	
	@Override
	public String toString() {
		String rating = String.format("%.1f", this.rating);
		String price = String.format("%.2f", getPrice());
		return name + ',' + rating + ',' + numRatings + ',' + price;
	}
}
