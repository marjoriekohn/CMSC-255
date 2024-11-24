/**
 *
 * Lab5.java
 *
 * A program that demonstrates the use of methods in Java.
 *
 * @author Maggie Kohn
 * @version 2024.10.17
 * CMSC255.002
 * */

package Labs.Lab5;

import java.util.Scanner;

public class Lab5{
	
	public static void main(String[] args) {
		// variable declarations for part 1
		String title;
		String firstName;
		String lastName;
		Scanner in = new Scanner(System.in);
		
		// prompt for input for part 1
		System.out.print("Enter a title:");
		title = in.next();
		System.out.print("Enter your first name:");
		firstName = in.next();
		System.out.print("Enter a your last name:");
		lastName = in.next();
		
		// call the method for part 1
		greeting(title, firstName, lastName);
		
		// variable declarations for part 2
		int number1;
		int number2;
		
		// user prompts for part 2
		System.out.print("Enter first number:");
		number1 = in.nextInt();
		System.out.print("Enter second number:");
		number2 = in.nextInt();
		
		// call the methods max and sumTo inside the println statement
		System.out.println("The largest number is " + max(number1, number2));
		System.out.println("The sum of the numbers is " + sumTo(number1, number2));
	}
	
	/******************** greeting method goes here*********************/
	public static void greeting(String title, String firstName, String lastName) {
		System.out.println();
		System.out.println("Dear " + title + " " + firstName + " " + lastName + ",");
		System.out.println();
	}
	/***********************end of method*************************/
	
	/******************** max method goes here*********************/
	public static int max(int number1, int number2) {
		return Math.max(number1, number2);
	}
	/***********************end of method*************************/
	
	/******************** sumTo method goes here*********************/
	public static int sumTo(int number1, int number2) {
		int sum = 0;
		int max = max(number1, number2);
		int min = (max == number1) ? number2 : number1;
		for (int i = min; i <= max; i++) {
			sum += i;
		}
		return sum;
	}
	/***********************end of method*************************/
}
