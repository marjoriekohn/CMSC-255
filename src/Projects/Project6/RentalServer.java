package Projects.Project6;

public class RentalServer extends Server {
	private double annualRent;
	
	public double getAnnualRent() {
		return annualRent;
	}
	
	public void setAnnualRent(double annualRent) {
		this.annualRent = annualRent;
	}
	
	public RentalServer(String brand, double annualRent) {
		super(brand);
		this.annualRent = annualRent;
	}
	
	public String toString() {}
}
