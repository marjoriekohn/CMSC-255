package Projects.Project6;

import java.util.Arrays;

public abstract class Server implements Comparable<Server> {
	private String brand;
	private static int compareNumYears = 5;
	
	public Server(){
		this.brand = "";
	}
	
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
	
	public abstract double getOperatingCost(int years);
	
	public int compareTo(Server other) {
		return Double.compare(this.getOperatingCost(compareNumYears), other.getOperatingCost(compareNumYears));
	}
	
	public String toString() {
		return brand;
	}
}
