/**
 * Project 5 - Data Centers Cost Analysis
 * <p>
 * This program models a data center and calculates the mean, highest, and lowest values of construction cost, IT load, and operating cost.
 * <hr>
 * @author Maggie Kohn
 * @version 11.08.2024
 * CMSC255.002
 */
package Projects.Project5;

import java.io.*;
import java.util.*;

/**
 * Gets the data from the input file and creates objects from the data.
 */
public class DataCenterCostAnalysis {
	/**
	 * Calls the methods to read the data from the input file, create objects from the data, and write the data to the output file.
	 * @param args the input and output file names
	 */
	public static void main(String[] args) {
		File inputFile = new File(args[0]);
		File outputFile = new File(args[1]);
		ArrayList<DataCenter> dataCenters = new ArrayList<>();
		
		// try to read the data from the input file
		try {
			dataCenters = createObjects(openFile(inputFile));
			System.out.println("Input file correct");
		} catch (FileNotFoundException fileNotFound) {
			System.out.println("Incorrect input filename");
		}
		
		// calculate the mean, highest value, data center closest to the mean, and data centers below the mean
		double mean = findMean(dataCenters, DataCenterAttributes.CONSTRUCTION_COST);
		double highValue = findHighValue(dataCenters, DataCenterAttributes.IT_LOAD);
		DataCenter meanDataCenter = findMeanDataCenter(dataCenters, DataCenterAttributes.CONSTRUCTION_COST, mean);
		ArrayList<DataCenter> lowValueDataCenters = findLowestDataCenters(dataCenters, DataCenterAttributes.CONSTRUCTION_COST, mean);
		
		// try to write the data to the output file
		try (PrintWriter outWriter = new PrintWriter(outputFile)) {
			outputToFile("The average construction cost of all data centers is: ", mean, outWriter);
			outputToFile("The highest IT load of all data centers is: ", highValue, outWriter);
			outputToFile("The data center closest to the average construction cost is: ", meanDataCenter, outWriter);
			outputToFile("The data centers below the average value for construction cost are: ", lowValueDataCenters, outWriter);
			outWriter.println();
			outWriter.close();
			System.out.println("Output file correct");
		} catch (FileNotFoundException fileNotFound) {
			System.out.println("Incorrect output filename");
		}
	}
	
	/**
	 * Read the data from the input file and save each line as an ArrayList of Strings.
	 * @param inputFile the input file to read from
	 * @return the list of Strings extracted from the input file
	 * @throws FileNotFoundException if the file is not found
	 */
	public static ArrayList<String> openFile(File inputFile) throws FileNotFoundException {
		// new String array stores the data from each firm as a single string
		ArrayList<String> dataCenterStrings = new ArrayList<>();
		Scanner input = new Scanner(inputFile);
		while(input.hasNext()) {
			String line = input.nextLine().trim();
			dataCenterStrings.add(line);
		}
		return dataCenterStrings;
	}
	
	/**
	 * Parse the data from each element of the given ArrayList into an ArrayList containing DataCenter objects.
	 * @param lines the list of Strings extracted from the input file
	 * @return the list of DataCenter objects created from the data
	 * @see DataCenter
	 */
	public static ArrayList<DataCenter> createObjects(ArrayList<String> lines) {
		// new DataCenter array stores the data from each firm as a DataCenter object
		ArrayList<DataCenter> dataCenters = new ArrayList<>();
		
		for (String line : lines) {
			String[] firmData = line.split("\t");
			try {
				DataCenter dataCenter = new DataCenter(
					firmData[0],
					Double.parseDouble(firmData[1]),
					Double.parseDouble(firmData[2]),
					Double.parseDouble(firmData[3])
				);
				dataCenters.add(dataCenter);
			} catch (NumberFormatException notNumeric) {
				double[] validData = validateData(firmData);
				DataCenter dataCenter = new DataCenter(firmData[0], validData[0], validData[1], validData[2]);
				dataCenters.add(dataCenter);
			}
		}
		return dataCenters;
	}
	
	/**
	 * Helper method to validate the double values from the input file.
	 * @param firmData the data from a single firm
	 * @return the validated data as an array of doubles
	 */
	public static double[] validateData(String[] firmData) {
		double[] validData = new double[3];
		for (int i = 1; i <= validData.length; i++) {
			try {
				validData[i - 1] = Double.parseDouble(firmData[i]);
				if (validData[i - 1] < 0) {
					throw new NumberFormatException();
				}
			}
			catch (NumberFormatException invalidDouble) {
				validData[i - 1] = 0.0;
			}
		}
		return validData;
	}
	
