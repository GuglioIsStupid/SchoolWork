package mathproblems;

import java.util.Scanner;

public class CircleProblem {
    public static void main(String[] args) {
        Scanner UserInput = new Scanner(System.in);
        
        double CircleRadius;
        double CircleCircumference, CircleArea;
        
        System.out.println("Please enter the circles radius: ");
        try {
            CircleRadius = UserInput.nextDouble();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
            UserInput.close();
            return;
        }

        CircleCircumference = 2 * Math.PI * CircleRadius;
        CircleArea = Math.PI * Math.pow(CircleRadius, 2);

        System.out.println("The circumference of the circle is: " + CircleCircumference + "\n" +
                            "The area of the circle is: " + CircleArea);
        UserInput.close();
    }
}
