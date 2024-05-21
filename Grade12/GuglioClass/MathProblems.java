package GuglioClass;

import java.util.Scanner;

public class MathProblems {
	public static void main(String[] args) {
		Scanner UserInput = new Scanner(System.in);
		
		int RectangleWidth, RectangleHeight;
		int RectanglePerimeter, RectangleArea;
		
		System.out.println("Please enter the rectangles width: ");
		try {
			RectangleWidth = UserInput.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid input. Please enter a valid interger.");
			UserInput.close();
			return;
		}

		System.out.println("Please enter the rectangles height: ");
		try {
			RectangleHeight = UserInput.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid input. Please enter a valid interger.");
			UserInput.close();
			return;
		}
		
		RectanglePerimeter = (RectangleWidth * 2) + (RectangleHeight * 2);
		RectangleArea = RectangleWidth * RectangleHeight;

		System.out.println("The perimeter of the rectangle is: " + RectanglePerimeter + "\n" +
							"The area of the rectangle is: " + RectangleArea);
		UserInput.close(); // Close the scanner because we are done with it.
	}
}
