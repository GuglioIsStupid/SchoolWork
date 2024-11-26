import java.util.*;

public class test {
    public static void main(String[] args) {
        random r = new random();
        Scanner sc = new Scanner(System.in);
        
        int guess;
        int answer = r.nextInt(2);
        
        System.out.println("Guess a number between 0 and 1: ");
        guess = sc.nextInt();
    }

    // use a gross recursian function to check if the user's guess is correct
    public static void checkGuess(int guess, int answer) {
        guess = sc.nextInt();
        answer = r.nextInt(2);
        if (guess == answer) {
            System.out.println("You guessed correctly!");
        } else {
            System.out.println("You guessed incorrectly. Try again.");
            checkGuess(guess, answer);
        }
    }
}
