package FirstFewPrograms;

// Guglio - 2024/02/20

import java.util.Scanner;

public class FirstSelectionProgram {
	public static void main(String args[]) {
		Scanner UserInput = new Scanner(System.in);
		double amountSpent, savingPercent, savingTotal, finalPrice;

		System.out.println("Enter the amount spent: ");
		amountSpent = UserInput.nextDouble();

		if (amountSpent >= 0.01 && amountSpent <= 40.00) {
			savingPercent = 10;
		} else if (amountSpent >= 40.01 && amountSpent <= 80.00) {
			savingPercent = 20;
		} else if (amountSpent >= 80.01 && amountSpent <= 120.00) {
			savingPercent = 30;
		} else {
			savingPercent = 40;
		}

		savingTotal = (amountSpent * savingPercent) / 100;
		finalPrice = amountSpent - savingTotal;

		// round to nearest hundredth
		System.out.println(
			"You spent: $" + (Math.round(amountSpent * 100.0) / 100.0) + "\n" +
			"You saved: " + savingPercent + "%\n" +
			"Total Savings: $" + (Math.round(savingTotal * 100.0) / 100.0) + "\n" +
			"Final Price: $" + (Math.round(finalPrice * 100.0) / 100.0)
		);
	}
}
