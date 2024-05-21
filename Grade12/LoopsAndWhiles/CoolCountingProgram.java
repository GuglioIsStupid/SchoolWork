package LoopsAndWhiles;

// Guglio - 2024/03/25
// Cool counting program

import java.util.Scanner;

public class CoolCountingProgram {
	public static void main(String[] args) {
		Scanner UserInput = new Scanner(System.in);
		
		int MenuChoice = 0;
		
		// Create our little menu so the user knows which option does what
		System.out.println("---Cool Counting Program---\n");
		
		System.out.println("Please enter your choice:");
		System.out.println("1: Count from 0 to 10 by 1.");
		System.out.println("2: Count from 100 to 0 by 10.");
		System.out.println("3: Count from 50 to 500 by 50");
		System.out.println("4: Count from 6000 to 1000 by 1000");
		
		System.out.println("Please enter your choice:");
		
		try { // Try to get the user's input. Only accept integers.
			MenuChoice = UserInput.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid interger.");
            UserInput.close();
            return;
        }
		
		System.out.println("\n---------------------------\n"); // End of menu
		
		switch(MenuChoice) {
			case 1: // User picked 1: Count from 0 to 10 by 1
				for (int i = 0; i <= 10; i++) {
					System.out.println(i);
				}
				break;
			case 2: // User picked 2: Count from 100 to 0 by 10
				for (int i = 100; i >= 0; i -= 10) {
					System.out.println(i);
				}
				break;
			case 3: // User picked 3: Count from 50 to 500 by 50
				for (int i = 50; i <= 500; i += 50) {
					System.out.println(i);
				}
				break;
			case 4: // User picked 4: Count from 6000 to 1000 by 1000
				for (int i = 6000; i >= 1000; i -= 1000) {
					System.out.println(i);
				}
				break;
			default: // User picked an invalid choice
				System.out.println("Invalid choice. Please enter a valid choice.");
				break;
		}
		
		UserInput.close(); // Close our scanner to free up resources
	}
}
