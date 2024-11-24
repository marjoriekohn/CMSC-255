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
		double maintenanceCost = super.getMaintenanceCost();
		double failureRate = super.getFailureRate();
		
		if (years > loanTerm) {
			return baseCost * apr * years + (baseCost / loanTerm) * years + (1 + failureRate) * years * maintenanceCost;
		} else {
			return baseCost * apr * loanTerm + super.getOperatingCost(years);
		}
	}
	
	public String toString() {
		return super.toString() + "," + loanTerm + "," + apr;
	}
}
