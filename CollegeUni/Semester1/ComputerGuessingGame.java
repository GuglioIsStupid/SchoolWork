// GuglioIsStupid // 2024/10/09

/*
 * Computer Guessing Game
 * The computer will first guess a number 1-100
 * The computer will use the hints the player gives to have a better range of guesses
 * The computer will keep guessing until it gets the number
 * After the computer has successfully guessed the number, the computer will ask if the player wants to play again
*/


import java.util.Scanner;

public class ComputerGuessingGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        String playAgain = "y";
        int guess = 50;
        int tries = 0;
        int high = 100;
        int low = 1;
        String response = "n";

        while (playAgain.equals("y")) {
            high = 100;
            low = 1;
            // CLEAR THE SCREEN
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
            System.out.println("Welcome to computer guess!");
            System.out.println("You will think of a number between 1 and 100");
            System.out.println("Then the computer will then try to guess the number.");
            
            guess = (int) (Math.random() * 100 + 1); // we only need to guess between 1 and 100 for the first guess as the computer doesn't have any hints yet
            tries = 0;
            response = "n";
            
            while (!response.equals("y")) {
                System.out.println("OK! Think of a number between 1 and 100");
                System.out.println("Is the number " + guess + "? (y/n)");
                response = input.nextLine();
                
                while (!response.equals("y")) {
                    tries++;
                    System.out.println("OK, did I guess too high or too low? (h/l):");
                    response = input.nextLine();
                    
                    if (response.equals("h")) {
                        high = guess;
                    } else {
                        low = guess;
                    }

                    guess = (int) (Math.random() * (high - low) + low); // random number between low and high
                    System.out.println("Is the number " + guess + "? (y/n)");
                    response = input.nextLine();
                }
            }
            
            System.out.println("Yay! I guessed the number!");
            System.out.println("It took me " + tries + " tr" + (tries == 1 ? "y" : "ies") + "!");
            
            System.out.println("Should we play again? (y/n):");
            playAgain = input.nextLine();
        }

        System.out.println("Thanks for playing!");

        input.close();
    }
}
