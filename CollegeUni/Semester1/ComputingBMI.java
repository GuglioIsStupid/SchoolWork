// GuglioIsStupid // 2024/09/19

/*
Psuedocode:
Start
    Output : Please input the weight in pounds
    Input : weightInPounds

    Output : Please input the height in inches
    Input : heightInMeters

    Define _weightInKilograms : weightInPounds * 0.45359237
    Define _heightInMeters : heightInMeters * 0.0254

    Define bmi : _weightInKilograms / _heightInMeters^2
    Output : BMI is {_bmi}
*/

import java.util.Scanner;

public class ComputingBMI {
    public static void main(String[] args) {
        // Declare the variables we will be using
        double weightInPounds, heightInInches, weightInKilograms, heightInMeters, calculatedBmi = 0;
        final double POUNDS_TO_KILOGRAMS = 0.45359237;
        final double INCHES_TO_METERS = 0.0254;

        Scanner input = new Scanner(System.in);
        
        // Prompt user for inputs
        System.out.println("Enter the weight in pounds: ");
        weightInPounds = input.nextDouble();

        System.out.println("Enter the height in inches: ");
        heightInInches = input.nextDouble();
        
        weightInKilograms = weightInPounds * POUNDS_TO_KILOGRAMS;
        heightInMeters = heightInInches * INCHES_TO_METERS;
        
        calculatedBmi = weightInKilograms / Math.pow(heightInMeters, 2);
        
        // Display the result to the user
        System.out.println("BMI is " + calculatedBmi);

        input.close();
    }
}