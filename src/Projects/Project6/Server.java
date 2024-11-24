package Projects.Project6;

abstract public class Server {
	private String brand;
	private static int compareNumYears = 5;
	
	public Server(String brand) {
		this.brand = brand;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public static int getCompareNumYears() {
		return compareNumYears;
	}
	
	public static void setCompareNumYears(int compareNumYears) {
		Server.compareNumYears = compareNumYears;
	}
	
	abstract double getOperatingCost(int years);
	
	public String toString() {}
}
