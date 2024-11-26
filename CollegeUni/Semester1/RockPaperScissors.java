import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Rock, Paper, Scissors");

        char playAgain = 'y';
        while (playAgain == 'y') {
            System.out.println("Enter r, p, or s");
            // get next character from the user
            char userChoice = input.next().charAt(0);

            while (userChoice != 'r' && userChoice != 'p' && userChoice != 's') {
                System.out.println("Invalid choice. Please enter r, p, or s");
                userChoice = input.next().charAt(0);
            }
    
            int computerChoice = (int) (Math.random() * 3);
    
            if (computerChoice == 0) {
                System.out.println("Computer chose rock");
                if (userChoice == 'r') {
                    System.out.println("Draw");
                } else if (userChoice == 'p') {
                    System.out.println("You win");
                } else {
                    System.out.println("You lose");
                }
            } else if (computerChoice == 1) {
                System.out.println("Computer chose paper");
                if (userChoice == 'r') {
                    System.out.println("You lose");
                } else if (userChoice == 'p') {
                    System.out.println("Draw");
                } else {
                    System.out.println("You win");
                }
            } else {
                System.out.println("Computer chose scissors");
                if (userChoice == 'r') {
                    System.out.println("You win");
                } else if (userChoice == 'p') {
                    System.out.println("You lose");
                } else {
                    System.out.println("Draw");
                }
            }

            System.out.println("Do you want to play again? (y/n)");
            playAgain = input.next().charAt(0);
        }
    }
}