package GuglioFirstFewPrograms;

import java.util.Scanner;

public class PartShopping {
    public static void main(String[] args) {
        Scanner UserInput = new Scanner(System.in);

        final double USB_STORAGE_COST = 19.99;
        final double KEYBOARD_COST = 49.99;
        final double MOUSE_COST = 25.99;
        final double TAX_RATE = 0.13;
        int USBStorageAmount;
        int KeyboardAmount;
        int MouseAmount;

        double USBStorageTotal;
        double KeyboardTotal;
        double MouseTotal;
        double GrandTotal;
        double TaxTotal;

        System.out.println("Please enter the amount of USB storage devices you want to purchase: ");
        try {
            USBStorageAmount = UserInput.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid interger.");
            UserInput.close();
            return;
        }

        System.out.println("Please enter the amount of keyboards you want to purchase: ");
        try {
            KeyboardAmount = UserInput.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid interger.");
            UserInput.close();
            return;
        }

        System.out.println("Please enter the amount of mice you want to purchase: ");
        try {
            MouseAmount = UserInput.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid interger.");
            UserInput.close();
            return;
        }

        USBStorageTotal = USBStorageAmount * USB_STORAGE_COST;
        KeyboardTotal = KeyboardAmount * KEYBOARD_COST;
        MouseTotal = MouseAmount * MOUSE_COST;

        GrandTotal = USBStorageTotal + KeyboardTotal + MouseTotal;
        GrandTotal *= (1 + TAX_RATE);
        TaxTotal = GrandTotal - (USBStorageTotal + KeyboardTotal + MouseTotal);

        System.out.println("The total cost of " + USBStorageAmount + " USB storage devices is: $" + USBStorageTotal + "\n" +
                           "The total cost of " + KeyboardAmount + " keyboards is: $" + KeyboardTotal + "\n" +
                           "The total cost of " + MouseAmount + " mice is: $" + MouseTotal + "\n" +
                           "The grand total is: $" + GrandTotal + "\n" +
                           "The tax total is: $" + TaxTotal);
        
        UserInput.close();
    }
}
