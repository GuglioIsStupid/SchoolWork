package GuglioFirstFewPrograms;

import java.util.Scanner;

public class IncomeTax {
    public static void main(String[] args) {
        Scanner UserInput = new Scanner(System.in);

        final double FIRST_TAX_RATE = 0.0505;
        final double SECOND_TAX_RATE = 0.0915;
        final double THIRD_TAX_RATE = 0.1116;
        final double FOURTH_TAX_RATE = 0.1216;
        final double FIFTH_TAX_RATE = 0.1316;

        final double FIRST_BRACKET = 51446;
        final double SECOND_BRACKET = 102894;
        final double THIRD_BRACKET = 150000;
        final double FOURTH_BRACKET = 220000;
        
        double Salary;
        double TaxOwed;

        System.out.println("Please enter your yearly salary: ");
        try {
            Salary = UserInput.nextDouble();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
            UserInput.close();
            return;
        }

        if (Salary <= FIRST_BRACKET) {
            TaxOwed = Salary * FIRST_TAX_RATE;
        } else if (Salary <= SECOND_BRACKET) {
            TaxOwed = Salary * SECOND_TAX_RATE;
        } else if (Salary <= THIRD_BRACKET) {
            TaxOwed = Salary * THIRD_TAX_RATE;
        } else if (Salary <= FOURTH_BRACKET) {
            TaxOwed = Salary * FOURTH_TAX_RATE;
        } else {
            TaxOwed = Salary * FIFTH_TAX_RATE;
        }
        
        System.out.println("The total amount of income tax owed on a salary of " + Salary + " is: " + TaxOwed + " dollars.");
        
        UserInput.close();
    }
}
