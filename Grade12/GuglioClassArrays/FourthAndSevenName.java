package GuglioClassArrays;
// Guglio 2024/04/02

import java.util.Scanner;

public class FourthAndSevenName {
    public static void main(String[] args) {
        Scanner keyedInput = new Scanner (System.in); // Create a Scanner object
        
        String [ ] friends = new String [10]; // Create an array of strings
        
        System.out.println("Enter the names of ten friends:"); // Prompt the user to enter 10 names
        for (int i = 0; i <= 9; i = i + 1)
        {
            friends[i] = keyedInput.nextLine();
        }
        
        // Output the fourth and seventh names listed
        System.out.println("The fourth and seventh names listed were:"); 
        System.out.println("Fourth: " + friends[3]);
        System.out.println("Seventh: " + friends[6]);  
    }
}