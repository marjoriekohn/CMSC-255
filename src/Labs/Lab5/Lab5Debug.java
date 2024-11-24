/**
 *
 * Lab5Debug.java
 *
 * A program that demonstrates the use of debugging methods in Java.
 *
 * @author Maggie Kohn
 * @version 2024.10.17
 * CMSC255.002
 * */

//package Labs.Lab5;
//
import java.util.Scanner;

public class Lab5Debug {
	
	public static void main(String[] args){
		// Create a Scanner
		Scanner input = new Scanner(System.in);
		
		// Prompt the user to enter two integers
		System.out.print("Enter first integer: ");
		int n1 = input.nextInt();
		System.out.print("Enter second integer: ");
		int n2 = input.nextInt();
		
		int gcd = GCD(n1,n2);
		
		System.out.println("The greatest common divisor for " + n1 + " and " + n2 + " is " + gcd);
	}
	
	public static int GCD(int num1, int num2){
		
		int gcd = 1;
		int k = 2;
		while (k <= num1 && k <= num2) {
			if (num1 % k == 0 && num2 % k == 0) {
				gcd = k;
			}
			k++;
		}
		
		return gcd;
	}
}