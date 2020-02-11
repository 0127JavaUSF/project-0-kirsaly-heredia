package com.revature.util;

import java.util.Scanner;

//Utility class used for getting user input.
 

public class InputUtil {
	
	//created a scanner object
	private static Scanner scanner = new Scanner(System.in);
	
	
	//Prompts user to input number in range min to max inclusive of both.
	public static int getIntInRange(int min, int max) {
		int option = 0;
		outer: do {
			System.out.println("\nPlease enter a number between " + min +
				" and " + max + ".");
			
			while(!scanner.hasNextInt()) {
				scanner.nextLine();
				continue outer;
			}
			option = scanner.nextInt();
			scanner.nextLine();
		} while(option < min || option > max);
		
		return option;
	}
	
	
	public static String getNextString() {
		return scanner.nextLine();
	}
	
	public static int getNextInt() {
		return scanner.nextInt();
	}
	
}
