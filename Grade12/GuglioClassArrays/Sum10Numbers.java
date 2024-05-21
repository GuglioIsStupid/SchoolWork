package GuglioClassArrays;
// Guglio 2024/04/02

import java.util.Scanner;

public class Sum10Numbers {
    public static void main(String[] args) {
        Scanner keyedInput = new Scanner (System.in); // Create a Scanner object
        
        int [ ] numbers = new int [10]; // Create an array of integers
        int total = 0; // Set total to 0, this will be used to store the sum of the numbers
        
        System.out.println("Enter ten integers and they will be added together:"); // Prompt the user to enter 10 integers
        for (int i = 0; i <= 9; i = i + 1) // Loop through the array and store the user's input in the array
        {
           numbers[i] = keyedInput.nextInt();
        }
        

        for (int i = 0; i <= 9; i = i + 1) // Loop through the array and add the numbers together
        {
            total = total + numbers[i]; 
        } // (This can be added to the previous loop, but seperated for readability)
        
        // Output the sum of the numbers
        System.out.println("The sum of those numbers is:");
        System.out.println(total);
    }
}