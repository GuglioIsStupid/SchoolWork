package GuglioClassArrays;

//Create a program that will create an array of 10 random values between 1 and 100, display it to the user and tell the user the max and min values.
/* HAS TO HAVE:
variables
at least one constant (final static)
loops
arrays */

public class RandomNumbers {
    public static void main(String[] args) {
        final int ARRAY_SIZE = 10; // Constant for the size of the array
        
        int [ ] randomNumbers = new int [ARRAY_SIZE]; // Create an array of integers
        
        int max = 0; // Set max to 0, this will be used to store the maximum value
        int min = 100; // Set min to 100, this will be used to store the minimum value

        // Generate random numbers between 1 and 100 and store them in the array
        for (int i = 0; i < ARRAY_SIZE; i = i + 1)
        {
            randomNumbers[i] = (int)(Math.random() * 100 + 1);
        }

        // Find the maximum and minimum values
        for (int i = 0; i < ARRAY_SIZE; i = i + 1)
        {
            if (randomNumbers[i] > max)
            {
                max = randomNumbers[i];
            }
            if (randomNumbers[i] < min)
            {
                min = randomNumbers[i];
            }
        }

        // Output the random numbers to the user
        System.out.println("The random numbers are:");
        for (int i = 0; i < ARRAY_SIZE; i = i + 1)
        {
            System.out.println(randomNumbers[i]);
        }

        // Output the maximum and minimum values to the user
        System.out.println("The maximum value is:");
        System.out.println(max);
        System.out.println("The minimum value is:");
        System.out.println(min);
    }
}