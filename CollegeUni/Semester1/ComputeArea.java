// GuglioIsStupid // 2024/09/19

/*
Psuedocode:
Start
    Output : Please input the radius of the circle
    Input : _radius

    Define Area : PI * _radius^2
    Output : The area of the circle is {Area}
End
 */

import java.util.Scanner;

public class ComputeArea {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // Prompt user for inputs
        System.out.println("Enter the radius: ");
        double _radius = input.nextDouble();
        
        double _area = Math.PI * _radius * _radius;
        
        // Display the result to the user
        System.out.println("The area is " + _area); 
        
        input.close();
    }
}