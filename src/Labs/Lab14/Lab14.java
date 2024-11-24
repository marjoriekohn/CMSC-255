/**
 * Lab 14: Recursion :( this one made me cry a little (a lot)
 *
 *  @author Maggie Kohn
 *  @version 2024.11.21
 *  CMSC255.002
 */

package Labs.Lab14;

import java.util.ArrayList;

public class Lab14 {
	public static void main(String[] args) {
		ArrayList<Integer> mySequence = new ArrayList<>();
		
		System.out.println("Pattern 1: " + pattern1(10, mySequence));
		mySequence.clear();
		
		System.out.println("Pattern 2: " + pattern2(10, mySequence));
		mySequence.clear();
		
		System.out.println("Pattern 3: " + pattern3(11, mySequence));
		mySequence.clear();
		
		System.out.println("Pattern 4: " + pattern4(10, mySequence));
		mySequence.clear();
	}
	
	/**
	 * Pattern 1: f(n - 1) + 2(n - 1) - 1
	 * Base case: f(1) = 1
	 *
	 * @param term     how many terms to calculate
	 * @param sequence An empty ArrayList that will be populated with all the previous terms
	 * @return the value of the nth term in the sequence
	 */
	public static int pattern1(int term, ArrayList<Integer> sequence) {
		// base case: when term is 1, add 1 to the list, return 1
		if (term == 1) {
			sequence.add(1);
			return 1;
		}
		
		// recursive call to get the previous value
		int previousValue = pattern1(term - 1, sequence);
		
		// calculate the value using the formula
		int currentValue = previousValue + 2 * (term - 1) - 1;
		
		// add the current value to the sequence and return it
		sequence.add(currentValue);
		return currentValue;
	}
	
	/**
	 * Pattern 2: f(n-1)-2 if n is odd; 3f(n-1) if n is even
	 * Base case: f(1) = 4
	 *
	 * @param term     how many terms to calculate
	 * @param sequence An empty ArrayList that will be populated with all the previous terms
	 * @return the value of the nth term in the sequence
	 */
	public static int pattern2(int term, ArrayList<Integer> sequence) {
		// base case: when term is 1, add 4 to the list, return 4
		if (term == 1) {
			sequence.add(4);
			return 4;
		}
		
		// recursive call to get the previous value
		int previousValue = pattern2(term - 1, sequence);
		
		// calculate the value using the formula:
		// if term is even, multiply the previous value by 3
		// if term is odd,  subtract the previous value by 2
		int currentValue = 0;
		if (term % 2 == 0) {
			currentValue = 3 * previousValue;
		} else {
			currentValue = previousValue - 2;
		}
		
		// add the value to the sequence and return it
		sequence.add(currentValue);
		return currentValue;
	}
	
	/**
	 * Pattern 3: f(n-1)-1 if n is odd; 2f(n-1) if n is even
	 * Base case: f(1) = 3
	 *
	 * @param term     how many terms to calculate
	 * @param sequence An empty ArrayList that will be populated with all the previous terms
	 * @return the value of the nth term in the sequence
	 */
	public static int pattern3(int term, ArrayList<Integer> sequence) {
		// base case: when term is 1, add 3 to the list, return 3
		if (term == 1) {
			sequence.add(3);
			return 3;
		}
		
		// recursive call to get the previous value
		int previousValue = pattern3(term - 1, sequence);
		
		// calculate the value using the formula:
		// if term is even, multiply the previous value by 2
		// if term is odd,  subtract the previous value by 1
		int currentValue = 0;
		if (term % 2 == 0) {
			currentValue = 2 * previousValue;
		} else {
			currentValue = previousValue - 1;
		}
		
		// add the value to the sequence and return it
		sequence.add(currentValue);
		return currentValue;
	}
	
	/**
	 * Pattern 4: 2f(n -1) - f(n - 2) + n + 1
	 * Base case: f(1) = 1 and f(2) = 5
	 *
	 * @param term how many terms to calculate
	 * @param sequence An empty ArrayList that will be populated with all the previous terms
	 * @return the value of the nth term in the sequence
	 */
	public static int pattern4(int term, ArrayList<Integer> sequence) {
		// base case: when sequence is empty, add 1 to the list
		// base case: when sequence has only 1 element, add 5 to the list
		if (sequence.isEmpty()) {
			sequence.add(1);
		}
		if (sequence.size() < 2) {
			sequence.add(5);
		}
		// stop condition, if term is less than or equal to the size of the sequence return the value
		if (term <= sequence.size()) {
			return sequence.get(term - 1);
		}
		
		// recursive call to get the previous values
		int previousValue1 = pattern4(term - 1, sequence);
		int previousValue2 = pattern4(term - 2, sequence);
		
		// calculate the value using the formula
		int currentValue = 2 * previousValue1 - previousValue2 + term + 1;
		
		// add the value to the sequence and return it
		sequence.add(currentValue);
		return currentValue;
	}
}
