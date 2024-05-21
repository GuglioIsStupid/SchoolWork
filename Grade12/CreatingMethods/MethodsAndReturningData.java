package CreatingMethods;

// Guglio - 2024/04/16

import java.util.Scanner;

public class MethodsAndReturningData {
    public static void MainMenuText() { // Method to display the main menu
        System.out.println("1. Calculate the perimeter of a rectangle");
        System.out.println("2. Calculate the area of a rectangle");
        System.out.println("3. Calculate the volume of a rectangle");
        System.out.println("4. Calculate the circumference of a circle");
        System.out.println("5. Calculate the area of a circle");
        System.out.println("6. Calculate the volume of a sphere");
        System.out.println("7. Calculate the volume of a cylinder");
        System.out.println("8. Calculate the volume of a cone");
        System.out.println("9. Calculate the volume of a pyramid");
        System.out.println("10. Find the distance between two points");
        System.out.println("11. Exit");
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

    public static double CalculateVolumeOfSphere(double radius) {
        return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }

    public static double CalculateVolumeOfCylinder(double radius, double height) {
        return Math.PI * Math.pow(radius, 2) * height;
    }

    public static double CalculateVolumeOfCone(double radius, double height) {
        return (1.0 / 3.0) * Math.PI * Math.pow(radius, 2) * height;
    }

    public static double CalculateVolumeOfPyramid(double baseLength, double baseWidth, double height) {
        return (1.0 / 3.0) * baseLength * baseWidth * height;
    }

    public static double CalculateDistanceBetweenTwoPoints(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    // Main method
    public static void main(String[] args) {
        int choice;
        double length, width, height, radius, baseLength, baseWidth;

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
                    System.out.println("Enter the radius of the sphere: ");
                    radius = userInput.nextDouble();
                    System.out.println("The volume of the sphere is: " + CalculateVolumeOfSphere(radius));
                    break;
                case 7:
                    System.out.println("Enter the radius of the cylinder: ");
                    radius = userInput.nextDouble();
                    System.out.println("Enter the height of the cylinder: ");
                    height = userInput.nextDouble();
                    System.out.println("The volume of the cylinder is: " + CalculateVolumeOfCylinder(radius, height));
                    break;
                case 8:
                    System.out.println("Enter the radius of the cone: ");
                    radius = userInput.nextDouble();
                    System.out.println("Enter the height of the cone: ");
                    height = userInput.nextDouble();
                    System.out.println("The volume of the cone is: " + CalculateVolumeOfCone(radius, height));
                    break;
                case 9:
                    System.out.println("Enter the length of the base of the pyramid: ");
                    baseLength = userInput.nextDouble();
                    System.out.println("Enter the width of the base of the pyramid: ");
                    baseWidth = userInput.nextDouble();
                    System.out.println("Enter the height of the pyramid: ");
                    height = userInput.nextDouble();
                    System.out.println("The volume of the pyramid is: " + CalculateVolumeOfPyramid(baseLength, baseWidth, height));
                    break;
                case 10:
                    double x1, y1, x2, y2;
                    System.out.println("Enter the x-coordinate of the first point: ");
                    x1 = userInput.nextDouble();
                    System.out.println("Enter the y-coordinate of the first point: ");
                    y1 = userInput.nextDouble();
                    System.out.println("Enter the x-coordinate of the second point: ");
                    x2 = userInput.nextDouble();
                    System.out.println("Enter the y-coordinate of the second point: ");
                    y2 = userInput.nextDouble();
                    System.out.println("The distance between the two points is: " + CalculateDistanceBetweenTwoPoints(x1, y1, x2, y2));
                    break;
                case 11:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;  
                    
            }
        } while (choice != 11);
    }
}
