package Labs.Lab7;

import java.util.Arrays;

public class Lab7 {
	
	public static void main(String[] args) {
		int [][] addTo10Input = {
			{  6,  3,  2,  0,  4},
			{ 34, 53,  0, 23,  1},
			{  0, 23, 54, 11,  7}
		};
		
		double [][] findAvgInput = {
			{  5, 4.5,  6.8},
			{  6,  0,  3.4},
			{ 7,  8.4,  2.3}
		};
		
		double [][] findAvgInputLarge = {
			{ 6.7,  23.8,  0,  5.9,  12.8,  45.7},
			{  0,  36.8,  13.5,  6.7,  54.9,  67.4},
			{  37.4,  2.5,  39.8,  0,  2.4,  43.6},
			{  44.6,  76.5,  4.5, 2.4,  0, 54.6},
			{  5.4,  76.3,  6.5, 28.5,  54.7,  0},
			{  19.4,  0,  5.3,  65.4,  93.5,  3.5}
		};
		
		addTo10 (addTo10Input);
		System.out.println();
		findAverage (findAvgInput);
		System.out.println();
		findAverage (findAvgInputLarge);
	}

	/**
	 * Takes a 2D array of integers and modifies it so that the sum of all numbers in the row is 10.
	 *
	 * @param array 2D array of integers
	 */
	public static void addTo10 (int [][] array) {
		// Iterate through the rows of the 2D array using an enhanced for loop.
		for (int[] row : array){
			int indexOfZero = 0;
			int sumOfRow = 0;
			
			// Iterate through the columns of the 2D array using a for loop.
			for (int i = 0; i < row.length; i++){
				// Find the sum of the row by adding each element in the row.
				sumOfRow += row[i];
				// If the value of the element is zero, store the index of the column.
				if (row[i] == 0){
					indexOfZero = i;
				}
			}
			// Within the first for loop, utilize the column index to replace the zero with (10 - sumOfRow).
			row[indexOfZero] = 10 - sumOfRow;
		}
	}
	
	/**
	 * Takes a 2D array of doubles, for any 0 found, it's replaced with either the row or column average at that point in the matrix, whichever is larger.
	 *
	 * @param array 2D array of doubles
	 */
	public static void findAverage (double [][] array) {
		
		// Iterate through the 2D array using nested for loops.
		for(int row = 0; row < array.length; row++) {
			for (int column = 0; column < array[0].length; column++) {
				
				// If the current value is 0,
				if (array[row][column] == 0) {
					double sumOfColumn = 0;
					double sumOfRow = 0;
					
					// Calculate the sum of the row using an enhanced for loop.
					for (double value : array[row]) {
						sumOfRow += value;
					}
					
					// Calculate the sum of the column by iterating over the rows.
					for (double[] value : array) {
						sumOfColumn += value[column];
					}
					
					// Calculate the average of the row and column.
					double rowAverage = sumOfRow / array[row].length;
					double columnAverage = sumOfColumn / array.length;
					
					// Replace the 0 in the array with the larger of the two averages.
					array[row][column] = Math.max(columnAverage, rowAverage);
				}
			}
		}
	}
}