	/**
	 * Find the mean of the specified attribute for the given list of DataCenter objects.
	 * @param dataCenters the list of DataCenter objects
	 * @param attribute the attribute to find the mean of
	 * @return the mean of the specified attribute
	 */
	public static double findMean(ArrayList<DataCenter> dataCenters, DataCenterAttributes attribute) {
		double total = 0.0;
		for (DataCenter dataCenter : dataCenters) {
			total += dataCenter.getAttribute(attribute);
		}
		return total / dataCenters.size();
	}
	
	/**
	 * Find the highest value of the specified attribute for the given list of DataCenter objects.
	 * @param dataCenters the list of DataCenter objects
	 * @param attribute the attribute to find the highest value of
	 * @return the highest value of the specified attribute
	 */
	public static double findHighValue(ArrayList<DataCenter> dataCenters, DataCenterAttributes attribute){
		double highValue = Double.MIN_VALUE;
		for (DataCenter dataCenter : dataCenters) {
			if (dataCenter.getAttribute(attribute) > highValue) {
				highValue = dataCenter.getAttribute(attribute);
			}
		}
		return highValue;
	}
	
	/**
	 * Find the data center with the closest mean value of the specified attribute for the given list of DataCenter objects.
	 * @param dataCenters the list of DataCenter objects
	 * @param attribute the attribute to find the mean value of
	 * @param meanValue the mean value of the specified attribute
	 * @return the data center with the mean value of the specified attribute
	 */
	public static DataCenter findMeanDataCenter(ArrayList<DataCenter> dataCenters, DataCenterAttributes attribute, double meanValue){
		DataCenter meanDataCenter = new DataCenter();
		double minDifference = Double.MAX_VALUE;
		
		for (DataCenter dataCenter : dataCenters) {
			double currentDifference = Math.abs(dataCenter.getAttribute(attribute) - meanValue);
			if (currentDifference < minDifference) {
				minDifference = currentDifference;
				meanDataCenter = dataCenter;
			}
		}
		return meanDataCenter;
	}
	
	/**
	 * Find the lowest value of the specified attribute for the given list of DataCenter objects.
	 * @param dataCenters the list of DataCenter objects
	 * @param attribute the attribute to find the lowest value of
	 * @return an arraylist containing the data centers with the lowest value of the specified attribute
	 */
	public static ArrayList<DataCenter> findLowestDataCenters(ArrayList<DataCenter> dataCenters, DataCenterAttributes attribute, double value){
		ArrayList<DataCenter> lowValueDataCenters = new ArrayList<>(dataCenters);
		for (DataCenter dataCenter : dataCenters) {
			if (dataCenter.getAttribute(attribute) > value) {
				lowValueDataCenters.remove(dataCenter);
			}
		}
		return lowValueDataCenters;
	}
	
	/**
	 * Output the specified message and the list of DataCenter objects to the output file.
	 * @param outputMessage the message to output to the file
	 * @param dataCenters the list of DataCenter objects to output to the file
	 * @param out the PrintWriter object to write to the output file
	 */
	public static void outputToFile(String outputMessage, ArrayList<DataCenter> dataCenters, PrintWriter out){
		out.print(outputMessage);
		for (DataCenter dataCenter : dataCenters) {
			out.print(dataCenter.toString().trim());
				out.print(" ");
		}
		out.println("\n");
	}
	
	/**
	 * Output the specified message and the DataCenter object to the output file.
	 * @param outputMessage the message to output to the file
	 * @param dataCenter the DataCenter object to output to the file
	 * @param out the PrintWriter object to write to the output file
	 */
	public static void outputToFile(String outputMessage, DataCenter dataCenter, PrintWriter out){
		out.println(outputMessage + dataCenter.toString().trim());
		out.println();
	}
	
	/**
	 * Output the specified message and the double value to the output file.
	 * @param outputMessage the message to output to the file
	 * @param value the double value to output to the file
	 * @param out the PrintWriter object to write to the output file
	 */
	public static void outputToFile(String outputMessage, double value, PrintWriter out){
		out.println(outputMessage + String.format("%.2f", value).trim());
		out.println();
	}
}
