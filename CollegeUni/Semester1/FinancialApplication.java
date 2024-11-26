// GuglioIsStupid // 2024/09/19

/*
Psuedocode:
Start
    Output : Please input the subtotal
    Input : _subtotal

    Output : Please input the gratuity rate
    Input : _gratuityRate

    Define _gratuity : _subtotal * (_gratuityRate / 100)
    Define _total : _subtotal + _gratuity

    Output : The gratuity is ${_gratuity} and the total is ${_total}
 */

import java.util.Scanner;

public class FinancialApplication {
    public static void main(String[] args) {
        double subtotal, gratuityRate, gratuity, total = 0;
        Scanner input = new Scanner(System.in);
        
        // Prompt user for inputs
        System.out.println("Enter the subtotal: ");
        subtotal = input.nextDouble();

        System.out.println("Enter the gratuity rate: ");
        gratuityRate = input.nextDouble();
        
        gratuity = subtotal * (gratuityRate / 100);
        total = subtotal + gratuity;
        
        // Display the result to the user
        System.out.println("The gratuity is $" + gratuity + " and the total is $" + total);

        input.close();
    }
}