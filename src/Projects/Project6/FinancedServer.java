package Projects.Project6;

public class FinancedServer extends OwnedServer {
	private int loanTerm;
	private double apr;
	
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
}
