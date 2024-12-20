package Projects.Project6;

import java.util.Arrays;

public class RentalServer extends Server {
	private double annualRent;
	
	public RentalServer() {
		super();
		this.annualRent = 0.0;
	}
	
	public RentalServer(String brand, double annualRent) {
		super(brand);
		this.annualRent = annualRent;
	}
	
	public double getAnnualRent() {
		return annualRent;
	}
	
	public void setAnnualRent(double annualRent) {
		this.annualRent = annualRent;
	}
	
	public double getOperatingCost(int years) {
		return annualRent * Server.getCompareNumYears();
	}
	
	public String toString() {
		return super.toString() + "," + String.format("%.2f", annualRent) + ",N/A,N/A,N/A,N/A,N/A";
	}
}
