package GuglioClassArrays;
// Guglio 2024/04/02

import java.util.Scanner;

public class AverageOfValues {
    public static void main(String[] args) {
        Scanner keyedInput = new Scanner (System.in); // Create a Scanner object
        
        double [ ] marks = {34.7, 54.1, 34.8, 99.6, 43.6, 43.2}; // Array (holding doubles) holding 6 marks
        double total=0; // used for the sum of the marks
        double average; // used for the average of the marks
        
        // Output all the marks to the user
        System.out.println("These are the marks:");
        for (int i = 0; i<=5; i= i + 1)
        {
           System.out.println(marks[i]);
        }
        
        // Calculate the total of the marks
        for (int i = 0; i<=5; i= i + 1)
        {
            total = total + marks[i];
        } 
        
        average = total/6; // Average out the total
        
        // Convert average to one decimal place
        average = average * 10;
        average = Math.round(average);
        average = average/10;
        
        // Output the average to the user
        System.out.println("The average mark is:");
        System.out.println(average);
    }
}