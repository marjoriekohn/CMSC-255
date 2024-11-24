/**
 * CompareDataCenters.java
 * A program that compares construction parameters of data centers from various firms.
 *
 * @author Maggie Kohn
 * @version 2024.10.25
 * CMSC255.002
 */

package Projects.Project2;
import java.util.Scanner;

public class CompareDataCenters {
	
	/**
	 * calculates the average of the values in a double array
	 * @param values an array of double values
	 * @return the average of the values in the array
	 **/
	public static double calcAvg(double[] values) {
		double total = 0;
		for (double value : values) {
			total += value;
		}
		return total / values.length;
	}
	
	/**
	 * finds and returns the largest value in a double array
	 * @param values an array of double values
	 * @return the largest value in the array, a double rounded to 2 decimals places
	 **/
	public static double findHighValue(double[] values) {
		double highValue = 0;
		for (double value : values) {
			highValue = Math.max(value, highValue);
		}
		return highValue;
	}
	
	/**
	 * finds and returns the smallest value in a double array
	 * @param values an array of double values
	 * @return the smallest value in the array, a double rounded to 2 decimals places
	 **/
	public static double findLeastValue(double[] values) {
		double leastValue = values[0];
		for (double value : values) {
			leastValue = Math.min(value, leastValue);
		}
		return leastValue;
	}
	
	/**
	 * finds the construction firms with the two largest values in a double array
	 * @param names a String array of names
	 * @param values a double array of values
	 * @return a String array of the firms associated with the highest values
	 **/
	public static String[] findHighestTwo(String[] names, double[] values) {
		String highestName1 = "";
		String highestName2 = "";
		double highestValue1 = findHighValue(values);
		double highestValue2 = 0;
		
		for(int i = 0; i <= values.length - 1; i++){
			if (values[i] == highestValue1) {
				highestName1 = names[i];
			} else if (values[i] > highestValue2) {
				highestName2 = names[i];
				highestValue2 = values[i];
			}
		}
		return new String[]{highestName1, highestName2};
	}
	
	/**
	 * finds the construction firms with the two smallest values in a double array
	 * @param names a String array of names
	 * @param values a double array of values
	 * @return a String array of the firms associated with the lowest values
	 **/
	public static String[] findLeastTwo(String[] names, double[] values) {
		String lowestName1 = "";
		String lowestName2 = "";
		double lowestValue2 = values[0];
		double lowestValue1 = findLeastValue(values);
		
		for(int i = 0; i <= values.length - 1; i++){
			if (values[i] == lowestValue1) {
				lowestName1 = names[i];
			} else if (values[i] < lowestValue2) {
				lowestValue2 = values[i];
				lowestName2 = names[i];
			}
		}
		return new String[]{lowestName1, lowestName2};
	}
	
	/**
	 * finds and returns the two lowest values in a double array
	 * @param names a String array of names
	 * @param constructionFirm a String, the firm the user is looking for
	 * @return a boolean indicating if the firm exists in the array
	 **/
	public static boolean findConstructionFirm(String[] names, String constructionFirm) {
		boolean doesFirmExist = false;
		
		for(String name : names){
			if (name.equals(constructionFirm)) {
				doesFirmExist = true;
			}
		}
		return doesFirmExist;
	}
	
	public static void main(String[] args) {
		
		// initializing sample parallel arrays
		String[] constructionFirms = {"Turner","Holder","HITT","DPR","Fortis","Mortenson","JE Dunn","Clune"};
		double[] constructionCosts = {442.2,584.8,420.7,726.5,574.1,717.0,763.4,527.9};
		double[] itLoad = {168.4, 307.2, 21.8, 271.0, 50.5, 117.7, 199.6, 289.1};
		double[] operatingCosts = {19.0, 26.4, 11.1, 24.5, 12.7, 16.2, 20.7, 25.5};
		
		// calculating and displaying the average construction costs
		double averageConstructionCosts = calcAvg(constructionCosts);
		System.out.printf("The average construction cost in dollars: $%.2fM\n", averageConstructionCosts);
		
		// calculating and displaying the average construction costs
		double megawattsLoad = calcAvg(itLoad);
		System.out.printf("The average IT Load in megawatts is: %.2fMW \n", megawattsLoad);
		
		// finding and returning the highest value in construction costs
		double highestConstructionCosts = findHighValue(constructionCosts);
		System.out.printf("The highest construction cost in dollars: $%.2fM\n", highestConstructionCosts);
		
		// finding and returning the smallest value in operating costs
		double leastOperatingCosts = findLeastValue(operatingCosts);
		System.out.printf("The least operating cost in dollars is: $%.2fM\n", leastOperatingCosts);
		
		// finding and returning the two firms with the highest construction costs
		String[] highestFirms = findHighestTwo(constructionFirms, constructionCosts);
		System.out.println("The two construction firms with the highest construction cost are: ");
		for (String firm : highestFirms) {
			System.out.println(firm);
		}
		
		// finding and returning the two firms with the lowest IT Load
		String[] lowestFirms = findLeastTwo(constructionFirms, itLoad);
		System.out.println("The two construction firms with the lowest IT Load are: ");
		for (String firm : lowestFirms) {
			System.out.println(firm);
		}
		
		// asking the user to enter the name of the construction firm
		Scanner stdin = new Scanner(System.in);
		System.out.println("Enter a construction firm: ");
		String userFirm = stdin.nextLine().trim();
		
		// finding if the firm exists in the array
		boolean doesFirmExist = findConstructionFirm(constructionFirms, userFirm);
		if (doesFirmExist) {
			System.out.println(userFirm + " is a construction firm in the array.");
		} else {
			System.out.println(userFirm + " is not a construction firm in the array.");
		}
	}
}
