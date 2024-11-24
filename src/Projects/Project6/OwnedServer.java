package Projects.Project6;

public class OwnedServer extends Server {
	private double maintenanceCost;
	private double failureRate;
	private double baseCost;
	
	public OwnedServer(String brand, double maintenanceCost, double failureRate, double baseCost) {
		super(brand);
		this.maintenanceCost = maintenanceCost;
		this.failureRate = failureRate;
		this.baseCost = baseCost;
	}
	
	public double getMaintenanceCost() {
		return maintenanceCost;
	}
	
	public void setMaintenanceCost(double maintenanceCost) {
		this.maintenanceCost = maintenanceCost;
	}
	
	public double getFailureRate() {
		return failureRate;
	}
	
	public void setFailureRate(double failureRate) {
		this.failureRate = failureRate;
	}
	
	public double getBaseCost() {
		return baseCost;
	}
	
	public void setBaseCost(double baseCost) {
		this.baseCost = baseCost;
	}
}
