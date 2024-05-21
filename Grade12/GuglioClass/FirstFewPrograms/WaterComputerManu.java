package GuglioClass.FirstFewPrograms;

import java.util.Scanner;

public class WaterComputerManu {
    public static void main(String[] args) {
        Scanner UserInput = new Scanner(System.in);
        
        final double WATER_AMOUNT = 1.5; // 1.5 tons to make a computer and monitor combo.
        
        // int for the amount of computers and monitors the user has (one var)
        int Computers;
        double TotalWaterUsed;

        System.out.println("Please enter the amount of computers and monitors you own: ");
        try {
            Computers = UserInput.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid interger.");
            UserInput.close();
            return;
        }

        TotalWaterUsed = Computers * WATER_AMOUNT;

        System.out.println("The total amount of water used to make " + Computers + " computers and monitors is: " + TotalWaterUsed + " tons.");

        UserInput.close(); // Close the scanner because we are done with it.
    }
}