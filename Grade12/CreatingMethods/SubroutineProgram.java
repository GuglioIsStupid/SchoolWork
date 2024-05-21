package CreatingMethods;

// Guglio - 2024/04/16

import java.util.Scanner;

public class SubroutineProgram {
    public static void MainMenuText() { // Method to display the main menu
        System.out.println("1. Calculate the perimeter of a rectangle");
        System.out.println("2. Calculate the area of a rectangle");
        System.out.println("3. Calculate the volume of a rectangle");
        System.out.println("4. Calculate the circumference of a circle");
        System.out.println("5. Calculate the area of a circle");
        System.out.println("6. Exit");
    }

    // All of our calculation methods
    public static double CalculatePerimeterOfRectangle(double length, double width) {
        return 2 * (length + width);
    }

    public static double CalculateAreaOfRectangle(double length, double width) {
        return length * width;
    }

    public static double CalculateVolumeOfRectangle(double length, double width, double height) {
        return length * width * height;
    }

    public static double CalculateCircumferenceOfCircle(double radius) {
        return 2 * Math.PI * radius;
    }

    public static double CalculateAreaOfCircle(double radius) {
        return Math.PI * Math.pow(radius, 2);
    }

    // Main method
    public static void main(String[] args) {
        int choice;
        double length, width, height, radius;

        Scanner userInput = new Scanner(System.in);

        do {
            MainMenuText();
            choice = userInput.nextInt();

            switch (choice) { // Switch statement to handle the user's choice
                case 1:
                    System.out.println("Enter the length of the rectangle: ");
                    length = userInput.nextDouble();
                    System.out.println("Enter the width of the rectangle: ");
                    width = userInput.nextDouble();
                    System.out.println("The perimeter of the rectangle is: " + CalculatePerimeterOfRectangle(length, width));
                    break;
                case 2:
                    System.out.println("Enter the length of the rectangle: ");
                    length = userInput.nextDouble();
                    System.out.println("Enter the width of the rectangle: ");
                    width = userInput.nextDouble();
                    System.out.println("The area of the rectangle is: " + CalculateAreaOfRectangle(length, width));
                    break;
                case 3:
                    System.out.println("Enter the length of the rectangle: ");
                    length = userInput.nextDouble();
                    System.out.println("Enter the width of the rectangle: ");
                    width = userInput.nextDouble();
                    System.out.println("Enter the height of the rectangle: ");
                    height = userInput.nextDouble();
                    System.out.println("The volume of the rectangle is: " + CalculateVolumeOfRectangle(length, width, height));
                    break;
                case 4:
                    System.out.println("Enter the radius of the circle: ");
                    radius = userInput.nextDouble();
                    System.out.println("The circumference of the circle is: " + CalculateCircumferenceOfCircle(radius));
                    break;
                case 5:
                    System.out.println("Enter the radius of the circle: ");
                    radius = userInput.nextDouble();
                    System.out.println("The area of the circle is: " + CalculateAreaOfCircle(radius));
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6); // while do loop to keep the program running until the user chooses to exit

        userInput.close();
    }
}
