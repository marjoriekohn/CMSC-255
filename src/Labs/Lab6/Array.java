/**
 * Array.java
 * <p>
 * This class demonstrates the use of arrays in Java.
 *
 * @author Maggie Kohn
 * @version 2024.10.25
 * CMSC255.002
 * <p>
 * Q&A Section
 * Q1. What is displayed for the last two array elements? [6] = null, [7] = null
 * Q2. Why is it that value? There are only 6 names in the input string so the last two index locations are empty.
 * Q3. Is there a way to simplify this method so that you do not need to return an array? Yes.
 * Q4. Are all instances of "Rick" removed correctly from the array? Yes.
 * Q5. How did you adjust your algorithm to not have a remaining "Rick"? I replaced my original if statement that checks the value of the element with a while loop so that it replaces consecutive "Rick"s.
 */

package Labs.Lab6;

public class Array {
	
	/** displayNames() uses a for each loop to display each name in the array on its own line.
	 * @param names an array of names
	 */
	public static void displayNames(String[] names){
		for (String name : names) {
			System.out.println(name);
		}
		System.out.println();
	}
	
	/** reverseNames() uses a for loop to reverse the order of the array.
	 * @param names an array of names
	 * @return a new array, names array reversed
	 */
	public static String[] reverseNames(String[] names) {
		String[] reversedNames = new String[8];
		for (int i = names.length - 1, j = 0; i >= 0; i--, j++) {
			reversedNames[j] = names[i];
		}
		return reversedNames;
	}
	
	/** reverseNames2() uses a for loop to reverse the order of the array.
	 * @param names an array of names
	 */
	public static void reverseNames2(String[] names) {
		for (int i = 0, j = names.length - 1; i < names.length / 2 ; i++, j--) {
			String tempName = names[j];
			names[j] = names[i];
			names[i] = tempName;
		}
	}
	
	/** main() calls methods to update and display the customer array
	 * @param args an array of customer names
	 */
	public static void main(String[] args) {
		
		// 2B: saving the arguments into an array of strings
		String[] tokens = args[0].split(",");
		
		// 2C: initializing an array for the customer names
		String [] customerNames = new String[8];
		
		// 2D: the for loop copies the values from tokens to customerNames
		for(int i = 0; i < tokens.length; i++){
			customerNames[i] = tokens[i];
		}
		
		// 2E. display the customer array (1)
		displayNames(customerNames);
		
		// 2F. moving and adding values
		customerNames[6] = customerNames[4];
		customerNames[7] = customerNames[5];
		customerNames[4] = "Rick";
		customerNames[5] = "Jessica";
		
		// 2G. display the updated customer array (2)
		displayNames(customerNames);
		
		// 2I. display the reversed customer array (3)
		displayNames(reverseNames(customerNames));
		
		// 2J. call the new and improved reverseNames2() method
		reverseNames2(customerNames);
		
		// 2K. a for loop that removes "Rick" and shifts remaining elements up
		for (int i = 0; i < customerNames.length - 1; i++) {
			if (customerNames[i] != null) {
				while (customerNames[i].equals("Rick")) {
					// shift elements up
					for (int j = i; j < customerNames.length - 1; j++) {
						customerNames[j] = customerNames[j + 1];
					}
					// fill in the last element with value of null
					customerNames[customerNames.length - 1] = null;
				}
			}
		}
		
		// 2L. display the updated customer array (4)
		displayNames(customerNames);
	}
}