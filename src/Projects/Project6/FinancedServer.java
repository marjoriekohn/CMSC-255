package Projects.Project6;

public class FinancedServer extends OwnedServer {
	private int loanTerm;
	private double apr;
	
	public FinancedServer() {
		super();
		this.loanTerm = 0;
		this.apr = 0.0;
	}
	
	public FinancedServer(String brand, double maintenanceCost, double failureRate, double baseCost, int loanTerm, double apr) {
		super(brand, maintenanceCost, failureRate, baseCost);
		this.loanTerm = loanTerm;
		this.apr = apr;
	}
	
	public int getLoanTerm() {
		return loanTerm;
	}
	
	public void setLoanTerm(int loanTerm) {
		this.loanTerm = loanTerm;
	}
	
	public double getApr() {
		return apr;
	}
	
	public void setApr(double apr) {
		this.apr = apr;
	}
	
	public double getOperatingCost(int years) {
		double baseCost = super.getBaseCost();
		double operatingCosts = super.getOperatingCost(years);
		
		if (years > loanTerm) {
			return (baseCost * apr * loanTerm) + operatingCosts;
		} else {
			return baseCost * apr * years + (baseCost / (double) loanTerm) * years + (operatingCosts - baseCost);
		}
	}
	
	public String toString() {
		return super.toString().substring(0, super.toString().length() - 7) + loanTerm + "," + String.format("%.2f", apr);
	}
}
