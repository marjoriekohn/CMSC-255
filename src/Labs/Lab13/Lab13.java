/**
 * Lab 13: Abstract Classes and Interfaces
 *
 *  @author Maggie Kohn
 *  @version 2024.11.13
 *  CMSC255.002
 */

package Labs.Lab13;

import java.util.ArrayList;
import java.util.Collections;

public class Lab13 {
	public static void main(String[] args) {
		ArrayList<Product> products = new ArrayList<>();
		
		products.add(new PackagedGood("Canned Sweet Corn", 4, 24, 10, 0.75));
		products.add(new PackagedGood("Eddy's Ice Cream", 4.8, 276, 5, 3.99));
		products.add(new Produce("Grape Tomatoes", 3.7, 458, 3.2, 0.89));
		products.add(new Produce("Bananas", 4.1, 167, 1.4, 0.75));
		products.add(new Appliance("LG Smart Washer", 4.2, 37, 599.65, 2));
		
		System.out.println("Shopping Cart Contents:");
		for (Product product : products) {
			System.out.println("\t" + product);
		}
		
		Collections.sort(products);
		
		System.out.println("Sorted Cart Contents:");
		for (Product product : products) {
			System.out.println("\t" + product);
		}
		
		double totalPrice = 0;
		for (Product item : products) {
			totalPrice += item.getPrice();
		}
		
		System.out.printf("Shopping Cart Total: $%.2f\n", totalPrice);
	}
}
