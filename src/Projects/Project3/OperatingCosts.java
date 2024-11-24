/**
 * OperatingCosts.java
 * This program determines the maintenance costs of various data centers.
 *
 * @author Maggie Kohn
 * @version 2024.10.11
 * CMSC255.002
 */

package Projects.Project3;
import java.util.Arrays;

public class OperatingCosts {
	
	/**
	 * This method splits a string into an array of strings using the comma as a delimiter
	 * @param metricNames - a string of metric names separated by commas
	 * @return an array of strings, the metric names
	 */
	public static String[] getOperatingMetrics(String metricNames) {
		return metricNames.split(",");
	}
	
	/**
	 * This method uses nested for loops to split a string into a 2D array of doubles
	 * @param dataCenterMetrics - a string of data center metrics, centers metrics separated by <>, metrics separated by commas
	 * @return a 2D array of doubles, each data centers metrics
	 */
	public static double[][] getDataCenters(String dataCenterMetrics) {
		String[] metricsPerCenter = dataCenterMetrics.split("<>");
		int rows = metricsPerCenter.length;
		double[][] metricValues = new double[rows][];
		
		// Split the metrics for each data center and convert them to doubles
		for (int i = 0; i < rows; i++) {
			String[] value = metricsPerCenter[i].split(",");
			metricValues[i] = new double[value.length];
			for (int j = 0; j < value.length; j++) {
				metricValues[i][j] = Double.parseDouble(value[j]);
			}
		}
		return metricValues;
	}
	
	/**
	 * This method uses a for loop to calculate the annual operation cost for each data center and determines which data centers met the criteria
	 * @param dataCenters - a 2D array of doubles, the data center metrics
	 * @return an array of integers, the data centers whose annual operation cost is less than or equal to 18.5
	 */
	public static int[] findAnnualOperationCost(double[][] dataCenters) {
		int[] centersMet = new int[dataCenters.length];
		int count = 0;
		
		// iterate through the data centers and calculate the annual cost
		for (int row = 0; row < dataCenters.length; row++) {
			double failure = dataCenters[row][0];
			double hard = dataCenters[row][1];
			double main = dataCenters[row][2];
			double power = dataCenters[row][3];
			double area = dataCenters[row][4];
			double climate = dataCenters[row][5];
			double annualCost = (1 + failure) * (hard * main + power + (area / climate));
			
			// Check if the annual cost is less than or equal to 18.5 and add the data center to the array
			if (annualCost <= 18.5) {
				centersMet[count] = row + 1;
				count++;
			}
		}
		return Arrays.copyOf(centersMet, count);
	}
	
	/**
	 * This method uses a for loop to search for the two metrics with the highest values for a specified data center
	 * @param dataCenters - a 2D array of doubles, the data center metrics
	 * @param metrics - an array of strings, the metric names
	 * @param dataCenter - an integer, the data center number
	 * @return a string, the two metrics with the highest values for that data center
	 */
	public static String searchHighestMetric(double[][] dataCenters, String[] metrics, int dataCenter) {
		int highestIndex1 = -1;
		int highestIndex2 = -1;
		double highest1 = 0;
		double highest2 = 0;
		double[] center = dataCenters[dataCenter - 1];
		
		// Find the two metrics with the highest values for the data center
		for (int i = 0; i < center.length; i++) {
			if (center[i] > highest1) {
				highest2 = highest1;
				highestIndex2 = highestIndex1;
				highest1 = center[i];
				highestIndex1 = i;
			} else if (center[i] > highest2) {
				highest2 = center[i];
				highestIndex2 = i;
			}
		}
		return metrics[highestIndex1] + " and " + metrics[highestIndex2];
	}
	
	/**
	 * This method uses nested for loops to search for the data center with the highest value for a specified metric
	 * @param dataCenters - a 2D array of doubles, the data center metrics
	 * @param metrics - an array of strings, the metric names
	 * @param metric - a string, the metric name
	 * @return an int, the data center number with the highest value for that metric
	 */
	public static int searchHighestDataCenter(double[][] dataCenters, String[] metrics, String metric) {
		double highest = 0.0;
		int index = 0;
		
		// Find the data center with the highest value for the specified metric
		for (int i = 0; i < dataCenters.length; i++) {
			for (int j = 0; j < dataCenters[0].length; j++) {
				if (metrics[j].equals(metric) && dataCenters[i][j] > highest) {
					highest = dataCenters[i][j];
					index = i;
				}
			}
		}
		return index + 1;
	}
	
	/**
	 * This method is the main method that calls the other methods and prints the results
	 * @param args - two strings, the metric names (seperated by commas) the data center metrics (centers metrics separated by <>, metrics separated by commas)
	 */
	public static void main(String[] args) {
		String metricNames = args[0];
		String[] operatingMetrics = getOperatingMetrics(metricNames);
		
		String dataCenterMetrics = args[1];
		double[][] dataCenters = getDataCenters(dataCenterMetrics);
		
		int[] annualCost = OperatingCosts.findAnnualOperationCost(dataCenters);
		System.out.println("Data Centers under the annual cost limit are: " + Arrays.toString(annualCost));
		
		String highestMetric = OperatingCosts.searchHighestMetric(dataCenters, operatingMetrics, 1);
		System.out.println("The two highest value operating cost metrics for the Data Center: " + highestMetric);
		
		int highestCenter = OperatingCosts.searchHighestDataCenter(dataCenters, operatingMetrics, "power");
		System.out.println("The data center with the highest value is: " + highestCenter);
	}
}


