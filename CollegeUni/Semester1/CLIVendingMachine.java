import java.util.Scanner;

public class CLIVendingMachine {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double money = 0;
        double price = 0;
        char choice;
        String productName = "";
        boolean quit = false;

        System.out.println("Welcome to the Vending Machine\n");

        while (!quit) {
            System.out.println("Main Menu:");
            System.out.println("    1. Enter coins");
            System.out.println("    2. Display Products");
            System.out.println("    3. Quit\n");
            System.out.print("Enter your choice: ");
            int menuChoice = input.nextInt();
            switch (menuChoice) {
                case 1:
                    System.out.print("\nEnter your coins: ");
                    money += input.nextDouble();
                    System.out.println("\nYou have $" + money + " entered\n");
                    break;
                case 2:
                    System.out.println("\nHere are the products for sale:");
                    System.out.println("    A. Chips $2.25");
                    System.out.println("    B. Candy $2.50");
                    System.out.println("    C. Pop $2.00");
                    System.out.println("    D. Water $1.50");
                    System.out.println("    E. Gum $1.75\n");
                    System.out.print("Enter your choice: ");

                    boolean isInvalid = true;

                    choice = input.next().toUpperCase().charAt(0);

                    while (isInvalid) {
                        isInvalid = false;
                        switch (choice) {
                            case 'A':
                                price = 2.25;
                                productName = "Chips";
                                break;
                            case 'B':
                                price = 2.50;
                                productName = "Candy";
                                break;
                            case 'C':
                                price = 2.00;
                                productName = "Pop";
                                break;
                            case 'D':
                                price = 1.50;
                                productName = "Water";
                                break;
                            case 'E':
                                price = 1.75;
                                productName = "Gum";
                                break;
                            default:
                                System.out.println("Invalid Selection");
                                isInvalid = true;
                        }
                    }

                    if (money < price) {
                        System.out.println("\n" + productName + " is $" + price + ", you have $" + money + " entered. Please enter more money\n");
                    } else {
                        System.out.println("\n" + productName + " is $" + price + ", you have $" + money + " entered.\n");
                        System.out.println("Here are your " + productName + " and your $" + (money - price) + " change.\n");
                        money -= price;
                    }

                    break;
                case 3:
                    System.out.println("\nGoodbye!");
                    quit = true;
                    break;

                default:
                    System.out.println("Invalid Selection.");
                    break;
            }
        }

        input.close();
    }
}